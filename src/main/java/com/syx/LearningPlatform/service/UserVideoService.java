package com.syx.LearningPlatform.service;

import com.syx.LearningPlatform.DTO.VideoDTO;
import com.syx.LearningPlatform.model.User;
import com.syx.LearningPlatform.model.UserVideo;
import com.syx.LearningPlatform.model.UserVideoId;
import com.syx.LearningPlatform.model.Video;
import com.syx.LearningPlatform.repository.UserRepository;
import com.syx.LearningPlatform.repository.UserVideoRepository;
import com.syx.LearningPlatform.repository.VideoRepository;
import lombok.Cleanup;
import lombok.SneakyThrows;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.FileOutputStream;
import java.util.Optional;

@Service
public class UserVideoService {
    private final UserRepository userRepository;
    private final VideoRepository videoRepository;
    private final UserVideoRepository userVideoRepository;
    @Value("${file.path}")
    private String path;

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

    @SneakyThrows
    public void save(Long userId, MultipartFile multipartFile, VideoDTO v) {
        if (multipartFile.isEmpty()) {
            throw new IllegalArgumentException("not file");
        }
        String[] split = Optional.ofNullable(multipartFile.getOriginalFilename()).orElse("").split("\\.");
        if (!"mp4".equals(split[1])) {
            throw new IllegalArgumentException("file type error");
        }
        User user = userRepository.findById(userId).orElseThrow(() -> new IllegalArgumentException("User not found with id: " + userId));

        String filePath = path + split[0] + System.currentTimeMillis() + ".m3u8";
        @Cleanup FileOutputStream fileOutputStream = new FileOutputStream(filePath);
        @Cleanup BufferedOutputStream bufferedOutputStream = new BufferedOutputStream(fileOutputStream);
        @Cleanup BufferedInputStream bufferedInputStream = new BufferedInputStream(multipartFile.getInputStream());
        byte[] bytes = new byte[2048];
        int len;
        while ((len = bufferedInputStream.read(bytes)) != -1) {
            bufferedOutputStream.write(bytes, 0, len);
        }
        Video video = videoRepository.save(new Video(v.getTitle(), v.getDescription(), filePath));
        UserVideoId userVideoId = new UserVideoId(user.getId(), video.getId());
        userVideoRepository.save(new UserVideo(userVideoId, user, video));
    }
}
