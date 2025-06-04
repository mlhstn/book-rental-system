package com.rentbook.demo.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.ToString;

import java.time.LocalDate;

@Entity
@Table(name="rentals")
@ToString
public class Rental {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "rental_date")
    private LocalDate rentalDate;

    @Column(name = "return_date")
    private LocalDate returnDate;

    @Column(name = "penalty")
    private double penalty = 0.0;

    @Column(name = "is_returned")
    private boolean isReturned = false;

     @ManyToOne
     @JoinColumn(name = "user_id", nullable = false)
     private User user;

     @ManyToOne
     @JoinColumn(name = "book_id", nullable = false)
     @JsonBackReference(value = "book-rentals")
     private Book book;

    public Rental() {
    }

    public Rental(Long id, LocalDate rentalDate,
                  LocalDate returnDate, double penalty,
                  boolean isReturned, User user, Book book) {
        this.id = id;
        this.rentalDate = rentalDate;
        this.returnDate = returnDate;
        this.penalty = penalty;
        this.isReturned = isReturned;
        this.user = user;
        this.book = book;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public LocalDate getRentalDate() {
        return rentalDate;
    }

    public void setRentalDate(LocalDate rentalDate) {
        this.rentalDate = rentalDate;
    }

    public LocalDate getReturnDate() {
        return returnDate;
    }

    public void setReturnDate(LocalDate returnDate) {
        this.returnDate = returnDate;
    }

    public double getPenalty() {
        return penalty;
    }

    public void setPenalty(double penalty) {
        this.penalty = penalty;
    }

    public boolean isReturned() {
        return isReturned;
    }

    public void setReturned(boolean returned) {
        isReturned = returned;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Book getBook() {
        return book;
    }

    public void setBook(Book book) {
        this.book = book;
    }
}










