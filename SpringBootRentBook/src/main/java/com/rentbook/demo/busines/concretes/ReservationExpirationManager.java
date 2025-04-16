package com.rentbook.demo.busines.concretes;

import com.rentbook.demo.busines.abstracts.IReservationExpirationService;
import com.rentbook.demo.dao.ReservationRepository;
import com.rentbook.demo.entity.Reservation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class ReservationExpirationManager implements IReservationExpirationService {

    private static final Logger logger = LoggerFactory.getLogger(ReservationExpirationManager.class);
    private final ReservationRepository reservationRepository;

    public ReservationExpirationManager(ReservationRepository reservationRepository) {
        this.reservationRepository = reservationRepository;
    }

    @Override
    @Scheduled(cron = "0 0 0 * * *")
    public void expireReservations() {

        LocalDateTime expirationThreshold = LocalDateTime.now().minusDays(1);
        List<Reservation> expiredReservations = reservationRepository
                .findByIsNotifiedTrueAndActiveTrueAndNotifiedAtBefore(expirationThreshold);

        expiredReservations.forEach(reservation -> {
            reservation.setActive(false);
            reservationRepository.save(reservation);
            logger.info("Rezervasyon iptal edildi: ID {}, Kullanıcı : {}, Kitap {}",
                    reservation.getId(),
                    reservation.getUser().getEmail(),
                    reservation.getBook().getTitle());
        });
    }
}
















