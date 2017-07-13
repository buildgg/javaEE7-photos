package ua.home.myphotos.exception;

/**
 * Created by vov on 12.07.2017.
 */
public class RetrieveSocialDataFailedException extends ApplicationException {
    public RetrieveSocialDataFailedException(String message) {
        super(message);
    }

    public RetrieveSocialDataFailedException(String message, Throwable cause) {
        super(message, cause);
    }
}
