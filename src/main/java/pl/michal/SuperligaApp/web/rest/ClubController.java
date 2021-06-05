package pl.michal.SuperligaApp.web.rest;

import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import pl.michal.SuperligaApp.mapper.ClubToClubResponseMapper;
import pl.michal.SuperligaApp.mapper.PlayerToPlayerResponseMapper;
import pl.michal.SuperligaApp.service.ClubService;
import pl.michal.SuperligaApp.service.PlayerService;
import pl.michal.SuperligaApp.web.rest.dto.ClubResponse;
import pl.michal.SuperligaApp.web.rest.dto.PlayerResponse;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/clubs")
@AllArgsConstructor
public class ClubController {

    private final ClubService clubService;
    private final PlayerService playerService;
    private final PlayerToPlayerResponseMapper playerMapper;
    private final ClubToClubResponseMapper clubMapper;

    @GetMapping("/club/{clubId}")
    @ResponseStatus(HttpStatus.OK)
    public List<PlayerResponse> getPlayersByClubId(@PathVariable Long clubId){
        clubService.findById(clubId);
        return playerService.getPlayersByClubId(clubId)
                .stream()
                .map(playerMapper::toResponse)
                .collect(Collectors.toList());
    }

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<ClubResponse> getAllClubs(){
        return clubService.getAllClubs()
                .stream()
                .map(clubMapper::toResponse)
                .collect(Collectors.toList());
    }
}
