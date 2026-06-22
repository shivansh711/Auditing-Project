package com.shivanshsharma2907.ProductionReady.DTO;


import jakarta.validation.constraints.*;
import lombok.*;

import java.time.LocalDate;


@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Builder
public class EmployeesDTO {

    public Long ID;

    @NotBlank(message = "Name of the Employee can not be blank")
    @Size(min = 3, max = 10, message = "Number of characters in name should be in the range:[3,10]")
    public String name;

    @NotBlank(message = "Email of the employee can not be blank")
    @Email(message = "Email should be Valid")
    public String email;

    @NotNull(message = "Age of the Employee can not be null")
    @Max(value = 80, message = "Age of the Employee can not be greater than 80")
    @Min(value = 18, message = "Age of the Employee can not be less than 18")
    public Integer age;

    @PastOrPresent(message = "Date of joining field in Employee can not be in the future")
    public LocalDate dateOfJoining;

    @AssertTrue(message = "Employee should be Active")
    public Boolean active;

}
