package pl.michal.SuperligaApp.web.rest.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class PlayerResponse {

    private Long id;
    private String name;
    private String lastName;
    private int number;
    private int goals;
    private int assists;
    private long clubId;
}
