package com.backend.empowerpro.dto.events;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;

import java.time.LocalDate;


@Data
public class EventCreationDto {
    @NotBlank(message = "Name should not be blank")
    private String name;
    @NotBlank(message = "Name should not be blank")
    private String reason;
    @NotBlank(message = "Name should not be blank")
    private String location;
    @NotBlank(message = "Name should not be blank")
    private LocalDate date;
    @NotBlank(message = "Name should not be blank")
    private String time;
    @NotBlank(message = "Name should not be blank")
    private String estimatedCost;
    @NotBlank(message = "Name should not be blank")
    private String image;
    private Long createdBy;
}
