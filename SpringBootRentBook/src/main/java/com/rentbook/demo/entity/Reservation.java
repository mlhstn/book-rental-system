package com.rentbook.demo.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
@Table(name = "reservation")
public class Reservation {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(optional = false)
    @JoinColumn(name = "user_id")
    private User user;

    @ManyToOne(optional = false)
    @JoinColumn(name = "book_id")
    private Book book;

    @Column(name = "reservation_date", nullable = false)
    private LocalDate reservationDate;

    @Column(name = "is_notified", nullable = false)
    private boolean isNotified = false;

    @Column(name = "notified_at")
    private LocalDateTime notifiedAt;

    @Column(name = "active", nullable = false)
    private boolean active = true;



    public Reservation() {
        this.reservationDate = LocalDate.now();
    }

    public Reservation(Long id,
                       User user,
                       Book book,
                       LocalDate reservationDate,
                       boolean isNotified,
                       LocalDateTime notifiedAt,
                       boolean active) {
        this.id = id;
        this.user = user;
        this.book = book;
        this.reservationDate = reservationDate;
        this.isNotified = isNotified;
        this.notifiedAt = notifiedAt;
        this.active = active;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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

    public LocalDate getReservationDate() {
        return reservationDate;
    }

    public void setReservationDate(LocalDate reservationDate) {
        this.reservationDate = reservationDate;
    }

    public boolean isNotified() {
        return isNotified;
    }

    public void setNotified(boolean notified) {
        isNotified = notified;
    }

    public LocalDateTime getNotifiedAt() {
        return notifiedAt;
    }

    public void setNotifiedAt(LocalDateTime notifiedAt) {
        this.notifiedAt = notifiedAt;
    }

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }
}
