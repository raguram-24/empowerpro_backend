package com.backend.empowerpro.dto.bank;

import com.backend.empowerpro.auth.entity.Employee;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class BankCreationDto {
    private Long id;
    private String username;
    private String bankName;
    private String acc_no;
    private String branch;
}
