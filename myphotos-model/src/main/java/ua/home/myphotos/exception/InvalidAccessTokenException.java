package ua.home.myphotos.exception;

/**
 * Created by vov on 12.07.2017.
 */
public class InvalidAccessTokenException extends BusinessException {
    public InvalidAccessTokenException(String message) {
        super(message);
    }
}
