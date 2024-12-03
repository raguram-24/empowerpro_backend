package com.backend.empowerpro.dto.attendance;

import lombok.*;

import java.sql.Time;
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Data
public class CheckoutAttendanceDto {
    private Long id;
    private Time checkOut;


}
