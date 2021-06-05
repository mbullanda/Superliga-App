package pl.michal.SuperligaApp.mapper;

import org.springframework.stereotype.Component;
import pl.michal.SuperligaApp.exception.MissingClubException;
import pl.michal.SuperligaApp.model.Player;
import pl.michal.SuperligaApp.repository.ClubRepository;
import pl.michal.SuperligaApp.web.rest.dto.CreatePlayerRequest;

@Component
public class CreatePlayerRequestToPlayerMapper {

    private ClubRepository clubRepository;

    public Player toPlayer(CreatePlayerRequest request){
        return Player.builder()
                .name(request.getName())
                .lastName(request.getLastName())
                .number(request.getNumber())
                .goals(0)
                .assists(0)
//                .club(clubRepository.findById(request.getClubId()).orElseThrow( () -> new MissingClubException(request.getClubId())))
                .build();
    }
}
