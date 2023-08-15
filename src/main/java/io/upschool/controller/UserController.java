package io.upschool.controller;

import io.swagger.annotations.ApiOperation;
import io.upschool.dto.UserDTO;
import io.upschool.entities.Ticket;
import io.upschool.entities.User;
import io.upschool.response.ApiResponse;
import io.upschool.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/ticketing/parametre/user")
public class UserController {
    private final UserService userService;

    @GetMapping
    @ApiOperation("User kayıtlarını döndürür.")
    public ResponseEntity<ApiResponse<List<User>>> getAllUsers() {
        ApiResponse<List<User>> response = userService.getAllUsers();
        return ResponseEntity.ok(response);
    }

    @GetMapping("/{id}")
    @ApiOperation("Id bilgisi verilen user kaydını döndürür.")
    public ResponseEntity<ApiResponse<User>> getUser(@PathVariable Long id) {
        ApiResponse<User> response = userService.getUser(id);
        return ResponseEntity.ok(response);
    }

    @PostMapping
    @ApiOperation("Yeni user kaydeder.")
    public ResponseEntity<ApiResponse<Void>> addUser(@RequestBody UserDTO userDTO) {
        ApiResponse<Void> response = userService.addUser(userDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @GetMapping("/tickets")
    @ApiOperation("User'a ait tüm biletleri listeler.")
    public ApiResponse<List<Ticket>> getUserTickets(@RequestParam Long userId) {
        List<Ticket> userTickets = userService.getUserTickets(userId);
        return new ApiResponse<>(true, userTickets, "Kullanıcıya ait tüm biletler listelendi.");
    }



}
