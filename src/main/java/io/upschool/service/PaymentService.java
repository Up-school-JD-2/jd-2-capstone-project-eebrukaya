package io.upschool.service;

import io.upschool.dto.PurchaseRequestDTO;
import io.upschool.entities.Ticket;
import io.upschool.entities.User;
import io.upschool.globalexception.CustomExcepiton;
import io.upschool.repository.TicketRepository;
import io.upschool.repository.UserRepository;
import io.upschool.response.ApiResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class PaymentService {

    private final TicketRepository ticketRepository;
    private final UserRepository userRepository;

    private final UserService userService;

    @Transactional
    public ApiResponse<Void> purchaseTicket(PurchaseRequestDTO request) {
        try {
            if (request != null && request.getTicketNumber() != null
                    && request.getCreditCardNumber() != null && request.getUserId() != null
                    && request.getPassengerFullName() != null) {

                String maskedCardNumber = maskCreditCardNumber(request.getCreditCardNumber());

                Ticket ticket = ticketRepository.getReferenceById(request.getTicketNumber());
                User user = userService.getUser(request.getUserId()).getData();

                ticket.setCreditCardNumber(maskedCardNumber);
                ticket.setActive(false);
                ticket.setPassengerFullName(request.getPassengerFullName());
                ticket.setUser(user);
                ticketRepository.save(ticket);

                user.getTickets().add(ticket);
                userRepository.save(user);
                return new ApiResponse<>(true, null, "Bilet başarıyla satın alındı.");
            } else {
                throw new CustomExcepiton.IllegalArgumentException("Tüm bilgileri eksiksiz girin ");
            }
        } catch (Exception e) {
            throw new CustomExcepiton.PaymentFailedException("Ödeme yapılırken bir hata oluştu " + e.getMessage());
        }


    }

    @Transactional
    public ApiResponse<Void> cancelTicketPurchase(String ticketNumber) {
        if (ticketNumber.isBlank()) {
            throw new CustomExcepiton.IllegalArgumentException("Bilet numarası boş.");
        }

        Ticket ticket = ticketRepository.getReferenceById(ticketNumber);

        if (ticket == null) {
            throw new CustomExcepiton.TicketNotFoundException("Belirtilen bilet numarası ile eşleşen bilet bulunamadı.");
        }

        if (ticket.isActive()) {
            throw new CustomExcepiton.IllegalArgumentException("Bu bilet zaten iptal edilmiştir.");
        }
        try {
            ticket.setActive(true);
            ticket.setCreditCardNumber(null);
            ticket.setPassengerFullName(null);
            ticket.setUser(null);
            ticketRepository.save(ticket);

            return new ApiResponse<>(true, null, "Bilet satın alma işlemi iptal edildi.");
        } catch (Exception e) {
            throw new CustomExcepiton.RecordCreationException("Bilet iptal edilirken bir hata meydana geldi " + e.getMessage());
        }


    }

    @Transactional
    public String maskCreditCardNumber(String cardNumber) {
        String cleanedCardNumber = cardNumber.replaceAll("[^0-9]", "");
        String maskedCardNumber = cleanedCardNumber.substring(0, 6) +
                "******" +
                cleanedCardNumber.substring(cleanedCardNumber.length() - 4);

        return maskedCardNumber;
    }


}
