package io.upschool.dto;

import io.upschool.entities.*;
import io.upschool.enums.FlightStatus;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.util.Date;
import java.util.List;
import java.util.Set;

@Data
public class FlightDTO {

    @Getter
    @Setter
    public static class Search implements Serializable {
        private String rota;
        private Long airportId;
        private String airlineCode;
        private String gateNumber;
        private String flightDate;
    }

    @Getter
    @Setter
    public static class Create implements Serializable {
        private Long id;
        private String rota;
        private Airport airport;
        private Airline airline;
        private Airplane airplane;
        public FlightStatus status;
        private String gateNumber;
        private List<Ticket> tickets;
        private Set<FlightCrewMember> crewMembers;
    }

    @Getter
    @Setter
    public static class Response implements Serializable {
        private Long id;
        private String rota;
        private Airport airport;
        private Airline airline;
        private Airplane airplane;
        public  FlightStatus status;
        private String gateNumber;
        private Date flightTime;
        private Set<FlightCrewMember> crewMembers;
    }

}
