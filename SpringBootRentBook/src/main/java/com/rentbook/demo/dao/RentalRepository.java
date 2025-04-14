package com.rentbook.demo.dao;

import com.rentbook.demo.entity.Book;
import com.rentbook.demo.entity.Rental;
import com.rentbook.demo.entity.Reservation;
import com.rentbook.demo.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

public interface RentalRepository extends JpaRepository<Rental,Long> {

    long countByUser(User user);

    List<Rental> findByIsReturnedFalseAndReturnDateBefore(LocalDate today);

}
