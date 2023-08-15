package io.upschool.entities;

import jakarta.persistence.*;

@Entity
public class Airport {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(name = "id", nullable = false)
    private Long id;

    @Column(name = "name")
    private String name;

    @Column(name = "iata_code", unique = true)
    private String iataCode;

    @Column(name = "contact_no")
    private String contactNo;

    @Column(name = "city")
    private String city;

    @Column(name = "passenger_capacity")
    private Long passengerCapacity;


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getIataCode() {
        return iataCode;
    }

    public void setIataCode(String iataCode) {
        this.iataCode = iataCode;
    }

    public String getContactNo() {
        return contactNo;
    }

    public void setContactNo(String contactNo) {
        this.contactNo = contactNo;
    }

    public Long getPassengerCapacity() {
        return passengerCapacity;
    }

    public void setPassengerCapacity(Long passengerCapacity) {
        this.passengerCapacity = passengerCapacity;
    }

}
