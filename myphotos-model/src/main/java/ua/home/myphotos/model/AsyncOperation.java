package ua.home.myphotos.model;

/**
 * Created by vov on 12.07.2017.
 */
public interface AsyncOperation<T> {
    void getTimeOutInMillis();

    void onSuccess(T result);

    void onFailed(Throwable throwable);

}
