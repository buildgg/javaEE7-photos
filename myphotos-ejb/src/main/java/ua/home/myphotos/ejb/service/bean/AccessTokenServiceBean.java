package ua.home.myphotos.ejb.service.bean;

import ua.home.myphotos.ejb.repository.AccessTokenRepository;
import ua.home.myphotos.exception.AccessForbiddenException;
import ua.home.myphotos.exception.InvalidAccessTokenException;
import ua.home.myphotos.model.domain.AccessToken;
import ua.home.myphotos.model.domain.Profile;
import ua.home.myphotos.service.AccessTokenService;

import javax.ejb.Local;
import javax.ejb.Stateless;
import javax.inject.Inject;
import java.util.Optional;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Created by vov on 08.08.2017.
 */
@Stateless
@Local(AccessTokenService.class)
public class AccessTokenServiceBean implements AccessTokenService {

    @Inject
    private Logger logger;

    @Inject
    private AccessTokenRepository accessTokenRepository;

    @Override
    public AccessToken generateAccessToken(Profile profile) {
        AccessToken accessToken = new AccessToken();
        accessToken.setProfile(profile);
        accessTokenRepository.create(accessToken);
        return accessToken;
    }

    @Override
    public Profile findProfile(String token, Long profileId)
            throws AccessForbiddenException, InvalidAccessTokenException {
        Optional<AccessToken> accessTokenOptional = accessTokenRepository.findByToken(token);
        if (!accessTokenOptional.isPresent()){
            throw new InvalidAccessTokenException(String.format("Access Token %s invalid", token));
        }
        Profile profile = accessTokenOptional.get().getProfile();

        if (!profile.getId().equals(profileId)){
            throw new AccessForbiddenException(String.format("Access forbidden for token=%s and profileId=%s", token, profileId));
        }


        return profile;
    }

    @Override
    public void invalidateAccessToken(String token) throws InvalidAccessTokenException {
       boolean removed = accessTokenRepository.removeAccessToken(token);
       if (!removed){
           logger.log(Level.WARNING, "Access token {0} not found", token);
           throw new InvalidAccessTokenException("Access token not found");
       }
    }
}
