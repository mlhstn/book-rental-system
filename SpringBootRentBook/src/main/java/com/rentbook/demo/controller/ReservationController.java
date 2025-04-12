package com.rentbook.demo.controller;

import com.rentbook.demo.busines.abstracts.IBookService;
import com.rentbook.demo.busines.abstracts.IReservationService;
import com.rentbook.demo.busines.abstracts.IUserService;
import com.rentbook.demo.entity.Book;
import com.rentbook.demo.entity.Reservation;
import com.rentbook.demo.entity.User;
import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/reservations")
@Data
public class ReservationController {

    private final IReservationService reservationService;
    private final IUserService userService;
    private final IBookService bookService;

    public ReservationController(IReservationService reservationService, IUserService userService, IBookService bookService) {
        this.reservationService = reservationService;
        this.userService = userService;
        this.bookService = bookService;
    }

    public ResponseEntity<String> createReservation(@RequestParam Long userId,
                                                    @RequestParam Long bookId) {

        User user = userService.getUserById(userId);
        Book book = bookService.getBookById(bookId);

        if (book.getStock() > 0) {
            return ResponseEntity.badRequest().body("Kitabı kiralayabilirsiniz");
        }
        if (reservationService.hasActiveReservation(user, book)) {
            return ResponseEntity.badRequest().body("Bu kullanıcı zaten bu kitap için bir rezervasyon yapmış.");
        }

        reservationService.createReservation(user,book);
        return ResponseEntity.ok("Rezervasyon başrıyla oluşturuldu.");
    }

    @GetMapping("/book/{bookId}")
    public ResponseEntity<List<Reservation>> getReservationsForBook(@PathVariable Long bookId) {
        Book book = bookService.getBookById(bookId);
        return ResponseEntity.ok(reservationService.getReservationsForBook(book));
    }

}
















