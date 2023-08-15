package io.upschool.controller;

import io.swagger.annotations.ApiOperation;
import io.upschool.entities.Airline;
import io.upschool.entities.Airplane;
import io.upschool.response.ApiResponse;
import io.upschool.service.AirlineService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping({"/ticketing/parametre/airline", "/ticketing/parametre/airplane"})
public class AirlineController {
    private final AirlineService airlineService;

    public AirlineController(AirlineService airlineService) {
        this.airlineService = airlineService;
    }

    @GetMapping
    @ApiOperation("Havayolu şirket kayıtlarını döndürür.")
    public ResponseEntity<ApiResponse<List<Airline>>> getAllAirlines() {
        ApiResponse<List<Airline>> response = airlineService.getAllAirlines();
        return ResponseEntity.ok(response);
    }

    @GetMapping("/{code}")
    @ApiOperation("Code bilgisi verilen havayolu kaydını döndürür.")
    public ResponseEntity<ApiResponse<Airline>> getAirlineByCode(@PathVariable String code) {
        ApiResponse<Airline> response = airlineService.getAirlineByCode(code);
        return ResponseEntity.ok(response);
    }

    @PostMapping
    @ApiOperation("Yeni havayolu şirketi kaydeder.")
    public ResponseEntity<ApiResponse<Void>> saveAirline(@RequestBody Airline airline) {
        ApiResponse<Void> response = airlineService.saveAirline(airline);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @PostMapping("/airlineCode/{airlineCode}")
    @ApiOperation("Havayolu şirketi için uçak kaydeder.")
    public ResponseEntity<ApiResponse<Void>> saveAirplaneForAirline(@PathVariable String airlineCode,
                                                                    @RequestBody Airplane airplane) {
        ApiResponse<Void> response = airlineService.saveAirplaneForAirline(airlineCode, airplane);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

}
