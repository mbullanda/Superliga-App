package pl.michal.SuperligaApp.web.rest;

import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import pl.michal.SuperligaApp.mapper.PlayerToPlayerResponseMapper;
import pl.michal.SuperligaApp.model.Player;
import pl.michal.SuperligaApp.service.ClubService;
import pl.michal.SuperligaApp.service.PlayerService;
import pl.michal.SuperligaApp.web.rest.dto.PlayerResponse;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/players")
@AllArgsConstructor
public class PlayerController {

    private final PlayerService playerService;
    private final PlayerToPlayerResponseMapper playerMapper;
    private final ClubService clubService;

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<PlayerResponse> getAllPlayers(){
        return playerService.getAllPlayers()
                .stream()
                .map(playerMapper::toResponse)
                .collect(Collectors.toList());
    }

    @GetMapping("/player/{id}")
    @ResponseStatus(HttpStatus.OK)
    public PlayerResponse getById(@PathVariable Long id){
        return playerMapper.toResponse(playerService.getPlayerById(id));
    }

}
