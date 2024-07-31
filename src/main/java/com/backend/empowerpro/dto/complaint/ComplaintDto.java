package com.backend.empowerpro.dto.complaint;


import com.backend.empowerpro.entity.Employee;
import jakarta.persistence.Column;
import jakarta.persistence.FetchType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.util.Date;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class ComplaintDto {
    private Long id;
    private String status;
    private String about;
    private Date date;
    private String sender;
    private String assignedTo;
    private String description;
    private String reply;
    private String filesToUpload;
}