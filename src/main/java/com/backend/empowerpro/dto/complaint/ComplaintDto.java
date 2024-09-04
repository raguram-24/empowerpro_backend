package com.backend.empowerpro.dto.complaint;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

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