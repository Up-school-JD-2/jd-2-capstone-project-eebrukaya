package io.upschool.mapper;

import io.upschool.dto.FlightCrewMemberDTO;
import io.upschool.entities.FlightCrewMember;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface FlightCrewMemberMapper {

    FlightCrewMemberMapper INSTANCE = Mappers.getMapper(FlightCrewMemberMapper.class);

    FlightCrewMemberDTO to(FlightCrewMember flightCrewMember);

    FlightCrewMember from(FlightCrewMemberDTO flightCrewMemberDTO);
}
