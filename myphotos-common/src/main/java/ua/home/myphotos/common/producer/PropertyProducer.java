package ua.home.myphotos.common.producer;

import ua.home.myphotos.common.annotation.cdi.Property;
import ua.home.myphotos.exception.ConfigException;

import javax.enterprise.context.Dependent;
import javax.enterprise.inject.Produces;
import javax.enterprise.inject.spi.InjectionPoint;
import javax.inject.Inject;

/**
 * Created by vov on 17.07.2017.
 */
@Dependent
public class PropertyProducer {

    @Inject
    private ApplicationPropertiesStorage aps;

    @Produces
    @Property
    public String resolveStringPropertyValue(InjectionPoint ip) {
        return resolvePropertyValue(ip);
    }
    @Produces
    @Property
    public int resolveIntPropertyValue(InjectionPoint ip) {
        return Integer.parseInt(resolvePropertyValue(ip));
    }
    @Produces
    @Property
    public boolean resolveBooleanPropertyValue(InjectionPoint ip){
        return Boolean.parseBoolean(resolvePropertyValue(ip));
    }


    private String resolvePropertyValue(InjectionPoint ip){
        String className = ip.getMember().getDeclaringClass().getName();
        String memberName = ip.getMember().getName();
        Property property = ip.getAnnotated().getAnnotation(Property.class);
        return resolvePropertyValue(className, memberName,property);

    }
    private String resolvePropertyValue(String className,
                                        String memberName,
                                        Property property){
       String propertyName = getPropertyName(className, memberName, property);
       String propertyValue = aps.getApplicationProperties().getProperty(propertyName);
       if (propertyValue == null){
           throw new ConfigException(
                   String.format("Can't resolve property: '%s' for injection to %s.%s",
                           propertyName, className, memberName));
       }else {
           return propertyValue;
       }

    }

    private String getPropertyName(String className,
                                   String memberName,
                                   Property property){
        String value = property.value();
        if("".equals(value)){
            return String.format("%s.%s", className, memberName);
        }else {
            return value;
        }

    }

}
