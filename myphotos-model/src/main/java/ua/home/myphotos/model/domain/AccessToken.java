package ua.home.myphotos.model.domain;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.sql.Timestamp;

/**
 * Created by vov on 12.07.2017.
 */
@Entity
@Table(name = "access_token", schema = "public", catalog = "myphotos")
public class AccessToken extends AbstractDomain{
    private String token;

    private Profile profile;

    @Id
    @Basic
    @Column(name = "token")
    @NotNull
    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    @JoinColumn(name = "profile_id", referencedColumnName = "id", nullable = false)
    @NotNull
    public Profile getProfileByProfileId() {
        return profile;
    }

    public void setProfileByProfileId(Profile profile) {
        this.profile = profile;
    }
}