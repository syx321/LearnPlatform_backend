package com.syx.LearningPlatform.service;

import com.syx.LearningPlatform.model.User;
import com.syx.LearningPlatform.model.UserVideo;
import com.syx.LearningPlatform.model.UserVideoId;
import com.syx.LearningPlatform.model.Video;
import com.syx.LearningPlatform.repository.UserRepository;
import com.syx.LearningPlatform.repository.UserVideoRepository;
import com.syx.LearningPlatform.repository.VideoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class UserService {
    private UserRepository userRepository;
    private VideoRepository videoRepository;
    private UserVideoRepository userVideoRepository;

    @Autowired
    public UserService(UserRepository userRepository, VideoRepository videoRepository, UserVideoRepository userVideoRepository) {
        this.userRepository = userRepository;
        this.videoRepository = videoRepository;
        this.userVideoRepository = userVideoRepository;
    }

    public User registerUser(String username, String password) {
        User user = new User();
        user.setUsername(username);
        user.setPassword(password);

        return userRepository.save(user);
    }

    public User loginUser(String username, String password) {
        User user = userRepository.findByUsername(username);

        if (user == null || !user.getPassword().equals(password)) {
            throw new IllegalArgumentException("Invalid username or password");
        }

        return user;
    }

    public void followUser(Long userId, Long followedUserId) {
        User user = userRepository.findById(userId).orElse(null);
        User followedUser = userRepository.findById(followedUserId).orElse(null);

        if (user == null || followedUser == null) {
            throw new IllegalArgumentException("Invalid user or followed user ID");
        }

        user.getFollowing().add(followedUser);
        userRepository.save(user);
    }

    public void unfollowUser(Long userId, Long followedUserId) {
        User user = userRepository.findById(userId).orElse(null);
        User followedUser = userRepository.findById(followedUserId).orElse(null);

        if (user == null || followedUser == null) {
            throw new IllegalArgumentException("Invalid user or followed user ID");
        }

        user.getFollowing().remove(followedUser);
        userRepository.save(user);
    }

    public void favoriteVideo(Long userId, Long videoId) {
        User user = userRepository.findById(userId).orElse(null);
        Video video = videoRepository.findById(videoId).orElse(null);

        if (user == null || video == null) {
            throw new IllegalArgumentException("Invalid user or video ID");
        }

        UserVideoId userVideoId = new UserVideoId(userId, videoId);
        UserVideo userVideo = new UserVideo();
        userVideo.setId(userVideoId);
        userVideo.setUser(user);
        userVideo.setVideo(video);

        userVideoRepository.save(userVideo);
    }

    public void unfavoriteVideo(Long userId, Long videoId) {
        User user = userRepository.findById(userId).orElse(null);
        Video video = videoRepository.findById(videoId).orElse(null);

        if (user == null || video == null) {
            throw new IllegalArgumentException("Invalid user or video ID");
        }

        UserVideoId userVideoId = new UserVideoId(userId, videoId);
        userVideoRepository.deleteById(userVideoId);
    }

    public List<Video> getFeed(Long userId) {
        List<UserVideo> userVideos = userVideoRepository.findByUserId(userId);
        List<Long> videoIds = userVideos.stream()
                .map(UserVideo::getVideo)
                .map(Video::getId)
                .collect(Collectors.toList());

        return videoRepository.findAllById(videoIds);
    }

    public User getUserById(Long userId){
        return userRepository.findById(userId).orElse(null);
    }
}
