package com.rentbook.demo.dao;

import com.rentbook.demo.entity.Rental;
import com.rentbook.demo.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RentalRepository extends JpaRepository<Rental,Long> {

    long countByUser(User user);

}
