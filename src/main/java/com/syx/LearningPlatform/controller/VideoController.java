package com.syx.LearningPlatform.controller;

import com.syx.LearningPlatform.DTO.VideoDTO;
import com.syx.LearningPlatform.model.Video;
import com.syx.LearningPlatform.service.VideoService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/videos")
public class VideoController {
    private final VideoService videoService;

    public VideoController(VideoService videoService) {
        this.videoService = videoService;
    }

    @GetMapping
    public ResponseEntity<List<Video>> getAllVideos() {
        List<Video> videos = videoService.getAllVideos();
        return ResponseEntity.ok(videos);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Video> getVideoById(@PathVariable("id") Long id) {
        Video video = videoService.getVideoById(id);
        if (video != null) {
            return ResponseEntity.ok(video);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping
    public ResponseEntity<Video> createVideo(@RequestBody VideoDTO videoDTO) {
        Video createdVideo = videoService.createVideo(videoDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdVideo);
    }

    @PutMapping("update/{id}")
    public ResponseEntity<Video> updateVideo(@PathVariable("id") Long id, @RequestBody VideoDTO videoDTO) {
        Video updatedVideo = videoService.updateVideo(id, videoDTO);
        if (updatedVideo != null) {
            return ResponseEntity.ok(updatedVideo);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("delete/{id}")
    public ResponseEntity<Void> deleteVideo(@PathVariable("id") Long id) {
        videoService.deleteVideo(id);
        return ResponseEntity.ok().build();
    }
}

