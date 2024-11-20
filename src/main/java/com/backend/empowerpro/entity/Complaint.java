package com.backend.empowerpro.entity;

import com.backend.empowerpro.auth.entity.Employee;
import jakarta.persistence.*;
import lombok.Data;

import java.util.Date;

@Data
@Entity
@Table(name = "complaints")

public class Complaint {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "sender_id", nullable = false)
    private Employee sender;

    @Column(name = "status")
    private String status;

    @Column(name = "about")
    private String about;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "date")
    private Date date;

    @Column(name = "assigned_to")
    private String assignedTo;

    @Column(name = "description")
    private String description;

    @Column(name = "reply")
    private String reply;

    @Column(name = "files_to_upload")
    private String filesToUpload;

}

