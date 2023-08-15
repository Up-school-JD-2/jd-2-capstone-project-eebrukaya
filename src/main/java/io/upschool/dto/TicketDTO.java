package io.upschool.dto;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class TicketDTO {
    private Long flightId;
    private BigDecimal price;
}
