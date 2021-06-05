package pl.michal.SuperligaApp.web.rest;

import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.*;
import pl.michal.SuperligaApp.exception.MissingPlayerException;
import pl.michal.SuperligaApp.mapper.CreatePlayerRequestToPlayerMapper;
import pl.michal.SuperligaApp.mapper.PlayerToPlayerResponseMapper;
import pl.michal.SuperligaApp.model.Player;
import pl.michal.SuperligaApp.service.ClubService;
import pl.michal.SuperligaApp.service.PlayerService;
import pl.michal.SuperligaApp.web.rest.dto.AddGoalsAndAssistsRequest;
import pl.michal.SuperligaApp.web.rest.dto.CreatePlayerRequest;
import pl.michal.SuperligaApp.web.rest.dto.PlayerResponse;

import javax.validation.Valid;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/players")
@AllArgsConstructor
public class PlayerController {

    private final PlayerService playerService;
    private final PlayerToPlayerResponseMapper playerMapper;
    private final ClubService clubService;
    private final CreatePlayerRequestToPlayerMapper createPlayerRequestToPlayerMapper;

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

    @PostMapping
    public ResponseEntity<?> createPlayer(@RequestBody @Valid CreatePlayerRequest request, BindingResult bindingResult){
        if (bindingResult.hasFieldErrors()){
            Map<String, String> errors = bindingResult.getFieldErrors()
                    .stream()
                    .collect(Collectors.toMap(FieldError::getField, FieldError::getDefaultMessage));
            return ResponseEntity.badRequest().body(errors);
        }

        Player playerToCreate = createPlayerRequestToPlayerMapper.toPlayer(request);
        playerToCreate.setClub(clubService.findById(request.getClubId()));
        playerToCreate.getClub().getPlayers().add(playerToCreate);
        return new ResponseEntity<>(playerMapper.toResponse(playerService.createPlayer(playerToCreate)), HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> addGoalsAndAssistsToPlayer(@PathVariable Long id, @RequestBody @Valid AddGoalsAndAssistsRequest request){
        if(!playerService.existsById(id)){
            return ResponseEntity.notFound().build();
        }
        Player playerById = playerService.getPlayerById(id);
        playerById.addGoals(request.getGoals());
        playerById.addAssists(request.getAssists());
        playerService.savePLayer(playerById);
        return ResponseEntity.noContent().build();
    }

}
