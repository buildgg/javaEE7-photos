package ua.home.myphotos.common.config;

import java.util.concurrent.TimeUnit;

/**
 * Created by vov on 07.08.2017.
 */
public class Constants {
    public static final long MAX_UPLOADED_PHOTO_SIZE_IN_BYTE = 10 * 1024 * 1024;

    public static final long DEFAULT_ASYNC_OPERATION_TIMEOUT_IN_MILLIS = TimeUnit.SECONDS.toMillis(30);

}
