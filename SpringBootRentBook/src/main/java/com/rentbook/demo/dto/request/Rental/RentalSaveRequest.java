package com.rentbook.demo.dto.request.Rental;

public class RentalSaveRequest {

    private long userId;

    private long bookId;

    public RentalSaveRequest(long userId, long bookId) {
        this.userId = userId;
        this.bookId = bookId;
    }

    public long getUserId() {
        return userId;
    }

    public void setUserId(long userId) {
        this.userId = userId;
    }

    public long getBookId() {
        return bookId;
    }

    public void setBookId(long bookId) {
        this.bookId = bookId;
    }
}
