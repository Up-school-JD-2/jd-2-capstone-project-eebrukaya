package io.upschool.controller;

import io.swagger.annotations.ApiOperation;
import io.upschool.dto.FlightCrewMemberDTO;
import io.upschool.entities.Airline;
import io.upschool.entities.Airplane;
import io.upschool.entities.FlightCrewMember;
import io.upschool.response.ApiResponse;
import io.upschool.service.FlightCrewMemberService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/ticketing/parametre/flightcrewmember")
public class FlightCrewMemberController {

    private final FlightCrewMemberService flightCrewMemberService;

    public FlightCrewMemberController(FlightCrewMemberService flightCrewMemberService) {
        this.flightCrewMemberService = flightCrewMemberService;
    }

    @GetMapping
    @ApiOperation("Uçuş personel kayıtlarını döndürür.")
    public ResponseEntity<ApiResponse<List<FlightCrewMember>>> getAllCrewMembers() {
        ApiResponse<List<FlightCrewMember>> response = flightCrewMemberService.getAllFlightCrewMembers();
        return ResponseEntity.ok(response);
    }

    @GetMapping("/{id}")
    @ApiOperation("Id bilgisi verilen uçuş personel kaydını döndürür.")
    public ResponseEntity<ApiResponse<FlightCrewMember>> getAirlineByCode(@PathVariable Long id) {
        ApiResponse<FlightCrewMember> crewMember = flightCrewMemberService.getFlightCrewMember(id);
        return ResponseEntity.ok(crewMember);
    }


    @PostMapping
    @ApiOperation("Uçuş personellerini kaydeder.")
    public ResponseEntity<ApiResponse<FlightCrewMemberDTO>> saveCrewMember(@RequestBody FlightCrewMemberDTO crewMemberDTO) {
        ApiResponse<FlightCrewMemberDTO> response = flightCrewMemberService.saveCrewMember(crewMemberDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    /*
    UÇUŞ TANIMLAMA METODU EKLE
     */
}
