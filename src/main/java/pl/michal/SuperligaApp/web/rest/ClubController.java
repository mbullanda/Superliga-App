package pl.michal.SuperligaApp.web.rest;

import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.*;
import pl.michal.SuperligaApp.mapper.ClubToClubResponseMapper;
import pl.michal.SuperligaApp.mapper.CreateClubRequestToClubMapper;
import pl.michal.SuperligaApp.mapper.PlayerToPlayerResponseMapper;
import pl.michal.SuperligaApp.model.Club;
import pl.michal.SuperligaApp.model.Player;
import pl.michal.SuperligaApp.service.ClubService;
import pl.michal.SuperligaApp.service.PlayerService;
import pl.michal.SuperligaApp.web.rest.dto.ClubResponse;
import pl.michal.SuperligaApp.web.rest.dto.CreateClubRequest;
import pl.michal.SuperligaApp.web.rest.dto.CreatePlayerRequest;
import pl.michal.SuperligaApp.web.rest.dto.PlayerResponse;

import javax.validation.Valid;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/clubs")
@AllArgsConstructor
public class ClubController {

    private final ClubService clubService;
    private final PlayerService playerService;
    private final PlayerToPlayerResponseMapper playerMapper;
    private final ClubToClubResponseMapper clubMapper;
    private final CreateClubRequestToClubMapper createClubRequestToClubMapper;

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

    @PostMapping
    public ResponseEntity<?> createClub(@RequestBody @Valid CreateClubRequest request, BindingResult bindingResult){
        if (bindingResult.hasFieldErrors()){
            Map<String, String> errors = bindingResult.getFieldErrors()
                    .stream()
                    .collect(Collectors.toMap(FieldError::getField, FieldError::getDefaultMessage));
            return ResponseEntity.badRequest().body(errors);
        }

        Club clubToCreate = createClubRequestToClubMapper.toClub(request);
        return new ResponseEntity<>(clubMapper.toResponse(clubService.createClub(clubToCreate)), HttpStatus.CREATED);
    }
}
