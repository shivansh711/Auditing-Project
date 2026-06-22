package com.shivanshsharma2907.ProductionReady;

import com.shivanshsharma2907.ProductionReady.DTO.EmployeesDTO;
import com.shivanshsharma2907.ProductionReady.clients.EmployeeClient;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@SpringBootTest
class  ProductionReadyApplicationTests {

	@Autowired
	private EmployeeClient employeeClient;

	@Test
	void contextLoads() {
	}

	@Test
	void getTestAllEmployees(){
		List<EmployeesDTO> employeesList = employeeClient.getAllEmployees();
		System.out.println(employeesList);
	}

	@Test
	void getEmployeeByID(){
		EmployeesDTO employeesDTO = employeeClient.getEmployeeByID(20555L);
		System.out.println(employeesDTO);
	}

	@Test
	void createNewEmployeeTest(){
        new EmployeesDTO();
        EmployeesDTO employeesDTO = EmployeesDTO
                .builder()
				.ID(null)
				.name("Bheem")
				.email("Bheem.sharma@adp.com")
				.age(25)
				.dateOfJoining(LocalDate.of(2020,12,1))
				.active(true)
				.build();
		EmployeesDTO saveEmployee = employeeClient.createNewEmployee(employeesDTO);
		System.out.println(saveEmployee);
	}

}
