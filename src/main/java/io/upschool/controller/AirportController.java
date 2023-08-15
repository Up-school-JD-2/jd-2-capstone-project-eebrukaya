package io.upschool.controller;

import io.swagger.annotations.ApiOperation;
import io.upschool.entities.Airport;
import io.upschool.response.ApiResponse;
import io.upschool.service.AirportService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/ticketing/parametre/airport")
public class AirportController {

    private final AirportService airportService;

    public AirportController(AirportService airportService) {
        this.airportService = airportService;
    }

    @GetMapping
    @ApiOperation("Havalimanı kayıtlarını döndürür.")
    public ResponseEntity<ApiResponse<List<Airport>>> getAllAirports() {
        return ResponseEntity.ok(airportService.getAllAirports());
    }

    @GetMapping("/{id}")
    @ApiOperation("Id bilgisi verilen havalimanı kaydını döndürür.")
    public ResponseEntity<ApiResponse<Airport>> getAirport(@PathVariable Long id) {
        return ResponseEntity.ok(airportService.getAirport(id));
    }

    @PostMapping
    @ApiOperation("Yeni havalimanı kaydeder.")
    public ResponseEntity<ApiResponse<Void>> saveAirport(@RequestBody Airport airport) {
        return ResponseEntity.status(HttpStatus.CREATED).body(airportService.saveAirport(airport));
    }
}
