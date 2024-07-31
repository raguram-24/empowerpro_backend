package com.backend.empowerpro.dto.suppliers;

import com.backend.empowerpro.entity.Employee;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.Date;
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class SuppliersCreationDto {
    private Long id;
    private String supplierName;
    private String contactEmail;
    private String contactPhoneNo;
    private String supplierDescription;

}
