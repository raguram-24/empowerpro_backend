package com.backend.empowerpro.utils;

import com.backend.empowerpro.auth.entity.Employee;
import com.backend.empowerpro.dto.employee.EmployeeCreationDto;
import com.backend.empowerpro.dto.employee.EmployeeDto;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "spring")
public interface EmployeeMapper {
    EmployeeDto toEmployeeDto(Employee employee);

    Employee toEmployee(EmployeeCreationDto employeeCreationDto);

    void updateEmployeeFromDto(EmployeeCreationDto employeeCreationDto, @MappingTarget Employee employee);
}
