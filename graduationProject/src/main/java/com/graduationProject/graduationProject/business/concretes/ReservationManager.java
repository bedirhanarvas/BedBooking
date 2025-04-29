package com.graduationProject.graduationProject.business.concretes;

import java.time.LocalDate;
import java.util.List;
import java.util.Random;

import javax.security.auth.login.AccountNotFoundException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.graduationProject.graduationProject.business.abstracts.AuthenticationService;
import com.graduationProject.graduationProject.business.abstracts.ReservationService;
import com.graduationProject.graduationProject.dataAccess.abstracts.BedDao;
import com.graduationProject.graduationProject.dataAccess.abstracts.ReservationDao;
import com.graduationProject.graduationProject.entities.concretes.Bed;
import com.graduationProject.graduationProject.entities.concretes.Reservation;
import com.graduationProject.graduationProject.entities.concretes.User;
import com.graduationProject.graduationProject.entities.dtos.requests.ReservationCreateRequest;

@Service
public class ReservationManager implements ReservationService{
	
	private final ReservationDao reservationDao;
	
	private final BedDao bedDao;
	
	
	
	@Autowired
	public ReservationManager(ReservationDao reservationDao, BedDao bedDao,AuthenticationService authenticationService) {
		this.reservationDao=reservationDao;
		this.bedDao=bedDao;
		
	}

	@Override
	public List<Reservation> findAll() {

		return reservationDao.findAll();
	}

	@Override
	public List<Reservation>  getMyReservations(){
		
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		
		User currentUser = (User) authentication.getPrincipal();
		
		return reservationDao.findByUser_NameAndUser_Surname(currentUser.getName(), currentUser.getSurname());
	}
	
	
	
	@Override
	public Reservation findByReservationId(int reservationId) {
        Reservation reservation = reservationDao.findByReservationId(reservationId);
        if (reservation == null) {
            throw new RuntimeException("Bu Reservasyon ID " + reservationId + " bulunamadı.");
        }
        return reservation;
    }

	@Override
	public List<Reservation> findByUser_NameAndUser_Surname(String name, String surname) {

		return reservationDao.findByUser_NameContainingAndUser_SurnameContaining(name, surname);
	}
	
	@Override
	public List<Reservation> findByUser_NameContainingIgnoreCaseOrUser_SurnameContainingIgnoreCase(String keyword) {
		
		return reservationDao.findByUser_NameContainingIgnoreCaseOrUser_SurnameContainingIgnoreCase(keyword, keyword);
	}
	
	@Override
	public List<Bed> findAvailableBeds(LocalDate startDate, LocalDate endDate) {
	    List<Long> occupiedBedIds = reservationDao.findOccupiedBedIdsBetweenDates(startDate, endDate);

	    if (occupiedBedIds.isEmpty()) {
	        return bedDao.findAll();
	    }

	    return bedDao.findByIdNotIn(occupiedBedIds);
	}

	@Override
	public Reservation createReservation(ReservationCreateRequest request) {
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		
		User currentUser = (User) authentication.getPrincipal();

        boolean isOccupied = reservationDao.existsByBed_IdAndStartingDateLessThanEqualAndFinishingDateGreaterThanEqual(
            request.getBedId(),
            request.getFinishingDate(),
            request.getStartingDate()
        );

        if (isOccupied) {
            throw new RuntimeException("Selected bed is already occupied during the selected dates.");
        }

        Bed bed = bedDao.findById(request.getBedId())
                .orElseThrow(() -> new RuntimeException("Selected bed not found."));

        Reservation reservation = new Reservation();
        reservation.setUser(currentUser);
        reservation.setBed(bed);
        reservation.setStartingDate(request.getStartingDate());
        reservation.setFinishingDate(request.getFinishingDate());
        reservation.setReservationId(generateReservationId());

        return reservationDao.save(reservation);
	}
	
	 private int generateReservationId() {
	        Random random = new Random();
	        return 100000 + random.nextInt(900000); 
	    }
	 
	 @Override
	 @Transactional
	 public void deleteMyReservation(int reservationId) {
	     Reservation reservation = reservationDao.findByReservationId(reservationId);

	     if (reservation == null) {
	         throw new RuntimeException("Rezervasyon bulunamadı.");
	     }

	     reservationDao.delete(reservation);
	 }



}
