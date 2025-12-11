package com.taskflow.dto.request;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;

public record LoginRequest(

        @NotBlank(message = "Email is required")
        @Email(message = "Provide correct email format")
        String email,

        @NotBlank(message = "Password is required")
        String password) {
}
