package pl.michal.SuperligaApp.exception;

public class MissingClubException extends RuntimeException{
    public MissingClubException(Long id) {
        super("Club with id " + id + " does not exists!");
    }
}
