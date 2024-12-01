package com.backend.empowerpro.auth.service;

import com.backend.empowerpro.auth.entity.Employee;
import com.backend.empowerpro.auth.repository.EmployeeRepository;
import com.backend.empowerpro.auth.utils.AuthResponse;
import com.backend.empowerpro.auth.utils.LoginRequest;
import com.backend.empowerpro.auth.utils.RegisterRequest;
import com.backend.empowerpro.entity.LeaveBalance;
import com.backend.empowerpro.exception.CustomAuthenticationException;
import com.backend.empowerpro.exception.FileExistsException;
import com.backend.empowerpro.repository.LeaveBalanceRepo;
import com.backend.empowerpro.service.EmployeeService;
import com.backend.empowerpro.service.FileService;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.*;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

@Service
@RequiredArgsConstructor
public class AuthService {
    private final FileService fileService;
    private final PasswordEncoder passwordEncoder;
    private final EmployeeRepository employeeRepository;
    private final JwtService jwtService;
    private final RefreshTokenService refreshTokenService;
    private final AuthenticationManager authenticationManager;
    private final LeaveBalanceRepo leaveBalanceRepo;
    @Value("${project.employeeProfile}")
    private String path;
    @Value("${base.url}")
    private String baseUrl;
    private static final Logger logger = LoggerFactory.getLogger(EmployeeService.class);


//    public AuthResponse register(RegisterRequest registerRequest, MultipartFile file) throws IOException {
//        if( Files.exists(Paths.get(path+ File.separator + file.getOriginalFilename()))){
//            throw new FileExistsException("File Already Exists, Please submit another file");
//        }
//        String uploadedFileName = fileService.uploadFile(path,file);
//        //Set the value of field 'poster' as filename
//        registerRequest.setProfile(uploadedFileName);
//        var employee = Employee.builder()
//                .firstName(registerRequest.getFirstName())
//                .lastName(registerRequest.getLastName())
//                .address(registerRequest.getAddress())
//                .email(registerRequest.getEmail())
//                .phoneNumber(registerRequest.getPhoneNumber())
//                .workTitle(registerRequest.getWorkTitle())
//                .role(registerRequest.getRole())
//                .username(registerRequest.getUsername())
//                .password(passwordEncoder.encode(registerRequest.getPassword()))
//                .profile(uploadedFileName)
//                .build();
//
//        Employee savedEmployee = employeeRepository.save(employee);
//        String profileUrl = baseUrl + "/file/profile/" + savedEmployee.getProfile();
//        var accessToken = jwtService.generateToken(savedEmployee);
//        var refreshToken = refreshTokenService.createRefreshToken(savedEmployee.getUsername());
//
//        return AuthResponse.builder()
//                .accessToken(accessToken)
//                .refreshToken(refreshToken.getRefreshToken())
//                .userId(savedEmployee.getId())
//                .WorkTitle(savedEmployee.getWorkTitle())
//                .firstName(savedEmployee.getFirstName())
//                .lastName(savedEmployee.getLastName())
//                .username(savedEmployee.getUsername())
//                .role(savedEmployee.getRole())
//                .profileUrl(profileUrl)
//                .build();
//    }

    public AuthResponse register(RegisterRequest registerRequest) {
        var employee = Employee.builder()
                .firstName(registerRequest.getFirstName())
                .lastName(registerRequest.getLastName())
                .address(registerRequest.getAddress())
                .email(registerRequest.getEmail())
                .phoneNumber(registerRequest.getPhoneNumber())
                .workTitle(registerRequest.getWorkTitle())
                .role(registerRequest.getRole())
                .username(registerRequest.getUsername())
                .password(passwordEncoder.encode(registerRequest.getPassword()))
                .build();

        Employee savedEmployee = employeeRepository.save(employee);

        LeaveBalance defaultLeaveBalance = LeaveBalance.builder()
                .employee(employee)
                .approvedLeaves(0)
                .rejectedLeaves(0)
                .totalAvailableLeaves(30)
                .build();

        leaveBalanceRepo.save(defaultLeaveBalance);


        String profileUrl = path + "achchu.jpeg";    // Set a default profile URL if needed

        var accessToken = jwtService.generateToken(savedEmployee);
//        var refreshToken = refreshTokenService.createRefreshToken(savedEmployee.getUsername());

        return AuthResponse.builder()
                .accessToken(accessToken)
//                .refreshToken(refreshToken.getRefreshToken())
                .userId(savedEmployee.getId())
                .WorkTitle(savedEmployee.getWorkTitle())
                .firstName(savedEmployee.getFirstName())
                .lastName(savedEmployee.getLastName())
                .username(savedEmployee.getUsername())
                .role(savedEmployee.getRole())
                .build();
    }


    public AuthResponse login(LoginRequest loginRequest) {
        try {
            // Authenticate user
            authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(
                            loginRequest.getUsername(),
                            loginRequest.getPassword()
                    )
            );

            // Fetch employee details
            var employee = employeeRepository.findByUsername(loginRequest.getUsername())
                    .orElseThrow(() -> new UsernameNotFoundException("Employee not found"));

            // Generate profile URL and access token
            String profileUrl = baseUrl + "/file/profile/" + employee.getProfile();
            var accessToken = jwtService.generateToken(employee);

            // Build and return the response
            return AuthResponse.builder()
                    .accessToken(accessToken)
                    .userId(employee.getId())
                    .WorkTitle(employee.getWorkTitle())
                    .firstName(employee.getFirstName())
                    .lastName(employee.getLastName())
                    .username(employee.getUsername())
                    .role(employee.getRole())
                    .profileUrl(profileUrl)
                    .build();

        } catch (BadCredentialsException ex) {
            throw new CustomAuthenticationException("Invalid username or password", ex);
        } catch (UsernameNotFoundException ex) {
            throw new CustomAuthenticationException("Employee not found", ex);
        } catch (DisabledException ex) {
            throw new CustomAuthenticationException("User account is disabled", ex);
        } catch (LockedException ex) {
            throw new CustomAuthenticationException("User account is locked", ex);
        } catch (Exception ex) {
            throw new CustomAuthenticationException("An unexpected error occurred during authentication", ex);
        }




    }
}