package io.upschool.service;

import io.upschool.dto.FlightDTO;
import io.upschool.entities.Airline;
import io.upschool.entities.Airport;
import io.upschool.entities.Flight;
import io.upschool.enums.FlightStatus;
import io.upschool.globalexception.CustomExcepiton;
import io.upschool.repository.FlightRepository;
import io.upschool.response.ApiResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class FlightService {
    private final FlightRepository flightRepository;
    private final AirportService airportService;
    private final AirlineService airlineService;

    @Transactional
    public ApiResponse<List<Flight>> getAllFlights() {
        List<Flight> flightList = flightRepository.findAll();
        return new ApiResponse<>(true, flightList, "Uçuşlar başarıyla listelendi.");
    }

    @Transactional
    public ApiResponse<Flight> getFlight(Long id) {
        Optional<Flight> flight = Optional.of(flightRepository.getReferenceById(id));
        return flight.map(value -> new ApiResponse<>(true, value, "Uçuş başarıyla bulundu."))
                .orElseThrow(() -> new CustomExcepiton.FlightNotFoundException("Havalimanı bulunamadı."));

    }
    @Transactional
    public ApiResponse<Void> saveFlight(FlightDTO.Search flightDTO) {

        try {
            Airline existingAirline = airlineService.getAirlineByCode(flightDTO.getAirlineCode()).getData();
            Airport existingAirport = airportService.getAirport(flightDTO.getAirportId()).getData();

            if (existingAirport != null && existingAirline != null) {
                Flight flight = new Flight();
                Date flightDate = parseDate(flightDTO.getFlightDate()).getData();

                flight.setStatus(FlightStatus.PLANLI);
                flight.setAirline(existingAirline);
                flight.setAirport(existingAirport);
                flight.setRota(flightDTO.getRota());
                flight.setFlightTime(flightDate);
                flight.setGateNumber(flightDTO.getGateNumber());

                flightRepository.save(flight);

                return new ApiResponse<>(true, null, "Uçuş başarıyla eklendi.");

            } else {
                throw new CustomExcepiton.AirlineNotFoundException("Uçuş eklemek istediğiniz havalimanı/havayolu şirketi kaydı bulunamadı!");
            }
        } catch (Exception e) {
            throw new CustomExcepiton.RecordCreationException("Uçuş eklenirken bir hata oluştu. " + e.getMessage());
        }

    }
    @Transactional
    public ApiResponse<Date> parseDate(String input) {
        try {
            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm");
            Date flightTime = dateFormat.parse(input);
            return new ApiResponse<>(true, flightTime, "");
        } catch (ParseException e) {
            return new ApiResponse<>(false, null, "Tarih parse edilemedi!");

        }
    }
    @Transactional
    public ApiResponse<List<Flight>> getFlightsByAirport(Long airportId) {
        if (airportId != null) {
            List<Flight> flightList =flightRepository.findByAirport_Id(airportId);
            System.out.println("Hello World!");
            //List<FlightDTO.Create> creates = FlightMapper.INSTANCE.toDTO(flightList);
            return new ApiResponse<>(true, flightList, "");
        } else {
            throw new CustomExcepiton.IllegalArgumentException("AirportId boş!");
        }
    }

    @Transactional
    public ApiResponse<List<Flight>> getFlightsByAirline(String airlineCode) {
        if (airlineCode != null) {
            List<Flight> flightList =flightRepository.findByAirline_Code(airlineCode);
            return new ApiResponse<>(true, flightList, "");
        } else {
            throw new CustomExcepiton.IllegalArgumentException("AirlineId boş!");
        }
    }

    /*
    FLIGHT TIME KONTROLU & FLIGHT EDIT!!
     */
}
