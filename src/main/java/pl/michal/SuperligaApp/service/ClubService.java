package pl.michal.SuperligaApp.service;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import pl.michal.SuperligaApp.exception.MissingClubException;
import pl.michal.SuperligaApp.model.Club;
import pl.michal.SuperligaApp.repository.ClubRepository;
import pl.michal.SuperligaApp.web.rest.dto.ClubResponse;

import java.util.List;

@Service
@AllArgsConstructor
public class ClubService {

    private final ClubRepository clubRepository;

    public Club findById(Long id){
        return clubRepository.findById(id).orElseThrow( () -> new MissingClubException(id));
    }

    public List<Club> getAllClubs() {
        return clubRepository.findAll();
    }

    public Club createClub(Club clubToCreate) {
        return clubRepository.save(clubToCreate);
    }
    public boolean existsById(Long id){
        return clubRepository.existsById(id);
    }

    public Club save(Club clubById) {
        return clubRepository.save(clubById);
    }
}
