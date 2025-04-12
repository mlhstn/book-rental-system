package com.rentbook.demo.busines.concretes;

import com.rentbook.demo.busines.abstracts.IReservationService;
import com.rentbook.demo.dao.ReservationRepository;
import com.rentbook.demo.entity.Book;
import com.rentbook.demo.entity.Reservation;
import com.rentbook.demo.entity.User;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Service
public class ReservationManager implements IReservationService {

    private final ReservationRepository reservationRepository;



    public ReservationManager(ReservationRepository reservationRepository) {
        this.reservationRepository = reservationRepository;
    }


    @Override
    public Reservation createReservation(User user, Book book) {

        if(reservationRepository.existsByUserAndBookAndIsNotifiedFalse(user, book)) {
            throw new IllegalStateException("Bu kullanıcı bu kitap için bir rezervasyon oluşturmuştur");
        }

        Reservation reservation = new Reservation();
        reservation.setUser(user);
        reservation.setBook(book);
        reservation.setReservationDate(LocalDate.now());
        reservation.setNotified(false);

        return reservationRepository.save(reservation);
    }

    @Override
    public List<Reservation> getReservationsForBook(Book book) {
        return reservationRepository.findByBookAndIsNotifiedFalseOrderByReservationDateAsc(book);
    }

    @Override
    public Reservation getNextReservation(Book book) {
        return reservationRepository.findFirstByBookAndIsNotifiedFalseOrderByReservationDateAsc(book)
                .orElse(null);
    }

    @Override
    public boolean hasActiveReservation(User user, Book book) {
        return reservationRepository.existsByUserAndBookAndIsNotifiedFalse(user, book);
    }


    @Override
    public void markNotified(Reservation reservation) {
        reservation.setNotified(true);
        reservationRepository.save(reservation);

    }

    @Override
    public Optional<Reservation> getNextReservationForBook(Book book) {
        return reservationRepository.findFirstByBookAndIsNotifiedFalseOrderByReservationDateAsc(book);
    }
}
