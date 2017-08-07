package ua.home.myphotos.common.resource;

import ua.home.myphotos.exception.ConfigException;

import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.inject.Any;
import javax.enterprise.inject.Instance;
import javax.inject.Inject;
import java.io.IOException;
import java.io.InputStream;

/**
 * Created by vov on 17.07.2017.
 */
@ApplicationScoped
public class ResourceLoaderManager {

    @Inject
    @Any
    private Instance<ResourceLoader> resourceLoaders;

    public InputStream getResourceInputStream(String resourceName)throws IOException{
        for (ResourceLoader resourceLoader : resourceLoaders){
            if (resourceLoader.isSupport(resourceName)){
                return resourceLoader.getInputStream(resourceName);
            }
        }
        throw new ConfigException("Can't get inputStream for resource " + resourceName);
    }
}
