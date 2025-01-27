package com.backend.empowerpro.service.impl;

import com.backend.empowerpro.auth.entity.Employee;
import com.backend.empowerpro.auth.repository.EmployeeRepository;
import com.backend.empowerpro.dto.payroll.PayRollDto;
import com.backend.empowerpro.dto.payroll.PayRollReq;
import com.backend.empowerpro.dto.payroll.PayRollViewDto;
import com.backend.empowerpro.entity.*;
import com.backend.empowerpro.exception.MedicalClaimNotFound;
import com.backend.empowerpro.exception.ResourceNotFoundException;
import com.backend.empowerpro.repository.BankRepo;
import com.backend.empowerpro.repository.PayrollRepo;
import com.backend.empowerpro.repository.TaxRepo;
import com.backend.empowerpro.service.InquiryService;
import com.backend.empowerpro.service.PayrollService;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public  class  PayrollServiceImp implements PayrollService {
    private final PayrollRepo payrollRepo;
    private final EmployeeRepository employeeRepository;
    private final BankRepo bankRepo;
    private final TaxRepo taxRepo;
    private final ExchangeRateService exchangeRateService;
    private static final Logger logger = LoggerFactory.getLogger(PayrollServiceImp.class);
    @Override
    public PayRoll getPayroll(PayRollReq payRollReq) {
        try{
            double salary = 300;
            double total;
            double taxAmount;
            double epfAmount;
            logger.info(String.valueOf(salary));
            var employee  = employeeRepository.findByUsername(payRollReq.getUsername());
            if(employee.isPresent()){
                logger.info("Employee Fetched");
                Employee fetchedEmployee = employee.get();
                logger.info(String.valueOf(fetchedEmployee));
                Tax tax = taxRepo.findLatestById();
                logger.info(String.valueOf(tax));
                BankDetails bankDetails = bankRepo.findByEmployeeId(fetchedEmployee.getId());
                logger.info(String.valueOf(bankDetails));
                if(Objects.equals(fetchedEmployee.getRole().toString(), "HR")){
                    salary = 350;
                } else if (Objects.equals(fetchedEmployee.getRole().toString(), "Finance")) {
                    salary = 250;
                    logger.info(String.valueOf(salary));
                }else if (Objects.equals(fetchedEmployee.getRole().toString(), "TeamLead")) {
                    salary = 700;
                }else if (Objects.equals(fetchedEmployee.getRole().toString(), "Employee")) {
                    salary = 500;
                    logger.info(String.valueOf(salary));
                }

                double toLkr = exchangeRateService.getUsdToLkrRate();
                logger.info(String.valueOf(toLkr));
                logger.info("Fetched USD to LKR rate: " + toLkr);
                if (toLkr == 0.0) {
                    throw new RuntimeException("Invalid exchange rate fetched");
                }

                double lkrSalary = toLkr * salary;


                taxAmount = lkrSalary * tax.getTaxRate()/ 100;
                epfAmount = lkrSalary * tax.getEpf()/100;
                double medicalLkr = toLkr * tax.getMedicalAllowance();
                double otherLkr = toLkr * tax.getOtherAllowance();
                total = lkrSalary + medicalLkr + otherLkr - taxAmount - epfAmount;
                BigDecimal roundedSalary = new BigDecimal(lkrSalary).setScale(2, RoundingMode.HALF_UP);
                BigDecimal roundedTax = new BigDecimal(taxAmount).setScale(2, RoundingMode.HALF_UP);
                BigDecimal roundedEpf = new BigDecimal(epfAmount).setScale(2, RoundingMode.HALF_UP);
                BigDecimal roundedMedical = new BigDecimal(medicalLkr).setScale(2, RoundingMode.HALF_UP);
                BigDecimal roundedOther = new BigDecimal(otherLkr).setScale(2, RoundingMode.HALF_UP);
                BigDecimal roundedTotal = new BigDecimal(total).setScale(2, RoundingMode.HALF_UP);
                PayRoll createdPayRoll = new PayRoll(
                        fetchedEmployee.getId(),
                        fetchedEmployee.getFirstName(),
                        fetchedEmployee.getAddress(),
                        fetchedEmployee.getWorkTitle(),
                        roundedSalary,
                        roundedMedical,
                        roundedOther,
                        roundedTax,
                        roundedEpf,
                        roundedTotal,
                        bankDetails.getBankName(),
                        bankDetails.getAcc_no(),
                        bankDetails.getBranch(),
                        Status.PENDING // This should match the Status enum
                );
                payrollRepo.save(createdPayRoll);


                return createdPayRoll;

            }else {
                throw new UsernameNotFoundException("Not found");
            }


        }catch (Exception e){
            throw new RuntimeException("Error Occured ");
        }
    }

    @Override
    public List<PayRollViewDto> getAllPayRoll() {

            try{
                List<PayRoll> allPayRolls = payrollRepo.findAll();
                List<PayRollViewDto> result = new java.util.ArrayList<>();
                logger.info("All Complaints has been Fetched Successfully");
                for(PayRoll p : allPayRolls){
                    var employee = employeeRepository.findById(p.getEmployeeId());
                    Employee employee1 = employee.get();
                    String name = employee1.getFirstName()+employee1.getLastName();

                    BigDecimal sum1 = p.getOtherAllowance().add(p.getMedicalAllowance());
                    BigDecimal grossSalary = p.getSalary().add(sum1);

                    BigDecimal tax = p.getIncomeAfterEpf().add(p.getIncomeAfterTax());

                    BigDecimal totalSalary = grossSalary.subtract(tax);

                    PayRollViewDto pw = new PayRollViewDto();
                    pw.setId(p.getId());
                    pw.setName(name);
                    pw.setPostion(p.getJobTitle());
                    pw.setGrossPay(grossSalary);
                    pw.setTax(tax);
                    pw.setTotalSalary(totalSalary);
                    pw.setStatus(p.getStatus().toString());

                    result.add(pw);
                }
                return result;
            } catch (Exception e) {
                logger.error("An unexpected error occurred while fetching Events: {}", e.getMessage(), e);
                throw new RuntimeException("An unexpected error occurred while fetching Events", e);
            }

        }

    @Override
    public PayRoll getOnePayRoll(Long employeeId) {
        if (employeeId == null || employeeId <= 0) {
            logger.warn("Invalid employee ID provided: {}", employeeId);
            throw new IllegalArgumentException("Employee ID must be a positive number");
        }

        try {
           List<PayRoll> results = payrollRepo.findByEmployeeId(employeeId);
           logger.info(results.toString());
            return results.get(results.size()-1);
        } catch (ResourceNotFoundException e) {
            // No further logging needed as it’s already handled above
            throw e;
        } catch (Exception e) {
            logger.error("An unexpected error occurred while fetching payroll details for employee ID: {}", employeeId, e);
            throw new RuntimeException("An unexpected error occurred while fetching payroll details", e);
        }
    }


    @Override
    public PayRoll updateStatus(Long id) {
        Logger logger = LoggerFactory.getLogger(getClass());  // Initialize logger

        try {
            // Fetch the PayRoll by ID from the repository
            Optional<PayRoll> result = payrollRepo.findById(id);

            // Check if PayRoll exists
            if (result.isPresent()) {
                PayRoll payRoll = result.get();
                logger.info("Updating status for PayRoll with ID: {}", id);

                // Update the status to APPROVED
                payRoll.setStatus(Status.APPROVED);

                // Save the updated PayRoll object back to the repository
                payRoll = payrollRepo.save(payRoll);

                logger.info("PayRoll with ID: {} updated successfully", id);
                return payRoll;
            } else {
                // If the PayRoll does not exist, log and throw an exception
                logger.error("PayRoll not found with ID: {}", id);
                throw new ResourceNotFoundException("PayRoll not found with ID: " + id);
            }
        } catch (ResourceNotFoundException e) {
            // Handle specific exception for resource not found
            logger.error("Error: {}", e.getMessage(), e);
            throw e;  // Re-throw the custom exception
        } catch (Exception e) {
            // Log unexpected errors
            logger.error("An unexpected error occurred while updating PayRoll status", e);
            throw new RuntimeException("An error occurred while updating the status", e);
        }
    }




}
