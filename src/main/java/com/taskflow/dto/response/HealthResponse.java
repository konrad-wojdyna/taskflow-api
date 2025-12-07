package com.taskflow.dto.response;

import java.time.LocalDateTime;

public record HealthResponse(String status, String message, LocalDateTime timestamp) {}
