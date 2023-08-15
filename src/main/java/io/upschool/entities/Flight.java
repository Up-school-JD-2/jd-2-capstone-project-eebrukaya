package io.upschool.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import io.upschool.enums.FlightStatus;
import jakarta.persistence.*;

import java.util.*;

@Entity
public class Flight {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(name = "id", nullable = false)
    private Long id;

    @Column(name = "rota")
    private String rota;

    @ManyToOne
    @JoinColumn(name = "airport_id")
    private Airport airport;

    @ManyToOne
    @JoinColumn(name = "airline_code")
    private Airline airline;

    public Airline getAirline() {
        return airline;
    }

    @ManyToOne
    @JoinColumn(name = "airplane_id")
    private Airplane airplane;

    @Enumerated(EnumType.STRING)
    @Column(name = "status")
    public FlightStatus status;

    @Column(name = "gate_number")
    private String gateNumber;

    @OneToMany(mappedBy = "flight")
    private List<Ticket> tickets = new ArrayList<>();

    @JsonIgnore
    @Column(name = "flight_time", nullable = false)
    @Temporal(TemporalType.TIMESTAMP)
    private Date flightTime;
    @ManyToMany
    @JoinTable(name = "flight_crew",
            joinColumns = @JoinColumn(name = "flight_id"),
            inverseJoinColumns = @JoinColumn(name = "crew_id"))
    private Set<FlightCrewMember> crewMembers = new HashSet<>();

    public void setAirline(Airline airline) {
        this.airline = airline;
    }

    public Airport getAirport() {
        return airport;
    }

    public void setAirport(Airport airport) {
        this.airport = airport;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getRota() {
        return rota;
    }

    public void setRota(String rota) {
        this.rota = rota;
    }

    public FlightStatus getStatus() {
        return status;
    }

    public void setStatus(FlightStatus status) {
        this.status = status;
    }

    public String getGateNumber() {
        return gateNumber;
    }

    public void setGateNumber(String gateNumber) {
        this.gateNumber = gateNumber;
    }

    public Airplane getAirplane() {
        return airplane;
    }

    public void setAirplane(Airplane airplane) {
        this.airplane = airplane;
    }

    public List<Ticket> getTickets() {
        return tickets;
    }

    public void setTickets(List<Ticket> tickets) {
        this.tickets = tickets;
    }

    public Set<FlightCrewMember> getCrewMembers() {
        return crewMembers;
    }

    public void setCrewMembers(Set<FlightCrewMember> crewMembers) {
        this.crewMembers = crewMembers;
    }

    public Date getFlightTime() {
        return flightTime;
    }

    public void setFlightTime(Date flightTime) {
        this.flightTime = flightTime;
    }
}
