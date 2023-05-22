package com.syx.LearningPlatform.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import org.springframework.data.annotation.Id;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "videos")
public class Video {
    @javax.persistence.Id
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "title", nullable = false)
    @JsonProperty("name")
    private String name;

    @Column(name = "avatar")
    @JsonProperty("avatarUrl")
    private String avatarUrl;

    @Column(name = "description")
    @JsonProperty("desc")
    private String desc;

    @Column(name = "url", nullable = false)
    @JsonProperty("videoUrl")
    private String videoUrl;

    @OneToOne(fetch = FetchType.EAGER)
    @JoinTable(name = "user_video",
            joinColumns = @JoinColumn(name = "video_id"),
            inverseJoinColumns = @JoinColumn(name = "user_id"))
    private User user;

    @JsonProperty("ownerId")
    private Long ownerId;

    @JsonProperty("ownerName")
    private String ownerName;

    @JsonProperty("ownerAvatarUrlStr")
    private String ownerAvatar;

    // Constructors
    public Video() {
    }

    public Video(String title, String description, String url) {
        this.name = title;
        this.desc = description;
        this.videoUrl = url;
    }

    // Getters and Setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return name;
    }

    public void setTitle(String title) {
        this.name = title;
    }

    public String getDescription() {
        return desc;
    }

    public void setDescription(String description) {
        this.desc = description;
    }

    public String getUrl() {
        return videoUrl;
    }

    public void setUrl(String url) {
        this.videoUrl = url;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Video video = (Video) o;
        return Objects.equals(id, video.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    public String getOwnerAvatar() {
        return user.getAvatarUrl();
    }

    public String getOwnerName() {
        return user.getUsername();
    }

    public Long getOwnerId() {
        return user.getId();
    }
}


