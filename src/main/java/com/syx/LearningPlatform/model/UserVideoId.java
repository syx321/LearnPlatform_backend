package com.syx.LearningPlatform.model;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.io.Serializable;
import java.util.Objects;

@Embeddable
public class UserVideoId implements Serializable {
    @Column(name = "user_id")
    private Long userId;

    @Column(name = "video_id")
    private Long videoId;

    // Constructors
    public UserVideoId() {
    }

    public UserVideoId(Long userId, Long videoId) {
        this.userId = userId;
        this.videoId = videoId;
    }

    // Getters and Setters
    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public Long getVideoId() {
        return videoId;
    }

    public void setVideoId(Long videoId) {
        this.videoId = videoId;
    }

    // Override methods
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        UserVideoId that = (UserVideoId) o;
        return Objects.equals(userId, that.userId) &&
                Objects.equals(videoId, that.videoId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(userId, videoId);
    }
}
