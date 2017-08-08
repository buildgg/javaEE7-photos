package ua.home.myphotos.model.domain;

import javax.persistence.*;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.sql.Timestamp;

/**
 * Created by vov on 12.07.2017.
 */
@Entity
@Table(name = "photo", schema = "public", catalog = "myphotos")
public class Photo extends AbstractDomain {
    private long id;
    private String smallUrl;
    private String largeUrl;
    private String originalUrl;
    private long views;
    private long downloads;
    private Profile profile;

    @Id
    @Column(name = "id", updatable = false)
    @SequenceGenerator(sequenceName = "photo_seq",
                       name = "photo_generator",
                       initialValue = 1,
                       allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "photo_generator")
    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    @Basic
    @Column(name = "small_url", nullable = false, length = 255, updatable = false)
    @NotNull
    @Size(max = 255)
    public String getSmallUrl() {
        return smallUrl;
    }

    public void setSmallUrl(String smallUrl) {
        this.smallUrl = smallUrl;
    }

    @Basic
    @Column(name = "large_url", nullable = false, length = 255, updatable = false)
    @NotNull
    @Size(max = 255)
    public String getLargeUrl() {
        return largeUrl;
    }

    public void setLargeUrl(String largeUrl) {
        this.largeUrl = largeUrl;
    }

    @Basic
    @Column(name = "original_url", nullable = false, length = 255, updatable = false)
    @NotNull
    @Size(max = 255)
    public String getOriginalUrl() {
        return originalUrl;
    }

    public void setOriginalUrl(String originalUrl) {
        this.originalUrl = originalUrl;
    }

    @Basic
    @Column(name = "views", nullable = false)
    @Min(0)
    public long getViews() {
        return views;
    }

    public void setViews(long views) {
        this.views = views;
    }

    @Basic
    @Column(name = "downloads", nullable = false)
    @Min(0)
    public long getDownloads() {
        return downloads;
    }

    public void setDownloads(long downloads) {
        this.downloads = downloads;
    }

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "profile_id", referencedColumnName = "id", nullable = false)
    @NotNull
    public Profile getProfile() {
        return profile;
    }

    public void setProfile(Profile profile) {
        this.profile = profile;
    }
}
