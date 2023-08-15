package io.upschool.service;

import io.upschool.dto.TicketDTO;
import io.upschool.entities.Flight;
import io.upschool.entities.Ticket;
import io.upschool.globalexception.CustomExcepiton;
import io.upschool.repository.TicketRepository;
import io.upschool.response.ApiResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class TicketService {

    private final TicketRepository ticketRepository;

    private final FlightService flightService;


    @Transactional
    public ApiResponse<List<Ticket>> getAllTickets() {
        List<Ticket> tickets = ticketRepository.findAll();
        return new ApiResponse<>(true, tickets, "Biletler başarıyla alındı.");
    }


    @Transactional
    public ApiResponse<Ticket> getTicket(String number) {
        Optional<Ticket> ticket = Optional.of(ticketRepository.getReferenceById(number));

       return ticket.map(value -> new ApiResponse<>(true, value, "Bilet başarıyla bulundu."))
                .orElseThrow(() -> new CustomExcepiton.TicketNotFoundException("Bilet bulunamadı."));
    }

    @Transactional
    public ApiResponse<Void> saveTicket(TicketDTO ticketDTO) {
        if (ticketDTO != null && ticketDTO.getFlightId() != null) {
            Flight flight = flightService.getFlight(ticketDTO.getFlightId()).getData();
            if (flight != null) {
                Ticket ticket = new Ticket();
                ticket.setPrice(ticketDTO.getPrice());
                ticket.setFlight(flight);
                try {
                    ticketRepository.save(ticket);
                    return new ApiResponse<>(true, null, "Ticket başarıyla eklendi.");
                } catch (Exception e) {
                    throw new CustomExcepiton.RecordCreationException("Ticket eklenirken bir hata oluştué " + e.getMessage());
                }
            } else {
                throw new CustomExcepiton.FlightNotFoundException("Uçuş bulunamadı! ");
            }
        } else {
            throw new CustomExcepiton.IllegalArgumentException("Flight id boş!");
        }

    }


    @Transactional
    public List<Ticket> getUserTickets(Long userId) {
        List<Ticket> ticketByUser_id = ticketRepository.findTicketByUser_Id(userId);
        return ticketByUser_id;
    }
}
