package ua.home.myphotos.ejb.repository.mock;

import ua.home.myphotos.ejb.repository.AccessTokenRepository;
import ua.home.myphotos.ejb.repository.PhotoRepository;
import ua.home.myphotos.ejb.repository.ProfileRepository;
import ua.home.myphotos.model.domain.AccessToken;

import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.context.Dependent;
import javax.enterprise.inject.Produces;
import javax.inject.Inject;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

/**
 * Created by vov on 07.08.2017.
 */
@Dependent
public class InMemoryRepositoryFactory {

    @Inject
    private ProfileRepositoryInvocationHandler profileRepositoryInvocationHandler;
    @Inject
    private PhotoRepositoryInvocationHandler photoRepositoryInvocationHandler;



    @Produces
    public ProfileRepository getProfileRepository(){
        return (ProfileRepository)Proxy.newProxyInstance(getClass().getClassLoader(),
                new Class[]{ProfileRepository.class}, profileRepositoryInvocationHandler);
    }
    @Produces
    public PhotoRepository getPhotoRepository(){
        return (PhotoRepository)Proxy.newProxyInstance(getClass().getClassLoader(),
                new Class[]{PhotoRepository.class}, photoRepositoryInvocationHandler);
    }
    @Produces
    public AccessTokenRepository getAccessTokenRepository(){
       return (AccessTokenRepository) Proxy.newProxyInstance(
               getClass().getClassLoader(),
               new Class[]{AccessTokenRepository.class},
               new InvocationHandler() {
                   @Override
                   public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
                       throw new UnsupportedOperationException("Not implemented yet");
                   }
               });
    }
}
