package com.backend.empowerpro.utils;

import com.backend.empowerpro.dto.employee.EmployeeCreationDto;
import com.backend.empowerpro.dto.employee.EmployeeDto;
import com.backend.empowerpro.dto.employee.EmployeeUpdateRequest;
import com.backend.empowerpro.entity.Employee;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface EmployeeMapper {
    EmployeeDto toEmployeeDto(Employee employee);

    Employee toEmployee(EmployeeCreationDto employeeCreationDto);

    Employee toEmployee(EmployeeUpdateRequest employeeUpdateRequest);
}
