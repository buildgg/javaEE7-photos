package ua.home.myphotos.service;

import ua.home.myphotos.exception.ObjectNotFoundException;
import ua.home.myphotos.model.AsyncOperation;
import ua.home.myphotos.model.ImageResource;
import ua.home.myphotos.model.domain.Profile;

import java.util.Optional;

/**
 * Created by vov on 12.07.2017.
 */
public interface ProfileService {
    Profile findById(Long id) throws ObjectNotFoundException;

    Profile findByUid(String uid) throws ObjectNotFoundException;

    Optional<Profile> findByEmail(String email);

    void signUp(Profile profile, boolean uploadProfileAvatar);

    void translitSocialProfile(Profile profile);

    void update(Profile profile);

    void uploadNewAvatar(Profile currentProfile,
                         ImageResource imageResource,
                         AsyncOperation<Profile> asyncOperation);


}
