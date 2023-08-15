package io.upschool.dto;

import lombok.Data;
import org.antlr.v4.runtime.misc.NotNull;

import java.math.BigDecimal;

@Data
public class PurchaseRequestDTO {
    private String creditCardNumber;
    private String ticketNumber;
    private String passengerFullName;
    private Long userId;
}
