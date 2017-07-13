package ua.home.myphotos.exception;

/**
 * Created by vov on 12.07.2017.
 */
public abstract class BusinessException extends ApplicationException {
    public BusinessException(String message) {
       super(message, null , true, false);
    }
}
