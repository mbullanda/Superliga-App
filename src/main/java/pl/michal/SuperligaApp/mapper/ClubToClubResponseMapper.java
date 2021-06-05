package pl.michal.SuperligaApp.mapper;

import org.springframework.stereotype.Component;
import pl.michal.SuperligaApp.model.Club;
import pl.michal.SuperligaApp.web.rest.dto.ClubResponse;

import java.util.stream.Collectors;

@Component
public class ClubToClubResponseMapper {

    public ClubResponse toResponse(Club club){
        return ClubResponse.builder()
                .id(club.getId())
                .name(club.getName())
                .points(club.getPoints())
                .goalsScored(club.getGoalsScored())
                .goalsConceded(club.getGoalsConceded())
                .playersId(club.getPlayers().stream()
                        .map(player -> player.getId())
                        .collect(Collectors.toSet()))
                .build();
    }
}
