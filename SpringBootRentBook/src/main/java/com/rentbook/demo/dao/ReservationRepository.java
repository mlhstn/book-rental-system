package com.rentbook.demo.dao;

import com.rentbook.demo.entity.Book;
import com.rentbook.demo.entity.Reservation;
import com.rentbook.demo.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

public interface ReservationRepository extends JpaRepository<Reservation,Long> {

    //Bir kitap için sırada bekleyen rezervasyonları getirir (en eski rezervasyon başta).
    List<Reservation> findByBookAndIsNotifiedFalseOrderByReservationDateAsc(Book book);

    //Kitap iade edildiğinde ilk sıradaki kullanıcıyı buluruz.
    Optional<Reservation> findFirstByBookAndIsNotifiedFalseOrderByReservationDateAsc(Book book);

    //Aynı kullanıcı aynı kitap için tekrar rezervasyon yapmasın diye.
    boolean existsByUserAndBookAndIsNotifiedFalse(User user, Book book);

    // Bildirimi gönderilmiş, aktif rezervasyonlardan notifiedAt 1 günden eski olanları getir.
    List<Reservation> findByIsNotifiedTrueAndActiveTrueAndNotifiedAtBefore(LocalDateTime time);



}

