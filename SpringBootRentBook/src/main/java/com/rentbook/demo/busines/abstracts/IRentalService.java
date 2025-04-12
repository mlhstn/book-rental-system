package com.rentbook.demo.busines.abstracts;

import com.rentbook.demo.dto.request.Rental.RentalSaveRequest;
import com.rentbook.demo.entity.Book;
import com.rentbook.demo.entity.Rental;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface IRentalService {

    Rental saveRental(RentalSaveRequest rentalSaveRequest);

    List<Rental> getAllRentals();

    Rental getRentalById(Long id);

    void deleteRental(Long id);

    Rental updateRental(Rental rental);

    Rental get(Long id);

    double returnBook(Long rentalId);
}
