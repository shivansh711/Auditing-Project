package com.shivanshsharma2907.ProductionReady.clients;

import com.shivanshsharma2907.ProductionReady.DTO.EmployeesDTO;

import java.util.List;

public interface EmployeeClient {

    List<EmployeesDTO> getAllEmployees();
    EmployeesDTO getEmployeeByID(Long ID);
    EmployeesDTO createNewEmployee(EmployeesDTO employeesDTO);

}
