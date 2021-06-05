package pl.michal.SuperligaApp.web.rest.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Min;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class AddMatchRequest {
    @Min(value = 0, message = "Goals cannot be lower than 0")
    int goalsScored;
    @Min(value = 0, message = "Goals cannot be lower than 0")
    int goalsConceded;
}
