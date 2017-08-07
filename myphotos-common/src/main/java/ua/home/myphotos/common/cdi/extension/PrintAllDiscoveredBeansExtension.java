package ua.home.myphotos.common.cdi.extension;

import javax.enterprise.event.Observes;
import javax.enterprise.inject.spi.BeanManager;
import javax.enterprise.inject.spi.Extension;
import javax.enterprise.inject.spi.ProcessAnnotatedType;
import java.lang.annotation.Annotation;
import java.util.Set;
import java.util.logging.Logger;

/**
 * Created by vov on 18.07.2017.
 */
public class PrintAllDiscoveredBeansExtension implements Extension {
    private final Logger LOGGER = Logger.getLogger(getClass().getName());

    <T> void processAnnotatedType(@Observes ProcessAnnotatedType<T> processAnnotatedType, BeanManager bm){
        String className = processAnnotatedType.getAnnotatedType().getJavaClass().getName();

        if (className.startsWith("ua.home")){
            Set<Annotation> annotations = processAnnotatedType.getAnnotatedType().getAnnotations();
            LOGGER.info(String.format("Discovered bean of %s with annotations: %s", className, annotations));
        }
    }


}
