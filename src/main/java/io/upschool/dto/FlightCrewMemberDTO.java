package io.upschool.dto;

import lombok.Data;
import org.antlr.v4.runtime.misc.NotNull;
import org.hibernate.annotations.NotFound;

@Data
public class FlightCrewMemberDTO {

    @NotNull
    private String name;
    @NotNull
    private String lastName;
    @NotNull
    private String identityNumber;
    @NotNull
    private String mission;
    @NotNull
    private String gender;
    @NotNull
    private String airlineCode;
    @NotNull
    private int age;
    @NotNull
    private String phone;
}
