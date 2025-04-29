package com.graduationProject.graduationProject.api.controller;

import java.time.LocalDate;
import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.graduationProject.graduationProject.business.abstracts.ReservationService;
import com.graduationProject.graduationProject.entities.concretes.Bed;
import com.graduationProject.graduationProject.entities.concretes.Reservation;
import com.graduationProject.graduationProject.entities.dtos.requests.ReservationCreateRequest;

@RestController
@RequestMapping("/reservations")
public class ReservationController {

	private final ReservationService reservationService;
	
	public ReservationController(ReservationService reservationService) {
		this.reservationService=reservationService;
	}
	
	@GetMapping("/getAll")
	public ResponseEntity<List<Reservation>> getAll(){
		return ResponseEntity.ok(reservationService.findAll()) ;
	}
	
	@GetMapping("/me")
	public ResponseEntity<List<Reservation>> getMyReservations(){
		return ResponseEntity.ok(reservationService.getMyReservations());
	}
	
    @GetMapping("/getByNameAndSurname")
    public ResponseEntity<List<Reservation>> getByNameAndSurname(@RequestParam String name,@RequestParam String surname) {
        return ResponseEntity.ok(reservationService.findByUser_NameAndUser_Surname(name, surname));
    }
	
    @GetMapping("/{reservationId:\\d+}")
    public ResponseEntity<Reservation> getByReservationId(@PathVariable int reservationId) {
        Reservation reservation = reservationService.findByReservationId(reservationId);
        return ResponseEntity.ok(reservation);
    }
    
    @GetMapping("/search")
    public ResponseEntity<List<Reservation>> searchByNameOrSurname(@RequestParam String keyword) {
        return ResponseEntity.ok(reservationService.findByUser_NameContainingIgnoreCaseOrUser_SurnameContainingIgnoreCase(keyword));
    }
    
    @GetMapping("/available-beds")
    public ResponseEntity<List<Bed>> getAvailableBeds(@RequestParam LocalDate startDate,@RequestParam LocalDate endDate) {
        List<Bed> availableBeds = reservationService.findAvailableBeds(startDate, endDate);
        return ResponseEntity.ok(availableBeds);
    }

    @PostMapping("/create")
    public Reservation createReservation(@RequestBody ReservationCreateRequest request) {
        return reservationService.createReservation(request);
    }
    
    @DeleteMapping("/{reservationId}")
    public ResponseEntity<String> deleteMyReservation(@PathVariable int reservationId) {
        reservationService.deleteMyReservation(reservationId);
        return ResponseEntity.ok("Rezervasyon başarıyla silindi.");
    }
	
	
	
	
}
