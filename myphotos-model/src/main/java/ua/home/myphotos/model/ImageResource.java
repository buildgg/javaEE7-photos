package ua.home.myphotos.model;

import java.nio.file.Path;

/**
 * Created by vov on 12.07.2017.
 */
public interface ImageResource extends AutoCloseable {
    Path getTempPath();

    @Override
    void close();
}
