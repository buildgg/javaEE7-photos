package ua.home.myphotos.ejb.repository.mock;

import javax.enterprise.context.ApplicationScoped;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.util.Optional;

import static ua.home.myphotos.ejb.repository.mock.InMemoryDataBase.PROFILE;

/**
 * Created by vov on 07.08.2017.
 */
@ApplicationScoped
public class ProfileRepositoryInvocationHandler implements InvocationHandler{
    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        if ("findByUid".equals(method.getName())){
            String uid = String.valueOf(args[0]);
            if ("richard-hendricks".equals(uid)) {
                return Optional.of(PROFILE);
            } else {
                return Optional.empty();
            }
        }
        throw new UnsupportedOperationException(String.format("Method %s hasn't implemented yet", method));
    }
}
