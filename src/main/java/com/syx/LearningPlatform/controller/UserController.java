package com.syx.LearningPlatform.controller;

import com.syx.LearningPlatform.DTO.UserLoginDTO;
import com.syx.LearningPlatform.DTO.UserRegistrationDTO;
import com.syx.LearningPlatform.model.User;
import com.syx.LearningPlatform.model.Video;
import com.syx.LearningPlatform.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/users")
public class UserController {
    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/register")
    public ResponseEntity<User> registerUser(@RequestBody UserRegistrationDTO registrationDTO) {
        User user = userService.registerUser(registrationDTO.getUsername(), registrationDTO.getPassword());
        return ResponseEntity.ok(user);
    }

    @PostMapping("/login")
    public ResponseEntity<User> loginUser(@RequestBody UserLoginDTO loginDTO) {
        User user = null;
        try {
            user = userService.loginUser(loginDTO.getUsername(), loginDTO.getPassword());
        } catch(IllegalArgumentException e) {
            UserRegistrationDTO registrationDTO = new UserRegistrationDTO(loginDTO.getUsername(), loginDTO.getPassword());
            user = this.registerUser(registrationDTO).getBody();
        }
        return ResponseEntity.ok(user);
    }

    @PostMapping("/{userId}/follow")
    public ResponseEntity<String> followUser(@PathVariable("userId") Long userId,
                                             @RequestParam("followedUserId") Long followedUserId) {
        userService.followUser(userId, followedUserId);
        return ResponseEntity.ok("User followed successfully");
    }

    @PostMapping("/{userId}/favorite")
    public ResponseEntity<String> favoriteVideo(@PathVariable("userId") Long userId,
                                                @RequestParam("videoId") Long videoId) {
        userService.favoriteVideo(userId, videoId);
        return ResponseEntity.ok("Video favorited successfully");
    }

    @GetMapping("/{userId}/feed")
    public ResponseEntity<List<Video>> getFeed(@PathVariable("userId") Long userId) {
        List<Video> feed = userService.getFeed(userId);
        return ResponseEntity.ok(feed);
    }
}
