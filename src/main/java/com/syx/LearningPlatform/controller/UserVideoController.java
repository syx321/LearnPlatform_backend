package com.syx.LearningPlatform.controller;

import com.syx.LearningPlatform.service.UserVideoService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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

    @PostMapping("/{videoId}/unfavorite")
    public ResponseEntity<Void> unfavoriteVideo(@PathVariable("userId") Long userId, @PathVariable("videoId") Long videoId) {
        userVideoService.unfavoriteVideo(userId, videoId);
        return ResponseEntity.ok().build();
    }
}
