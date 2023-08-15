package io.upschool.service;

import io.upschool.entities.Airline;
import io.upschool.entities.Airplane;
import io.upschool.globalexception.CustomExcepiton;
import io.upschool.repository.AirlineRepository;
import io.upschool.response.ApiResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class AirlineService {
    private final AirlineRepository airlineRepository;
    private final AirplaneService airplaneService;

    @Transactional
    public ApiResponse<List<Airline>> getAllAirlines() {
        List<Airline> airlines = airlineRepository.findAll();
        return new ApiResponse<>(true, airlines, "Havayolu şirketleri başarıyla alındı.");
    }

    @Transactional
    public ApiResponse<Airline> getAirlineByCode(String code) {
        Optional<Airline> airline = Optional.of(airlineRepository.getReferenceById(code));
        return airline.map(value -> new ApiResponse<>(true, value, "Havayolu şirketi bulundu."))
                .orElseThrow(() -> new CustomExcepiton.AirlineNotFoundException("Havayolu şirketi bulunamadı."));

    }

    @Transactional
    public ApiResponse<Void> saveAirline(Airline airline) {
        try {
            airlineRepository.save(airline);
            return new ApiResponse<>(true, null, "Havayolu şirketi başarıyla eklendi.");
        } catch (Exception e) {
            throw new CustomExcepiton.RecordCreationException("Havayolu şirketi eklenirken bir hata oluştu.");
        }
    }

    @Transactional
    public ApiResponse<Void> saveAirplaneForAirline(String airlineCode, Airplane airplane) {
        try {
            Airline airline = airlineRepository.getReferenceById(airlineCode);
            airplaneService.saveAirplane(airline, airplane);
            return new ApiResponse<>(true, null, "Uçak başarıyla havayolu şirketine eklendi.");
        } catch (Exception e) {
            throw new CustomExcepiton.RecordCreationException("Uçak havayolu şirketine eklenirken bir hata oluştu. " + e.getMessage());
        }
    }

}
