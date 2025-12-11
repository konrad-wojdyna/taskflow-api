package com.taskflow.controller;


import com.taskflow.dto.request.LoginRequest;
import com.taskflow.dto.request.RegisterRequest;
import com.taskflow.dto.response.UserResponse;
import com.taskflow.service.UserService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

    private final UserService userService;

    public AuthController(UserService userService) {
        this.userService = userService;
    }

    /**
     * Register new user
     *
     * POST /api/auth/register
     *
     * @param request Registration data from client
     *
     * @return 201 Created + UserReponse
     */
    @PostMapping("/register")
    public ResponseEntity<UserResponse> register(@Valid @RequestBody RegisterRequest request){
        UserResponse response = userService.register(request);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @PostMapping("/login")
    public ResponseEntity<UserResponse> login(@Valid @RequestBody LoginRequest request){
        UserResponse response = userService.login(request);
        return ResponseEntity.ok(response);
    }
}
