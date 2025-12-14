package com.taskflow.dto.response;

public record AuthResponse(
        String token,
        UserResponse user
) {}
