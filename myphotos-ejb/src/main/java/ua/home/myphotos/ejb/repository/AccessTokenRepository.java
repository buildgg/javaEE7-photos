package ua.home.myphotos.ejb.repository;

import ua.home.myphotos.model.domain.AccessToken;

import java.util.Optional;

/**
 * Created by vov on 07.08.2017.
 */
public interface AccessTokenRepository extends EntityRepository<AccessToken, String> {
    Optional<AccessToken> findByToken(String token);

    boolean removeAccessToken(String token);
}
