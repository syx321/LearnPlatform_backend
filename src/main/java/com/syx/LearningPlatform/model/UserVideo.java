package com.syx.LearningPlatform.model;

import javax.persistence.*;

@Entity
@Table(name = "user_video")
public class UserVideo {
    @EmbeddedId
    private UserVideoId id;
    private boolean favorited;
    private boolean liked;

    @ManyToOne(fetch = FetchType.LAZY)
    @MapsId("userId")
    private User user;

    @ManyToOne(fetch = FetchType.LAZY)
    @MapsId("videoId")
    private Video video;

    // Constructors
    public UserVideo() {
    }

    public UserVideo(UserVideoId id, User user, Video video) {
        this.id = id;
        this.user = user;
        this.video = video;
    }

    // Getters and Setters
    public UserVideoId getId() {
        return id;
    }

    public void setId(UserVideoId id) {
        this.id = id;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Video getVideo() {
        return video;
    }

    public void setVideo(Video video) {
        this.video = video;
    }
    public boolean isFavorited() {
        return favorited;
    }

    public void setFavorited(boolean favorited) {
        this.favorited = favorited;
    }

    public boolean isLiked() {
        return liked;
    }

    public void setLiked(boolean liked) {
        this.liked = liked;
    }
}

