package io.upschool.service;

import io.upschool.entities.Airline;
import io.upschool.entities.Airplane;
import io.upschool.globalexception.CustomExcepiton;
import io.upschool.repository.AirplaneRepository;
import io.upschool.response.ApiResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class AirplaneService {
    private final AirplaneRepository airplaneRepository;

    @Transactional
    public ApiResponse<Void> saveAirplane(Airline airline, Airplane airplane) {
        try {
            airplane.setAirline(airline);
            airplaneRepository.save(airplane);
            return new ApiResponse<>(true, null, "Uçak başarıyla eklendi.");
        } catch (Exception e) {
            throw new CustomExcepiton.RecordCreationException("Uçak eklenirken bir hata oluştu. " + e.getMessage());
        }
    }
}