package io.upschool.entities;

import jakarta.persistence.*;


@Entity
public class Airline {
    @Id
    @Column(name = "code", nullable = false, unique = true)
    private String code;

    @Column(name = "company_name")
    private String companyName;

    @Column(name = "iata", unique = true)
    private String iata;

    @Column(name = "contact_no")
    private String contactNo;

    @Column(name = "country")
    private String country;

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    public String getIata() {
        return iata;
    }

    public void setIata(String iata) {
        this.iata = iata;
    }

    public String getContactNo() {
        return contactNo;
    }

    public void setContactNo(String contactNo) {
        this.contactNo = contactNo;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }
}
