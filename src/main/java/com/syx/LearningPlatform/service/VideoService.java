package com.syx.LearningPlatform.service;

import com.syx.LearningPlatform.DTO.VideoDTO;
import com.syx.LearningPlatform.model.Video;
import com.syx.LearningPlatform.repository.VideoRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class VideoService {
    private final VideoRepository videoRepository;

    public VideoService(VideoRepository videoRepository) {
        this.videoRepository = videoRepository;
    }

    public Video createVideo(VideoDTO videoDTO) {
        Video video = new Video();
        video.setTitle(videoDTO.getTitle());
        video.setDescription(videoDTO.getDescription());
        video.setUrl(videoDTO.getUrl());

        return videoRepository.save(video);
    }

    public Video getVideoById(Long videoId) {
        return videoRepository.findById(videoId)
                .orElseThrow(() -> new IllegalArgumentException("Video not found with id: " + videoId));
    }

    public List<Video> getAllVideos() {
        return videoRepository.findAll();
    }

    public void deleteVideo(Long videoId) {
        Video video = getVideoById(videoId);
        videoRepository.delete(video);
    }

    public Video updateVideo(Long id, VideoDTO videoDTO) {
        Video existingVideo = getVideoById(id);
        existingVideo.setTitle(videoDTO.getTitle());
        existingVideo.setDescription(videoDTO.getDescription());
        existingVideo.setUrl(videoDTO.getUrl());
        return videoRepository.save(existingVideo);
    }
}
