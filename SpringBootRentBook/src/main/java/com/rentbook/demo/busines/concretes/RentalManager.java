package com.rentbook.demo.busines.concretes;

import com.rentbook.demo.busines.abstracts.*;
import com.rentbook.demo.core.config.Msg;
import com.rentbook.demo.core.config.exception.NotFoundException;
import com.rentbook.demo.dao.BookRepository;
import com.rentbook.demo.dao.RentalRepository;
import com.rentbook.demo.dao.ReservationRepository;
import com.rentbook.demo.dto.request.Rental.RentalSaveRequest;
import com.rentbook.demo.entity.Book;
import com.rentbook.demo.entity.Rental;
import com.rentbook.demo.entity.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.List;

@Service
public class RentalManager implements IRentalService {

    private static final Logger logger = LoggerFactory.getLogger(RentalManager.class);

    private final RentalRepository rentalRepository;
    private final BookRepository bookRepository;
    private final ReservationRepository reservationRepository;
    private final IBookService bookService;
    private final IUserService userService;
    private final IReservationService reservationService;
    private final IEmailService emailService;

    public RentalManager(RentalRepository rentalRepository,
                         BookRepository bookRepository,
                         ReservationRepository reservationRepository,
                         IBookService bookService,
                         IUserService userService,
                         IReservationService reservationService,
                         IEmailService emailService) {
        this.rentalRepository = rentalRepository;
        this.bookRepository = bookRepository;
        this.reservationRepository = reservationRepository;
        this.bookService = bookService;
        this.userService = userService;
        this.reservationService = reservationService;
        this.emailService = emailService;
    }

    @Transactional
    @Override
    public Rental saveRental(RentalSaveRequest rentalSaveRequest) {
        User user = userService.getUserById(rentalSaveRequest.getUserId());
        Book book = bookService.getBookById(rentalSaveRequest.getBookId());

        if (book.getStock() <= 0) {
            throw new IllegalStateException("Stokta kitap yok");
        }

        book.setStock(book.getStock() - 1);

        Rental rental = new Rental();
        rental.setUser(user);
        rental.setBook(book);
        rental.setRentalDate(LocalDate.now());
        rental.setReturnDate(LocalDate.now().plusDays(14));
        rental.setPenalty(0.0);
        rental.setReturned(false);

        bookService.updateBook(book);
        return rentalRepository.save(rental);
    }

    @Transactional
    @Override
    public double returnBook(Long rentalId) {
        Rental rental = rentalRepository.findById(rentalId)
                .orElseThrow(() -> new RuntimeException("Rental not found"));

        if (rental.isReturned()) {
            throw new IllegalStateException("Kitap daha önce iade edilmiştir.");
        }

        LocalDate today = LocalDate.now();
        rental.setReturned(true);
        rental.setReturnDate(today);

        Book book = rental.getBook();
        book.setStock(book.getStock() + 1);
        bookRepository.save(book);

        double penalty = 0.0;
        double totalCost = 0.0;
        long daysKept = ChronoUnit.DAYS.between(rental.getRentalDate(), today);
        double dailyRentalFee = book.getRentalPriceDay();

        totalCost = daysKept * dailyRentalFee;

        if (daysKept > 14) {
            long overdueDays = daysKept - 14;
            penalty = overdueDays * 10.0;
            totalCost += penalty;
            rental.setPenalty(penalty);
            logger.info("Ceza uygulanmıştır! Gün: {}, Tutar: {} TL", overdueDays, penalty);
        }

        long rentedBooksCount = rentalRepository.countByUser(rental.getUser());
        if (rentedBooksCount >= 3) {
            double discount = totalCost * 0.10;
            totalCost -= discount;
            logger.info("Kullanıcı 3 veya daha fazla kitap kiraladı. İndirim: {} TL", discount);
        }

        rentalRepository.save(rental);

        reservationService.getNextReservationForBook(book).ifPresent(reservation -> {
            String to = reservation.getUser().getEmail();
            String subject = "Rezerve Ettiğiniz Kitap Müsait!";
            String body = "Rezerve ettiğiniz '" + book.getTitle() +
                    "' kitabı şu anda müsait. 1 gün içinde almazsanız rezervasyon iptal edilecektir.";

            emailService.sendLateReturnEmail(to, subject, body);
            reservation.setNotified(true);
            reservationRepository.save(reservation);
        });

        logger.info("İade edilen kitap: {}, Kiralama süresi: {} gün, Toplam ücret: {} TL",
                book.getTitle(), daysKept, totalCost);

        return totalCost;
    }

    @Override
    public List<Rental> getAllRentals() {
        return rentalRepository.findAll();
    }

    @Override
    public Rental getRentalById(Long id) {
        return rentalRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Kiralık kitap bulunamadı"));
    }

    @Override
    public void deleteRental(Long id) {
        rentalRepository.deleteById(id);
    }

    @Override
    public Rental updateRental(Rental rental) {
        this.get(rental.getId());
        return this.rentalRepository.save(rental);
    }

    @Override
    public Rental get(Long id) {
        return this.rentalRepository.findById(id)
                .orElseThrow(() -> new NotFoundException(Msg.NOT_FOUND));
    }
}
