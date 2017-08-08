package ua.home.myphotos.ejb.repository;

import ua.home.myphotos.model.domain.Photo;
import ua.home.myphotos.model.domain.Profile;

import java.util.List;

/**
 * Created by vov on 07.08.2017.
 */
public interface PhotoRepository extends EntityRepository<Photo, Long> {
    List<Photo> findProfilePhotosLatestFirst(Long profileId, int offset, int limit);

    int countProfilePhotos(Long profileId);

    List<Photo> findAllOrderByViewsDesc(int offset, int limit);

    List<Photo> findAllOrderByAutorRatingDesc(int offset, int limit);

    long countAll();
}
