package io.upschool.service;

import io.upschool.dto.FlightCrewMemberDTO;
import io.upschool.entities.Airline;
import io.upschool.entities.FlightCrewMember;
import io.upschool.enums.Gender;
import io.upschool.enums.Mission;
import io.upschool.globalexception.CustomExcepiton;
import io.upschool.repository.FlightCrewMemberRepository;
import io.upschool.response.ApiResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class FlightCrewMemberService {
    private final FlightCrewMemberRepository flightCrewMemberRepository;

    private final AirlineService airlineService;

    @Transactional
    public ApiResponse<List<FlightCrewMember>> getAllFlightCrewMembers() {
        List<FlightCrewMember> crewMemberList = flightCrewMemberRepository.findAll();
        return new ApiResponse<>(true, crewMemberList, "Uçuş personelleri başarıyla listelendi.");
    }

    @Transactional
    public ApiResponse<FlightCrewMember> getFlightCrewMember(Long id) {
        Optional<FlightCrewMember> flightCrewMember = Optional.of(flightCrewMemberRepository.getReferenceById(id));
        return flightCrewMember.map(value -> new ApiResponse<>(true, value, "Personel başarıyla bulundu."))
                .orElseThrow(() -> new CustomExcepiton.CrewMemberNotFoundException("Personel bulunamadı."));
    }

    @Transactional
    public ApiResponse<FlightCrewMemberDTO> saveCrewMember(FlightCrewMemberDTO flightCrewMemberDTO) {
        try {
            if (flightCrewMemberDTO != null) {
                FlightCrewMember crewMember = new FlightCrewMember();
                crewMember.setName(flightCrewMemberDTO.getName());
                crewMember.setLastName(flightCrewMemberDTO.getLastName());
                crewMember.setIdentityNumber(flightCrewMemberDTO.getIdentityNumber());
                crewMember.setMission(Mission.valueOf(flightCrewMemberDTO.getMission()));
                crewMember.setGender(Gender.valueOf(flightCrewMemberDTO.getGender()));
                crewMember.setAge(flightCrewMemberDTO.getAge());
                crewMember.setPhone(flightCrewMemberDTO.getPhone());

                Airline airline = airlineService.getAirlineByCode(flightCrewMemberDTO.getAirlineCode()).getData();

                if (airline != null) crewMember.setAirline(airline);

                //FlightCrewMemberMapper.INSTANCE.from(flightCrewMemberDTO);
                flightCrewMemberRepository.save(crewMember);
                return new ApiResponse<>(true, flightCrewMemberDTO, "Personel başarıyla eklendi.");
            } else throw new CustomExcepiton.IllegalArgumentException("Tüm bilgileri eksiksiz girin");
        } catch (Exception e) {
            throw new CustomExcepiton.RecordCreationException("Personel eklenirken bir hata oluştu. " + e.getMessage());
        }
    }
}
