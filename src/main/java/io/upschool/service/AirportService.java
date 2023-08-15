package io.upschool.service;

import io.upschool.entities.Airport;
import io.upschool.globalexception.CustomExcepiton;
import io.upschool.repository.AirportRepository;
import io.upschool.response.ApiResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class AirportService {
    private final AirportRepository airportRepository;

    @Transactional
    public ApiResponse<List<Airport>> getAllAirports() {
        List<Airport> airports = airportRepository.findAll();
        return new ApiResponse<>(true, airports, "Havalimanları başarıyla alındı.");
    }

    @Transactional
    public ApiResponse<Airport> getAirport(Long id) {
        Optional<Airport> airport = Optional.of(airportRepository.getReferenceById(id));
        return airport.map(value -> new ApiResponse<>(true, value, "Havalimanı başarıyla bulundu."))
                .orElseThrow(() -> new CustomExcepiton.AirportNotFoundException("Havalimanı bulunamadı."));

    }
    @Transactional
    public ApiResponse<Void> saveAirport(Airport airport) {
        try{
            airportRepository.save(airport);
            return new ApiResponse<>(true, null, "Havalimanı başarıyla eklendi.");
        }catch (Exception e){
            throw new CustomExcepiton.RecordCreationException("Havalimanı eklenirken bir hata oluştu. " + e.getMessage());
        }
    }
}
