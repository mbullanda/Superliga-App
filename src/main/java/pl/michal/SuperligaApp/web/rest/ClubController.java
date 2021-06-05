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
import pl.michal.SuperligaApp.web.rest.dto.*;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.Comparator;
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

    //mappery przenieść do serwisu

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

    @GetMapping("/rank")
    @ResponseStatus(HttpStatus.OK)
    public List<String> getClubRank(){
        List<Club> clubs = clubService.getAllClubs().stream()
                .sorted(Comparator.comparing(Club::getPoints, Comparator.reverseOrder()))
                .collect(Collectors.toList());
        List<String> clubRank= new ArrayList<>();
        for (int i = 0; i < clubs.size(); i++){
            StringBuilder sb = new StringBuilder();
            Club club = clubs.get(i);
            sb.append(i+1)
                    .append(". ")
                    .append(club.getName())
                    .append(" ")
                    .append(club.getPoints())
                    .append(" ")
                    .append(club.getGoalsScored())
                    .append("-")
                    .append(club.getGoalsConceded());
            clubRank.add(sb.toString());
        }
        return clubRank;
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

    @PutMapping({"/{id}"})
    public ResponseEntity<?> addMatch(@RequestBody @Valid AddMatchRequest request, @PathVariable Long id){
        //przenieść metodę do serwisu
        if (!clubService.existsById(id)){
            return ResponseEntity.notFound().build();
        }
        Club clubById = clubService.findById(id);
        clubById.updateGoalsAndAddPoints(request.getGoalsScored(), request.getGoalsConceded());
        clubService.save(clubById);
        return ResponseEntity.noContent().build();
    }

}
