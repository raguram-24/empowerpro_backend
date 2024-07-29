package com.backend.empowerpro.entity;

import jakarta.persistence.*;
import lombok.Data;
import org.springframework.boot.autoconfigure.web.WebProperties;

import java.util.Date;

@Data
@Entity
@Table(name = "complaints")

public class Complaint {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    @Column(name = "status")
    private String status;

    @Column(name = "about")
    private String about;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "date")
    private Date date;

    @Column(name = "sender")
    private String sender;

    @Column(name = "assigned_to")
    private String assignedTo;

    @Column(name = "description")
    private String description;

    @Column(name = "reply")
    private String reply;

    @Column(name = "files_to_upload")
    private String filesToUpload;

}

