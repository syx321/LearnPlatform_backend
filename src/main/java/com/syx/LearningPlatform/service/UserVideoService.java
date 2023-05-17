package com.syx.LearningPlatform.service;

import com.syx.LearningPlatform.model.User;
import com.syx.LearningPlatform.model.UserVideo;
import com.syx.LearningPlatform.model.UserVideoId;
import com.syx.LearningPlatform.model.Video;
import com.syx.LearningPlatform.repository.UserRepository;
import com.syx.LearningPlatform.repository.UserVideoRepository;
import com.syx.LearningPlatform.repository.VideoRepository;
import org.springframework.stereotype.Service;

@Service
public class UserVideoService {
    private final UserRepository userRepository;
    private final VideoRepository videoRepository;
    private final UserVideoRepository userVideoRepository;

    public UserVideoService(UserRepository userRepository, VideoRepository videoRepository, UserVideoRepository userVideoRepository) {
        this.userRepository = userRepository;
        this.videoRepository = videoRepository;
        this.userVideoRepository = userVideoRepository;
    }

    public void likeVideo(Long userId, Long videoId) {
        User user = userRepository.findById(userId).orElseThrow(() -> new IllegalArgumentException("User not found with id: " + userId));
        Video video = videoRepository.findById(videoId).orElseThrow(() -> new IllegalArgumentException("Video not found with id: " + videoId));

        UserVideoId userVideoId = new UserVideoId(user.getId(), video.getId());
        UserVideo userVideo = new UserVideo(userVideoId, user, video);
        userVideo.setLiked(true);

        userVideoRepository.save(userVideo);
    }

    public void unlikeVideo(Long userId, Long videoId) {
        User user = userRepository.findById(userId).orElseThrow(() -> new IllegalArgumentException("User not found with id: " + userId));
        Video video = videoRepository.findById(videoId).orElseThrow(() -> new IllegalArgumentException("Video not found with id: " + videoId));

        UserVideoId userVideoId = new UserVideoId(user.getId(), video.getId());
        UserVideo userVideo = userVideoRepository.findById(userVideoId).orElseThrow(() -> new IllegalArgumentException("UserVideo not found for user id: " + userId + " and video id: " + videoId));

        userVideoRepository.delete(userVideo);
    }

    public void favoriteVideo(Long userId, Long videoId) {
        User user = userRepository.findById(userId).orElseThrow(() -> new IllegalArgumentException("User not found with id: " + userId));
        Video video = videoRepository.findById(videoId).orElseThrow(() -> new IllegalArgumentException("Video not found with id: " + videoId));

        UserVideoId userVideoId = new UserVideoId(user.getId(), video.getId());
        UserVideo userVideo = new UserVideo(userVideoId, user, video);
        userVideo.setFavorited(true);

        userVideoRepository.save(userVideo);
    }

    public void unfavoriteVideo(Long userId, Long videoId) {
        User user = userRepository.findById(userId).orElseThrow(() -> new IllegalArgumentException("User not found with id: " + userId));
        Video video = videoRepository.findById(videoId).orElseThrow(() -> new IllegalArgumentException("Video not found with id: " + videoId));

        UserVideoId userVideoId = new UserVideoId(user.getId(), video.getId());
        UserVideo userVideo = userVideoRepository.findById(userVideoId).orElseThrow(() -> new IllegalArgumentException("UserVideo not found for user id: " + userId + " and video id: " + videoId));

        userVideoRepository.delete(userVideo);
    }
}
