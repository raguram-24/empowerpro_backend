package com.backend.empowerpro.dto.events;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.time.LocalDate;


@Data
@AllArgsConstructor
public class EventCreationDto {
    @NotBlank(message = "Name should not be blank")
    private String name;
    @NotBlank(message = "reason should not be blank")
    private String reason;
    @NotBlank(message = "location should not be blank")
    private String location;
    @NotNull(message = "date should not be blank")
    private LocalDate date;
    @NotBlank(message = "time should not be blank")
    private String time;
    @NotNull(message = "Cost cannot be empty")
    private Float estimatedCost;
    private String image;
}
