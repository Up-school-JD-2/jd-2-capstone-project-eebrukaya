package io.upschool.controller;

import io.swagger.annotations.ApiOperation;
import io.upschool.dto.FlightDTO;
import io.upschool.entities.Flight;
import io.upschool.response.ApiResponse;
import io.upschool.service.FlightService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/ticketing/parametre/flight")
public class FlightController {

    private final FlightService flightService;

    public FlightController(FlightService flightService) {
        this.flightService = flightService;
    }

    @GetMapping
    @ApiOperation("Uçuş kayıtlarını döndürür.")
    public ResponseEntity<ApiResponse<List<Flight>>> getFlights() {
        ApiResponse<List<Flight>> response = flightService.getAllFlights();
        return ResponseEntity.ok(response);
    }

    @GetMapping("/{id}")
    @ApiOperation("Id bilgisi verilen uçuş kaydını döndürür.")
    public ResponseEntity<ApiResponse<Flight>> getFlight(@PathVariable Long id) {
        ApiResponse<Flight> crewMember = flightService.getFlight(id);
        return ResponseEntity.ok(crewMember);
    }


    @PostMapping
    @ApiOperation("Uçuş bilgisi kaydeder.")
    public ResponseEntity<ApiResponse<Void>> saveFlight(@RequestBody FlightDTO.Search flightDTO) {
        ApiResponse<Void> response = flightService.saveFlight(flightDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @GetMapping("/by-airport/{airportId}")
    @ApiOperation("Airporta ait tüm uçuşları listeler.")
    public ResponseEntity<ApiResponse<List<Flight>>> getFlightsByAirport(@PathVariable Long airportId) {
        ApiResponse<List<Flight>> response = flightService.getFlightsByAirport(airportId);
        return ResponseEntity.ok(response);
    }

    @GetMapping("/airlineCode")
    @ApiOperation("Airline'a ait tüm uçuşları listeler.")
    public ResponseEntity<ApiResponse<List<Flight>>> getFlightsByAirline(@RequestParam String airlineCode) {
        ApiResponse<List<Flight>> response = flightService.getFlightsByAirline(airlineCode);
        return ResponseEntity.ok(response);
    }

    /*
    UÇUŞA UÇAK EKLE
     */
}
