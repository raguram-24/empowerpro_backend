package com.backend.empowerpro.entity;

import jakarta.persistence.*;
import lombok.*;

import java.sql.Time;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Data
@Entity
@Table(name = "attendance")
public class    Attendance {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "attendance_id" ,nullable = false   )
    private Long id;

    @Column(name="user_id")
    private Long userId;

    @Column(name = "check_in")
    private Time checkIn;

    @Column(name = "check_out")
    private Time checkOut;

    @Column(name = "date")
    private LocalDate date;

    @ElementCollection
    @CollectionTable(name ="break_time",joinColumns = @JoinColumn(name = "attendance_id"))
    @Column(name = "break_time"   )
    private List<Time> break_time;




}
