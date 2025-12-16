package com.taskflow.controller;

import com.taskflow.dto.response.UserResponse;
import com.taskflow.model.User;
import com.taskflow.service.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;

@RestController
@RequestMapping("/api/users")
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/{id}")
    public ResponseEntity<UserResponse> getUserById(@PathVariable Long id){
            User user = userService.getUserById(id);
            return ResponseEntity.ok(UserResponse.fromUser(user));
    }

    @GetMapping("/me")
    public ResponseEntity<UserResponse> getCurrentUser(@AuthenticationPrincipal UserDetails userDetails){
        String email = userDetails.getUsername();

        User user = userService.getUserByEmail(email);

        return ResponseEntity.ok(UserResponse.fromUser(user));

    }
}
