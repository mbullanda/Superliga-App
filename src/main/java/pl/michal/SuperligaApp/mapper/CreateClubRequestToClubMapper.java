package pl.michal.SuperligaApp.mapper;

import org.springframework.stereotype.Component;
import pl.michal.SuperligaApp.model.Club;
import pl.michal.SuperligaApp.web.rest.dto.CreateClubRequest;

@Component
public class CreateClubRequestToClubMapper {

    public Club toClub(CreateClubRequest request){
        return Club.builder()
                .name(request.getName())
                .points(0)
                .goalsScored(0)
                .goalsConceded(0)
                .build();
    }
}
