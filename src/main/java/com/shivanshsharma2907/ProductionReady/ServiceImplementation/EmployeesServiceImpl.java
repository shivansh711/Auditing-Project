package com.shivanshsharma2907.ProductionReady.ServiceImplementation;

import com.shivanshsharma2907.ProductionReady.Advice.ApiResponse;
import com.shivanshsharma2907.ProductionReady.DTO.EmployeesDTO;
import com.shivanshsharma2907.ProductionReady.Exception.ResourceNotFoundException;
import com.shivanshsharma2907.ProductionReady.clients.EmployeeClient;
import lombok.RequiredArgsConstructor;
import org.apache.catalina.startup.CertificateCreateRule;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClient;

import java.util.List;

@Service
@RequiredArgsConstructor
public class EmployeesServiceImpl implements EmployeeClient {

    Logger log = LoggerFactory.getLogger(EmployeesServiceImpl.class);

    private final RestClient restClient;

    @Override
    public List<EmployeesDTO> getAllEmployees(){
        log.trace("Trying to retrieve the employees in getAllEmployees");
        try{
            log.info("Attempting to call the restClient Method in getAllEmployee");
            ApiResponse<List<EmployeesDTO>> employeesDTOLists =   restClient
                    .get()
                    .uri("presentationlayer/employees")
                    .retrieve()
                    .onStatus(HttpStatusCode::is4xxClientError,(req,res) ->{
                        log.error(new String(res.getBody().readAllBytes()));   // this is recomended
                        throw new ResourceNotFoundException("could not create the employee");
                    })
                    .body(new ParameterizedTypeReference<>() {
                    });
            log.debug("Successfully retrieved the employees in getAllEmployees");
            log.trace("Retrieved employees list in getAllEmployees : {}",employeesDTOLists.getData());
            return employeesDTOLists.getData();

        } catch (Exception e) {
            log.error("Exception occurred in getAllEmployees");
            throw new RuntimeException(e);
        }

    }

    @Override
    public EmployeesDTO getEmployeeByID(Long ID){
        log.trace("Trying to get Employee by ID: {}",ID);
        try {
            log.info("Attempting to call the restClient Method in getEmployeeByID");
            ApiResponse<EmployeesDTO> employee = restClient
                    .get()
                    .uri("presentationlayer/employees/{ID}", ID)
                    .retrieve()
                    .onStatus(HttpStatusCode::is4xxClientError,(req,res) ->{
                      log.error(new String(res.getBody().readAllBytes()));
                        throw new ResourceNotFoundException("could not create the employee");
                    })
                    .body(new ParameterizedTypeReference<>() {
                    });

            log.debug("Successfully retrieved the employee in getEmployeeByID");
            log.trace("Retrieved employee in getEmployeeByID : {}",employee.getData());
            return employee.getData();
        } catch (Exception e) {
            log.error("Exception occurred in getEmployeesByID");
            throw new RuntimeException(e);
        }

    }

    @Override
    public EmployeesDTO createNewEmployee(EmployeesDTO inputEmployee){
        log.trace("Trying to create new Employee: {}",inputEmployee);
        try {
            log.info("Attempting to call the restClient Method in createNewEmployee");
            ResponseEntity<ApiResponse<EmployeesDTO>> newEmployee = restClient
                    .post()
                    .uri("presentationlayer/newEmployee")
                    .body(inputEmployee)
                    .retrieve()
                    .onStatus(HttpStatusCode::is4xxClientError,(req,res) ->{
                        log.debug("4xxClientError error occurred during the employee creations");
                        log.error(new String(res.getBody().readAllBytes()));
                        throw new ResourceNotFoundException("could not create the employee");
                    })
                    .toEntity(new ParameterizedTypeReference<>() {
                    });
            log.debug("Successfully created new  employee in createNewEmployee");
            log.trace("Retrieved employee in createNewEmployee : {}",newEmployee.getBody().getData());
            return newEmployee.getBody().getData();

        } catch (Exception e) {
            log.error("Exception occurred in createNewEmployee");
            throw new RuntimeException(e);
        }
    }

}
