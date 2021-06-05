package pl.michal.SuperligaApp.web.rest.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CreateClubRequest {

    @NotBlank(message = "Name cannot be blank")
    private String name;



}
