package com.rentbook.demo.busines.concretes;

import com.rentbook.demo.busines.abstracts.IBookService;
import com.rentbook.demo.busines.abstracts.IRentalService;
import com.rentbook.demo.busines.abstracts.IUserService;
import com.rentbook.demo.core.config.Msg;
import com.rentbook.demo.core.config.exception.NotFoundException;
import com.rentbook.demo.dao.RentalRepository;
import com.rentbook.demo.dto.request.Rental.RentalSaveRequest;
import com.rentbook.demo.entity.Book;
import com.rentbook.demo.entity.Rental;
import com.rentbook.demo.entity.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.List;

@Service
public class RentalManager implements IRentalService {

    private static final Logger logger = LoggerFactory.getLogger(RentalManager.class);

    private RentalRepository rentalRepository;


    private IBookService bookService;
    private IUserService userService;


    public RentalManager(RentalRepository rentalRepository,
                         IBookService bookService,
                         IUserService userService
                        ) {
        this.rentalRepository = rentalRepository;
        this.bookService = bookService;
        this.userService = userService;

    }

    @Transactional
    @Override
    public Rental saveRental(RentalSaveRequest rentalSaveRequest) {
        User user = userService.getUserById(rentalSaveRequest.getUserId());
        Book book = bookService.getBookById(rentalSaveRequest.getBookId());

        if (book.getStock() <= 0) {
            throw new IllegalStateException("stokta kitap yok");
        }
        book.setStock(book.getStock() - 1);

        Rental rental = new Rental();
        rental.setUser(user);
        rental.setBook(book);
        rental.setRentalDate(LocalDate.now()); // bugünün tarihi ata
        rental.setReturnDate(LocalDate.now().plusDays(14)); // 14 gün sonrası iade tarihi
        rental.setPenalty(0.0);
        rental.setReturned(false);

        bookService.updateBook(book); // Kitap stok güncellemesini kaydet
        return rentalRepository.save(rental); // Kiralama işlemini kaydet
    }

    @Override
    public double returnBook(long rentalId) {
        Rental rental = rentalRepository
                .findById(rentalId)
                .orElseThrow(() -> new IllegalStateException("Kayıt bulunamadı"));

        if (rental.isReturned()) {
            throw new IllegalStateException("Kitap daha önce iade edilmiştir!");
        }

        // Kitap stokunu arttır
        Book book = rental.getBook();
        book.setStock(book.getStock() + 1);

        // Kitabın günlük kiralama bedelini al
        double dailyRentalFee = book.getRentalPriceDay(); // Kitabın rentalPriceDay'ini al
        double penalty = 0.0;
        double totalCost = 0.0;

        // Kiralama süresi hesapla
        LocalDate borrowDate = rental.getRentalDate();
        LocalDate returnDate = LocalDate.now();
        long daysKept = ChronoUnit.DAYS.between(borrowDate, returnDate);

        rental.setReturnDate(returnDate);

        // Toplam kiralama ücreti hesapla
       totalCost = daysKept * dailyRentalFee;


        // 14 günü aşarsa ceza ekle
        if (daysKept > 14) {
            long overdueDays = daysKept - 14;
            penalty = overdueDays * 10.0; // Günlük ceza 10 TL
            totalCost += penalty; // Cezayı toplam ücrete ekliyoruz
            rental.setPenalty(penalty);
            logger.info("Ceza uygulanmıştır! Gün: {}," +
                    " Tutar: {} TL", overdueDays, penalty);
        }

        // Kullanıcının kiraladığı kitap sayısını kontrol et
        long rentedBooksCount = rentalRepository.countByUser(rental.getUser()); // Kullanıcının kiraladığı kitap sayısı
        if (rentedBooksCount >= 3) {
            // %10 indirim uygulayalım
            double discount = totalCost * 0.10; // %10 indirim
            totalCost -= discount; // İndirimli fiyat
            logger.info("Kullanıcı 3 veya daha fazla kitap kiraladı. İndirim: {} TL", discount);
        }

        // Kitap iade edildi olarak işaretle
        rental.setReturned(true);

        // Kiralama kaydını güncelle
        rentalRepository.save(rental);

        // Kitap güncellemeyi yap
        bookService.updateBook(book);

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
        return rentalRepository.findById(id).orElseThrow(() -> new RuntimeException("Kiralık kitap bulunamadı"));
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

