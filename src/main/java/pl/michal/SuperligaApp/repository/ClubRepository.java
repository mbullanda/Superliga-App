package pl.michal.SuperligaApp.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pl.michal.SuperligaApp.model.Club;

@Repository
public interface ClubRepository extends JpaRepository<Club, Long> {
}
