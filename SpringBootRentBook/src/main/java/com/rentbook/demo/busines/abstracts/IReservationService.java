package com.rentbook.demo.busines.abstracts;

import com.rentbook.demo.entity.Book;
import com.rentbook.demo.entity.Reservation;
import com.rentbook.demo.entity.User;

import java.util.List;
import java.util.Optional;

public interface IReservationService {

    Reservation createReservation(User user, Book book);
    List<Reservation> getReservationsForBook(Book book);
    Optional<Reservation> getNextReservationForBook(Book book);
    boolean hasActiveReservation(User user, Book book);
    List<Reservation> getAllReservations();

}
