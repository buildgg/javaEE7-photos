package ua.home.myphotos.ejb.service.bean;

import ua.home.myphotos.ejb.repository.PhotoRepository;
import ua.home.myphotos.ejb.repository.ProfileRepository;
import ua.home.myphotos.exception.ObjectNotFoundException;
import ua.home.myphotos.exception.ValidationException;
import ua.home.myphotos.model.*;
import ua.home.myphotos.model.domain.Photo;
import ua.home.myphotos.model.domain.Profile;
import ua.home.myphotos.service.PhotoService;

import javax.annotation.Resource;
import javax.ejb.*;
import javax.inject.Inject;
import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

/**
 * Created by vov on 08.08.2017.
 */
@Stateless
@Local(PhotoService.class)
@LocalBean
public class PhotoServiceBean implements PhotoService {

    @Inject
    private PhotoRepository photoRepository;

    @Inject
    private ProfileRepository profileRepository;

    @Resource
    private SessionContext sessionContext;

    @Override
    public List<Photo> findProfilePhotos(Long profileId, Pageable pageable) {
        return photoRepository.findProfilePhotosLatestFirst(profileId,pageable.getOffset(),pageable.getLimit());
    }

    @Override
    public List<Photo> findPopularPhotos(SortMode sortMode, Pageable pageable) {
        switch (sortMode){
            case POPULAR_AUTHOR:
                return photoRepository.findAllOrderByAutorRatingDesc(pageable.getOffset(), pageable.getLimit());
            case POPULAR_PHOTO:
                return photoRepository.findAllOrderByViewsDesc(pageable.getOffset(), pageable.getLimit());
            default: throw new ValidationException("Unsupported sort mode: " + sortMode);
        }
    }

    @Override
    public long countAllPhotos() {
        return photoRepository.countAll();
    }

    @Override
    public String viewLargePhoto(Long photoId) throws ObjectNotFoundException {
        Photo photo = getPhoto(photoId);
        photo.setViews(photo.getViews() + 1);
        photoRepository.update(photo);

        return photo.getLargeUrl();
    }

    private Photo getPhoto(Long photoId) {
        Optional<Photo> photoOptional = photoRepository.findById(photoId);
        if (!photoOptional.isPresent()){
            throw new ObjectNotFoundException(String.format("Photo not found by id: %s", photoId));
        }
        return photoOptional.get();
    }

    @Override
    public OriginalImage downloadOriginalImage(Long photoId) throws ObjectNotFoundException {
        Photo photo = getPhoto(photoId);
        photo.setDownloads(photo.getDownloads() + 1);
        photoRepository.update(photo);

        throw new UnsupportedOperationException("Not implemented yet");
    }

    @Override
    @Transactional
    @TransactionAttribute(TransactionAttributeType.REQUIRES_NEW)
    public void uploadNewPhoto(Profile currentProfile, ImageResource imageResource, AsyncOperation<Photo> asyncOperation) {
        try {
            Photo photo = null;
            asyncOperation.onSuccess(photo);
        }catch (Throwable throwable){
            sessionContext.setRollbackOnly();
            asyncOperation.onFailed(throwable);
        }

    }
}
