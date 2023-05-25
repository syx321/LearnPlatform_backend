package com.syx.LearningPlatform.controller;

import com.syx.LearningPlatform.DTO.UserLoginDTO;
import com.syx.LearningPlatform.DTO.UserRegistrationDTO;
import com.syx.LearningPlatform.model.User;
import com.syx.LearningPlatform.model.Video;
import com.syx.LearningPlatform.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

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
    public ResponseEntity<User> loginUser(@RequestParam("username") String username,
                                          @RequestParam("password") String password) {
        User user = null;
        try {
            user = userService.loginUser(username, password);
        } catch (IllegalArgumentException e) {
            User registeredUser = userService.getUserByName(username);
            if (registeredUser != null) {
                return ResponseEntity.badRequest().build();
            } else {
                UserRegistrationDTO registrationDTO = new UserRegistrationDTO(username, password);
                user = this.registerUser(registrationDTO).getBody();
            }
        }
        return ResponseEntity.ok(user);
    }

    @PostMapping("/{userId}/follow")
    public ResponseEntity<String> followUser(@PathVariable("userId") String userId,
                                             @RequestParam("followedUserId") String followedUserId) {
        Long idL = Long.parseLong(userId);
        Long followL = Long.parseLong(followedUserId);
        userService.followUser(idL, followL);
        return ResponseEntity.ok("User followed successfully");
    }

    @GetMapping("/{userId}/following")
    public ResponseEntity<List<User>> getFollowing(@PathVariable("userId") String userId) {
        Long idL = Long.parseLong(userId);
        List<User> following = userService.getFollowing(idL);
        return ResponseEntity.ok(following);
    }

    @PostMapping("/{userId}/unfollow")
    public ResponseEntity<String> unfollowUser(@PathVariable("userId") String userId,
                                             @RequestParam("followedUserId") String followedUserId) {
        Long idL = Long.parseLong(userId);
        Long followL = Long.parseLong(followedUserId);
        userService.unfollowUser(idL, followL);
        return ResponseEntity.ok("User followed successfully");
    }

    @PostMapping("/{userId}/favorite")
    public ResponseEntity<String> favoriteVideo(@PathVariable("userId") Long userId,
                                                @RequestParam("videoId") Long videoId) {
        userService.favoriteVideo(userId, videoId);
        return ResponseEntity.ok("Video favorited successfully");
    }

    @PostMapping("/{userId}/unfavorite")
    public ResponseEntity<String> unfavoriteVideo(@PathVariable("userId") String userId,
                                                @RequestParam("videoId") String videoId) {
        Long idL = Long.parseLong(userId);
        Long videoL = Long.parseLong(videoId);
        userService.unfavoriteVideo(idL, videoL);
        return ResponseEntity.ok("Video unfavorited successfully");
    }

    @GetMapping("/{userId}/feed")
    public ResponseEntity<List<Video>> getFeed(@PathVariable("userId") String userId) {
        Long idL = Long.parseLong(userId);
        List<Video> feed = userService.getFeed(idL);
        return ResponseEntity.ok(feed);
    }

    @GetMapping("/{userId}/getUserDataId")
    public ResponseEntity<User> getMyData(@PathVariable("userId") String userId) {
        Long idL = Long.parseLong(userId);
        return ResponseEntity.ok(userService.getUserById(idL));
    }
}
