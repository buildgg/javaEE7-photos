package ua.home.myphotos.common.producer;

import ua.home.myphotos.common.annotation.cdi.PropertiesSource;

import javax.enterprise.context.Dependent;
import javax.enterprise.inject.Produces;
import javax.enterprise.inject.spi.InjectionPoint;
import java.util.Properties;

/**
 * Created by vov on 18.07.2017.
 */
@Dependent
public class PropertiesSourceProducer extends AbstractPropertiesLoader {
    @Produces
    @PropertiesSource("")
    private Properties loadProperties(InjectionPoint ip){
        Properties properties = new Properties();
        PropertiesSource propertiesSource = ip.getAnnotated().getAnnotation(PropertiesSource.class);
        loadProperties(properties, propertiesSource.value());
        return properties;
    }
}
