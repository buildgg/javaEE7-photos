package ua.home.myphotos.service;

import ua.home.myphotos.exception.ObjectNotFoundException;
import ua.home.myphotos.model.*;
import ua.home.myphotos.model.domain.Photo;
import ua.home.myphotos.model.domain.Profile;

import java.util.List;

/**
 * Created by vov on 12.07.2017.
 */
public interface PhotoService {

    List<Photo> findProfilePhotos(Long profileId, Pageable pageable);

    List<Photo> findPopularPhotos(SortMode sortMode, Pageable pageable);

    long countAllPhotos();

    String viewLargePhoto(Long photoId) throws ObjectNotFoundException;

    OriginalImage downloadOriginalImage(Long photoId) throws ObjectNotFoundException;

    void uploadNewPhoto(Profile currentProfile,
                        ImageResource imageResource,
                        AsyncOperation<Photo> asyncOperation);


}
