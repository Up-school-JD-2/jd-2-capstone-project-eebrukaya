package io.upschool.repository;

import io.upschool.dto.FlightDTO;
import io.upschool.entities.Flight;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

@Repository
public interface FlightRepository extends JpaRepository<Flight,Long> {
    List<Flight> findByAirport_Id(@PathVariable Long airportId);
    List<Flight> findByAirline_Code(@PathVariable String airlineCode);

    /*@Query(value = " select fl from Flight as fl  " +
            " left join fl.airline " +
            " left join fl.airport " +
            " left join fl.crewMembers " +
            " where fl.airline.code = :airlineCode " )
    List<FlightDTO.Response> findByAirline_Code(@PathVariable String airlineCode);*/
}
