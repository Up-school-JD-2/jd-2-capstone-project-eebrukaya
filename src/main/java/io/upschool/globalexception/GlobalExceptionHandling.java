package io.upschool.globalexception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandling {

    @ExceptionHandler(CustomExcepiton.AirlineNotFoundException.class)
    public ResponseEntity<String> handleAirlineNotFoundException(CustomExcepiton.AirlineNotFoundException ex) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Airline bulunamadı.");
    }

    @ExceptionHandler(CustomExcepiton.AirportNotFoundException.class)
    public ResponseEntity<String> handleAirportNotFoundException(CustomExcepiton.AirportNotFoundException ex) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Airport bulunamadı.");
    }

    @ExceptionHandler(CustomExcepiton.FlightNotFoundException.class)
    public ResponseEntity<String> handleFlightNotFoundException(CustomExcepiton.FlightNotFoundException ex) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Flight bulunamadı.");
    }

    @ExceptionHandler(CustomExcepiton.RecordCreationException.class)
    public ResponseEntity<String> handleRecordCreationException(CustomExcepiton.RecordCreationException ex) {
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Kaydederken bir hata gerçekleşti.");
    }

    @ExceptionHandler(CustomExcepiton.AirportUnavailableException.class)
    public ResponseEntity<String> handleAirportUnavailableException(CustomExcepiton.AirportUnavailableException ex) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Uçuş tanımlamak istediğiniz havaalanı mevcut değil.");
    }

    @ExceptionHandler(CustomExcepiton.AirlineUnavailableException.class)
    public ResponseEntity<String> handleAirlineUnavailableException(CustomExcepiton.AirlineUnavailableException ex) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Uçuş tanımlamak istediğiniz havayolu şirketi mevcut değil.");
    }

}
