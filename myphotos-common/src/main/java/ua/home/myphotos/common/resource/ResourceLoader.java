package ua.home.myphotos.common.resource;

import javax.enterprise.inject.Vetoed;
import java.io.IOException;
import java.io.InputStream;

/**
 * Created by vov on 17.07.2017.
 */
@Vetoed
public interface ResourceLoader {

    boolean isSupport(String resourceName);

    InputStream getInputStream(String resourceName) throws IOException;
}
