package pl.michal.SuperligaApp.service;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import pl.michal.SuperligaApp.exception.MissingClubException;
import pl.michal.SuperligaApp.model.Club;
import pl.michal.SuperligaApp.repository.ClubRepository;

@Service
@AllArgsConstructor
public class ClubService {

    private final ClubRepository clubRepository;

    public Club findById(Long id){
    return clubRepository.findById(id).orElseThrow( () -> new MissingClubException(id));
    }

}
