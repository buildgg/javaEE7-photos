package ua.home.myphotos.model.domain;

import ua.home.myphotos.model.validation.Email;
import ua.home.myphotos.model.validation.EnglishLanguage;

import javax.persistence.*;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;


/**
 * Created by vov on 12.07.2017.
 */
@Entity
@Table(name = "profile", schema = "public", catalog = "myphotos")
public class Profile extends AbstractDomain {
    private Long id;
    private String uid;
    private String email;
    private String firstName;
    private String lastName;
    private String avatarUrl;
    private String jobTitle;
    private String location;
    private int photoCount;
    private int rating;

    @Id
    @Column(name = "id", updatable = false)
    @SequenceGenerator(name = "profile_generator",
                       allocationSize = 1,
                       initialValue = 1,
                       sequenceName = "profile_seq")
    @GeneratedValue(generator = "profile_generator",
                    strategy = GenerationType.SEQUENCE)

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @Basic
    @Column(name = "uid", nullable = false, length = 255, unique = true, updatable = false)
    @NotNull
    @Size(max = 255)
    public String getUid() {
        return uid;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }

    @Basic
    @Column(name = "email", nullable = false, length = 128, unique = true, updatable = false)
    @NotNull
    @Size(max = 128)
    @Email
    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Basic
    @Column(name = "first_name", nullable = false, length = 60)
    @NotNull(message = "{}")
    @Size(min = 1, max = 60, message = "{}")
    @EnglishLanguage(withNumbers = false, withSpecialSymbols = false)
    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    @Basic
    @Column(name = "last_name", nullable = false, length = 60)
    @NotNull(message = "{}")
    @Size(min = 1, max = 60, message = "{}")
    @EnglishLanguage(withNumbers = false, withSpecialSymbols = false)
    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    @Basic
    @Column(name = "avatar_url", nullable = false, length = 255)
    @NotNull
    @Size(max = 255)
    public String getAvatarUrl() {
        return avatarUrl;
    }

    public void setAvatarUrl(String avatarUrl) {
        this.avatarUrl = avatarUrl;
    }

    @Basic
    @Column(name = "job_title", nullable = false, length = 128)
    @NotNull(message = "{}")
    @Size(min = 5, max = 128, message = "{}")
    @EnglishLanguage(withSpecialSymbols = false)
    public String getJobTitle() {
        return jobTitle;
    }

    public void setJobTitle(String jobTitle) {
        this.jobTitle = jobTitle;
    }

    @Basic
    @Column(name = "location", nullable = false, length = 128)
    @NotNull(message = "{}")
    @Size(min = 5, max = 128, message = "{}")
    @EnglishLanguage(withSpecialSymbols = false)
    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    @Basic
    @Column(name = "photo_count")
    @Min(0)
    public int getPhotoCount() {
        return photoCount;
    }

    public void setPhotoCount(int photoCount) {
        this.photoCount = photoCount;
    }

    @Basic
    @Column(name = "rating")
    @Min(0)
    public int getRating() {
        return rating;
    }

    public void setRating(int rating) {
        this.rating = rating;
    }

    @Transient
    public String getFullName() {
        return getFirstName() + getLastName();
    }

}
