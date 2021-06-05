package pl.michal.SuperligaApp.web.rest.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CreatePlayerRequest {

    @NotBlank(message = "Name cannot be blank")
    private String name;
    @NotBlank(message = "Last name cannot be blank")
    private String lastName;
    @Min(value = 1, message = "Number cannot be lower than 1")
    @Max(value = 99, message = "Number must be lower than 100")
    private int number;
    private long clubId;

}
