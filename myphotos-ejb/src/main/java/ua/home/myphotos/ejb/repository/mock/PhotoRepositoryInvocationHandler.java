package ua.home.myphotos.ejb.repository.mock;

import javax.enterprise.context.ApplicationScoped;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.util.Collections;
import java.util.stream.Collectors;

import static ua.home.myphotos.ejb.repository.mock.InMemoryDataBase.PHOTOS;
import static ua.home.myphotos.ejb.repository.mock.InMemoryDataBase.PROFILE;

/**
 * Created by vov on 07.08.2017.
 */
@ApplicationScoped
public class PhotoRepositoryInvocationHandler implements InvocationHandler {
    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        switch (method.getName()){
            case "findProfilePhotosLatestFirst":
                return findProfilePhotosLatestFirst(args);
            case "findAllOrderByViewsDesc":
            case "findAllOrderByAutorRatingDesc":
                return findAll(args);
            case "countAll":
                return countAll(args);
            default:
                throw new UnsupportedOperationException(String.format("Method %s hasn't implemented yet", method));
        }

    }

    private Object countAll(Object[] args) {
        return (long)PHOTOS.size();
    }

    private Object findAll(Object[] args) {
        int offset = (int) args[0];
        int limit = (int) args[1];
        return PHOTOS.stream().skip(offset).limit(limit).collect(Collectors.toList());
    }


    private Object findProfilePhotosLatestFirst(Object[] args) {
        Long profileId = (Long) args[0];
        int offset = (int) args[1];
        int limit = (int) args[2];

        if (profileId.equals(PROFILE.getId())){
            return PHOTOS.stream().skip(offset).limit(limit).collect(Collectors.toList());
        } else {
            return Collections.emptyList();
        }

    }
}
