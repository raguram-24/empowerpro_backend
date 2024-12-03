package com.backend.empowerpro.dto.attendance;

import jakarta.persistence.Column;
import lombok.*;

import java.sql.Time;
import java.util.Date;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Data
public class CreateAttendanceDto {

    private Long userId;


    private Time checkIn;



    private Date date;

}
