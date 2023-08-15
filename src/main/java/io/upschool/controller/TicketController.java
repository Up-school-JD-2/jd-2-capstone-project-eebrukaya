package io.upschool.controller;

import io.swagger.annotations.ApiOperation;
import io.upschool.dto.PurchaseRequestDTO;
import io.upschool.dto.TicketDTO;
import io.upschool.entities.Ticket;
import io.upschool.response.ApiResponse;
import io.upschool.service.PaymentService;
import io.upschool.service.TicketService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/ticketing/parametre/ticket")
public class TicketController {

    private final TicketService ticketService;

    private final PaymentService paymentService;

    @GetMapping
    @ApiOperation("Bilet kayıtlarını döndürür.")
    public ResponseEntity<ApiResponse<List<Ticket>>> getAllTickets() {
        ApiResponse<List<Ticket>> response = ticketService.getAllTickets();
        return ResponseEntity.ok(response);
    }

    @GetMapping("/{code}")
    @ApiOperation("Bilet numarası verilen havayolu kaydını döndürür.")
    public ResponseEntity<ApiResponse<Ticket>> getTicket(@PathVariable String code) {
        ApiResponse<Ticket> response = ticketService.getTicket(code);
        return ResponseEntity.ok(response);
    }

    @PostMapping
    @ApiOperation("Yeni bilet kaydeder.")
    public ResponseEntity<ApiResponse<Void>> saveTicket(@RequestBody TicketDTO ticketDTO) {
        ApiResponse<Void> response = ticketService.saveTicket(ticketDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @PostMapping("/purchase")
    @ApiOperation("Bilet satın alma işlemi yapar.")
    public ResponseEntity<ApiResponse<Void>> purchaseTicket(@RequestBody PurchaseRequestDTO request) {
        String maskedCardNumber = paymentService.maskCreditCardNumber(request.getCreditCardNumber());
        request.setCreditCardNumber(maskedCardNumber);
        ApiResponse<Void> response = paymentService.purchaseTicket(request);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @PostMapping("/cancel/{ticketNumber}")
    @ApiOperation("Bilet satın alma işlemini iptal eder.")
    public ResponseEntity<ApiResponse<Void>> cancelTicketPurchase(@RequestParam String ticketNumber) {
        ApiResponse<Void> response = paymentService.cancelTicketPurchase(ticketNumber);
        return ResponseEntity.ok(response);
    }

}
