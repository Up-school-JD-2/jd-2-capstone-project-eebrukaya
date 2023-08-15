package io.upschool.mapper;

import io.upschool.dto.FlightDTO;
import io.upschool.entities.Flight;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper
public interface FlightMapper {

    FlightMapper INSTANCE = Mappers.getMapper(FlightMapper.class);

    FlightDTO.Create to(Flight flight);

    Flight from(FlightDTO.Create flightDTO);

    List<FlightDTO.Create> toDTO(List<Flight> flightList);

}
