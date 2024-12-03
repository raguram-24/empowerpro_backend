package com.backend.empowerpro.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.sql.Time;
import java.time.LocalDate;
import java.util.Date;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "attendance")
public class Attendance {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "attendance_id", nullable = false)
    private Long attendanceId;

    @Column(name = "user_id", nullable = false)
    private Long userId;

    @Column(name = "date", updatable = false)
    private LocalDate date;

    @Column(name = "check_in", updatable = false)
    private Time checkIn;

    @Column(name = "check_out", updatable = true)
    private Time checkOut;

    @ElementCollection
    @CollectionTable(name = "break_time", joinColumns = @JoinColumn(name = "attendance_id"))
    @Column(name = "break_time" ,nullable = true,updatable = true)
    private List<Time> breakTime;

    @ElementCollection
    @CollectionTable(name = "continue_time", joinColumns = @JoinColumn(name = "attendance_id"))
    @Column(name = "continue_time" ,nullable = true,updatable = true)
    private List<Time> continueTime;

}
