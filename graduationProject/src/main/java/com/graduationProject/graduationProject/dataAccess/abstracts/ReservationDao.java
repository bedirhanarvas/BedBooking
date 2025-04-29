package com.graduationProject.graduationProject.dataAccess.abstracts;

import java.time.LocalDate;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.graduationProject.graduationProject.entities.concretes.Reservation;

public interface ReservationDao extends JpaRepository<Reservation, Long>{

	List<Reservation> findAll();
	
	Reservation findByReservationId(int reservationId);
	
	List<Reservation> findByUser_NameAndUser_Surname(String name, String surname);
	
	List<Reservation> findByUser_NameContainingAndUser_SurnameContaining(String name, String surname);
	
	List<Reservation> findByUser_NameContainingIgnoreCaseOrUser_SurnameContainingIgnoreCase(String keyword1, String keyword2);

	@Query("SELECT r.bed.id FROM Reservation r " +"WHERE (r.startingDate <= :endDate AND r.finishingDate >= :startDate)")
	List<Long> findOccupiedBedIdsBetweenDates(@Param("startDate") LocalDate startDate,@Param("endDate") LocalDate endDate);
	
	boolean existsByBed_IdAndStartingDateLessThanEqualAndFinishingDateGreaterThanEqual(Long bedId, LocalDate endDate, LocalDate startDate);
	
}
