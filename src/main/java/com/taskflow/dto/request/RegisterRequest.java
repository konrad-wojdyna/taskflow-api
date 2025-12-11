package com.taskflow.dto.request;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

public record RegisterRequest(

        @NotBlank(message = "Email is required")
        @Email(message = "Provide correct email format")
        @Pattern(regexp = "^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.(com|pl|org|net)$", message =
        "Email must end with .com, .pl, .org or .net")
        String email,

        @NotBlank(message = "Password is required")
        @Size(min = 8, message = "Password must be min 8 characters")
        @Pattern(regexp = ".*[0-9].*", message = "Password must contain at least one digit")
        String password,

        @NotBlank(message = "Name is required")
        @Size(min = 2, max = 50, message = "Name must be between 2 and 50 characters")
        @Pattern( regexp = "^[^0-9].*$", message = "Name cannot start with a digit")
        String name) {

        public RegisterRequest{
                if(email != null){
                        email = email.trim();
                }

                if(password != null){
                        password = password.trim();
                }

                if(name != null){
                        name = name.trim();
                }
        }

}
