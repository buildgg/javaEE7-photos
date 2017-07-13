package ua.home.myphotos.exception;

/**
 * Created by vov on 12.07.2017.
 */
public class ConfigException extends ApplicationException {
    public ConfigException(String message) {
        super(message);
    }

    public ConfigException(String message, Throwable cause) {
        super(message, cause);
    }
}
