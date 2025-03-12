package com.rentbook.demo.dto.request.Rental;

import java.time.LocalDate;

public class RentalUpdateRequest {

    private LocalDate rentalDate;

    private LocalDate returnDate;

    private double penalty = 0.0;

    private boolean isReturned = false;




}
