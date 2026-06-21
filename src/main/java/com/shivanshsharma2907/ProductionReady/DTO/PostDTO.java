package com.shivanshsharma2907.ProductionReady.DTO;

import jakarta.persistence.Column;
import jakarta.validation.constraints.NotBlank;
import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PostDTO {

    private Long ID;

    @NotBlank(message = "Title can not be blank")
    private String title;

    @NotBlank(message = "Description can not be blank")
    private String description;


}
