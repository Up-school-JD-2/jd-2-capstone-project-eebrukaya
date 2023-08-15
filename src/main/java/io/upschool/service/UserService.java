package io.upschool.service;

import io.upschool.dto.UserDTO;
import io.upschool.entities.Ticket;
import io.upschool.entities.User;
import io.upschool.globalexception.CustomExcepiton;
import io.upschool.repository.UserRepository;
import io.upschool.response.ApiResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepository;

    private final TicketService ticketService;

    @Transactional
    public ApiResponse<List<User>> getAllUsers() {
        List<User> users = userRepository.findAll();
        return new ApiResponse<>(true, users, "Userlar başarıyla alındı.");
    }

    @Transactional
    public ApiResponse<User> getUser(Long id) {
        Optional<User> user = Optional.of(userRepository.getReferenceById(id));
        return user.map(value -> new ApiResponse<>(true, value, "User başarıyla bulundu.")).orElseGet(() -> new ApiResponse<>(false, null, "User bulunamadı."));
    }

    @Transactional
    public ApiResponse<Void> addUser(UserDTO userDTO) {

        if (userDTO != null && userDTO.getUserName() != null && userDTO.getPassword() != null) {
            try {
                User newUser = new User();
                newUser.setPassword(maskPassword(userDTO.getPassword()));
                newUser.setUserName(userDTO.getUserName());
                userRepository.save(newUser);
                return new ApiResponse<>(true, null, "Kullanıcı başarıyla eklendi.");

            } catch (Exception e) {
                throw new CustomExcepiton.RecordCreationException("User kaydederken bir hata meydana geldi!");
            }
        } else {
            throw new CustomExcepiton.IllegalArgumentException("Tüm alanları doldurduğunuzdan emin olun!");
        }
    }

    public List<Ticket> getUserTickets(Long userId) {
        return ticketService.getUserTickets(userId);
    }

    public String maskPassword(String password) {
        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        return passwordEncoder.encode(password);
    }
}
