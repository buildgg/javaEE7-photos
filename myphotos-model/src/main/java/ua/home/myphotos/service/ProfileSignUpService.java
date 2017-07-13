package ua.home.myphotos.service;

import ua.home.myphotos.exception.ObjectNotFoundException;
import ua.home.myphotos.model.domain.Profile;

/**
 * Created by vov on 12.07.2017.
 */
public interface ProfileSignUpService {
    void createSignUpProfile(Profile profile);
    Profile getCurrentProfile() throws ObjectNotFoundException;

    void completeSignUp();

    void cancel();


}
