package com.graduationProject.graduationProject.business.abstracts;

import java.time.LocalDate;
import java.util.List;

import com.graduationProject.graduationProject.entities.concretes.Bed;
import com.graduationProject.graduationProject.entities.concretes.Reservation;
import com.graduationProject.graduationProject.entities.concretes.User;
import com.graduationProject.graduationProject.entities.dtos.requests.ReservationCreateRequest;


public interface ReservationService {

	List<Reservation> findAll();
	
	List<Reservation> getMyReservations();
	
	Reservation findByReservationId(int reservation_id);
	
	List<Reservation> findByUser_NameAndUser_Surname(String name, String surname);
	
	List<Reservation> findByUser_NameContainingIgnoreCaseOrUser_SurnameContainingIgnoreCase(String keyword);
	
	List<Bed> findAvailableBeds(LocalDate startDate, LocalDate endDate);
	
	Reservation createReservation(ReservationCreateRequest request);
	
	void deleteMyReservation(int reservationId);
	
}
