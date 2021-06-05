package pl.michal.SuperligaApp.mapper;

import org.springframework.stereotype.Component;
import pl.michal.SuperligaApp.model.Player;
import pl.michal.SuperligaApp.web.rest.dto.PlayerResponse;

@Component
public class PlayerToPlayerResponseMapper {

    public PlayerResponse toResponse(Player player){
        return PlayerResponse.builder()
                .id(player.getId())
                .name(player.getName())
                .lastName(player.getLastName())
                .number(player.getNumber())
                .goals(player.getGoals())
                .assists(player.getAssists())
                .clubId(player.getClub().getId())
                .build();
    }
}
