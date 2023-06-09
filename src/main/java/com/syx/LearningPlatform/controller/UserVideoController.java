package com.syx.LearningPlatform.controller;

import com.syx.LearningPlatform.DTO.VideoDTO;
import com.syx.LearningPlatform.model.Video;
import com.syx.LearningPlatform.service.UserVideoService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@RestController
@RequestMapping("/users/{userId}/videos")
public class UserVideoController {
    private final UserVideoService userVideoService;

    public UserVideoController(UserVideoService userVideoService) {
        this.userVideoService = userVideoService;
    }

    @PostMapping("/{videoId}/like")
    public ResponseEntity<Void> likeVideo(@PathVariable("userId") Long userId, @PathVariable("videoId") Long videoId) {
        userVideoService.likeVideo(userId, videoId);
        return ResponseEntity.ok().build();
    }

    @PostMapping("/{videoId}/unlike")
    public ResponseEntity<Void> unlikeVideo(@PathVariable("userId") Long userId, @PathVariable("videoId") Long videoId) {
        userVideoService.unlikeVideo(userId, videoId);
        return ResponseEntity.ok().build();
    }

    @PostMapping("/{videoId}/favorite")
    public ResponseEntity<Void> favoriteVideo(@PathVariable("userId") Long userId, @PathVariable("videoId") Long videoId) {
        userVideoService.favoriteVideo(userId, videoId);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/favoriteVideos")
    public ResponseEntity<List<Video>> getFavoriteVideos(@PathVariable("userId") Long userId) {
        return ResponseEntity.ok(userVideoService.getFavoriteVideos(userId));
    }

    @PostMapping("/{videoId}/unfavorite")
    public ResponseEntity<Void> unfavoriteVideo(@PathVariable("userId") Long userId, @PathVariable("videoId") Long videoId) {
        userVideoService.unfavoriteVideo(userId, videoId);
        return ResponseEntity.ok().build();
    }
    @PostMapping("/{otheruUserId}/unfollowing")
    public ResponseEntity<Void> unfollowing(@PathVariable("userId") Long userId, @PathVariable("otheruUserId") Long ownerId) {
        userVideoService.unfavoriteVideo(userId, ownerId);
        return ResponseEntity.ok().build();
    }

    @PostMapping("/upload")
    public ResponseEntity<Void> upload(@PathVariable("userId") Long userId, @RequestParam("video") MultipartFile multipartFile, VideoDTO v) {
        userVideoService.save(userId, multipartFile, v);
        return ResponseEntity.ok().build();
    }
}
