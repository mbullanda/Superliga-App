package pl.michal.SuperligaApp.exception;

public class MissingPlayerException extends RuntimeException {
    public MissingPlayerException(Long id) {
        super("Player with id " + id + " does not exists!");
    }
}
