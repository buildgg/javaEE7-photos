package ua.home.myphotos.ejb.service.bean;

import ua.home.myphotos.common.annotation.cdi.Property;
import ua.home.myphotos.ejb.repository.ProfileRepository;
import ua.home.myphotos.exception.ObjectNotFoundException;
import ua.home.myphotos.model.AsyncOperation;
import ua.home.myphotos.model.ImageResource;
import ua.home.myphotos.model.domain.Profile;
import ua.home.myphotos.service.ProfileService;

import javax.ejb.*;
import javax.inject.Inject;
import java.util.Optional;

/**
 * Created by vov on 08.08.2017.
 */
@Stateless
@Local(ProfileService.class)
@LocalBean
public class ProfileServiceBean implements ProfileService {

    @Inject
    @Property("myphotos.profile.avatar.placeholder.url")
    private String avatarPlaceHolderUrl;

    @Inject
    private ProfileRepository profileRepository;

    @Override
    public Profile findById(Long id) throws ObjectNotFoundException {
        Optional<Profile> profileOptional = profileRepository.findById(id);
        if (!profileOptional.isPresent()){
            throw new ObjectNotFoundException(String.format("Profile not found by id: %s", id));
        }
        return profileOptional.get();
    }

    @Override
    public Profile findByUid(String uid) throws ObjectNotFoundException {
        Optional<Profile> profileRepositoryByUid = profileRepository.findByUid(uid);
        if (!profileRepositoryByUid.isPresent()){
            throw new ObjectNotFoundException(String.format("Profile not found by uid: %s", uid));
        }
        return profileRepositoryByUid.get();
    }

    @Override
    public Optional<Profile> findByEmail(String email) {
        Optional<Profile> profile = profileRepository.findByEmail(email);
        if (!profile.isPresent()){
            throw new ObjectNotFoundException(String.format("Profile not found by uid: %s", email));
        }
        return profile;
    }

    @Override
    public void signUp(Profile profile, boolean uploadProfileAvatar) {

    }

    @Override
    public void translitSocialProfile(Profile profile) {

    }

    @Override
    public void update(Profile profile) {
        profileRepository.update(profile);
    }

    @Override
    @Asynchronous
    @TransactionAttribute(TransactionAttributeType.REQUIRES_NEW)
    public void uploadNewAvatar(Profile currentProfile, ImageResource imageResource, AsyncOperation<Profile> asyncOperation) {
        try {
            uploadNewAvatar(currentProfile, imageResource);
            asyncOperation.onSuccess(currentProfile);

        }catch (Throwable throwable){
            setAvatarPlaceHolder(currentProfile);
            asyncOperation.onFailed(throwable);
        }
    }
    public void setAvatarPlaceHolder(Long profileId) {
        setAvatarPlaceHolder(profileRepository.findById(profileId).get());
    }

    private void setAvatarPlaceHolder(Profile currentProfile) {
        if (currentProfile.getAvatarUrl() == null){
            currentProfile.setAvatarUrl(avatarPlaceHolderUrl);
            profileRepository.update(currentProfile);
        }
    }

    public void uploadNewAvatar(Profile currentProfile, ImageResource imageResource){

    }
}
