package com.backend.empowerpro.utils;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder

public class ReplyRequest {
    private String reply;
    private String status;
    // Getters and setters
}
