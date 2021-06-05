package pl.michal.SuperligaApp.web.rest.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Set;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ClubResponse {

    private Long id;
    private String name;
    private int points;
    private int goalsScored;
    private int goalsConceded;
    private Set<Long> playersId;
}
