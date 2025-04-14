package com.rentbook.demo.busines.concretes;

import com.rentbook.demo.busines.abstracts.ILateReturnNotifierService;
import com.rentbook.demo.dao.RentalRepository;
import com.rentbook.demo.entity.Rental;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.List;

@Service
public class LateReturnNotifierManager implements ILateReturnNotifierService {

    private final RentalRepository rentalRepository;
    private final EmailManager emailManager;

    public LateReturnNotifierManager(RentalRepository rentalRepository, EmailManager emailManager) {
        this.rentalRepository = rentalRepository;
        this.emailManager = emailManager;
    }

    @Override
    @Scheduled(cron = "0 0 9 * * *")
    public void notifyLateReturns() {

        List<Rental> lateRentals = rentalRepository.findByIsReturnedFalseAndReturnDateBefore(LocalDate.now());

        for (Rental rental : lateRentals) {
            String to = rental.getUser().getEmail();
            String bookTitle = rental.getBook().getTitle();
            long daysLate = ChronoUnit.DAYS.between(rental.getReturnDate(),LocalDate.now());

            String subject = "Geciken kitap iadesi bildirimi";
            String body = String.format("Sayın %s,\n\n'%s' adlı kitabın iade tarihi %s idi.\n" +
                            " Şu anda %d gündür gecikmiş durumda.\n" +
                            "Bilginize: Her geciken gün için 10₺ ceza uygulanacaktır.\n\n " +
                            "Lütfen kitabı en kısa sürede iade ediniz.\n\nİyi günler.",
                    rental.getUser().getName(),
                    bookTitle,
                    rental.getReturnDate(),
                    daysLate);

            emailManager.sendLateReturnEmail(to,subject,body);
        }
    }
}
