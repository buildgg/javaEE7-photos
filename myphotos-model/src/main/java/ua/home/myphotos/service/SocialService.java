package ua.home.myphotos.service;

import ua.home.myphotos.exception.RetrieveSocialDataFailedException;
import ua.home.myphotos.model.domain.Profile;

/**
 * Created by vov on 12.07.2017.
 */
public interface SocialService {
    Profile fetchProfile(String code) throws RetrieveSocialDataFailedException;
    String getClientId();

   default String getAuthorizeUrl(){
       throw new UnsupportedOperationException();
   }
}
