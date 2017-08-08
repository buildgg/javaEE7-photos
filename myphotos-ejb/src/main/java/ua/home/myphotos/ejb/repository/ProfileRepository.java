package ua.home.myphotos.ejb.repository;

import ua.home.myphotos.model.domain.Profile;

import java.util.List;
import java.util.Optional;

/**
 * Created by vov on 07.08.2017.
 */
public interface ProfileRepository extends EntityRepository<Profile, Long> {
    Optional<Profile> findByUid(String uid);

    Optional<Profile> findByEmail(String email);

    void updateRating();

    List<String> findUids(List<String> uids);

}
