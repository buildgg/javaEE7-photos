package ua.home.myphotos.service;

import ua.home.myphotos.exception.AccessForbiddenException;
import ua.home.myphotos.exception.InvalidAccessTokenException;
import ua.home.myphotos.model.domain.AccessToken;
import ua.home.myphotos.model.domain.Profile;

/**
 * Created by vov on 12.07.2017.
 */
public interface AccessTokenService {
    AccessToken generateAccessToken(Profile profile);

    Profile findProfile(String token, Long profileId) throws AccessForbiddenException, InvalidAccessTokenException;

    void invalidateAccessToken(String token) throws InvalidAccessTokenException;
}
