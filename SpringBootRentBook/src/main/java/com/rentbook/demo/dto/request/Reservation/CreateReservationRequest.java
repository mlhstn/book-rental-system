package com.rentbook.demo.dto.request.Reservation;

public class CreateReservationRequest {

    public CreateReservationRequest(Long userId, Long bookId) {
        this.userId = userId;
        this.bookId = bookId;
    }

    private Long userId;
    private Long bookId;

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public Long getBookId() {
        return bookId;
    }

    public void setBookId(Long bookId) {
        this.bookId = bookId;
    }
}
