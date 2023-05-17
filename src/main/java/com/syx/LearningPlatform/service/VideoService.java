package com.syx.LearningPlatform.service;

import com.syx.LearningPlatform.DTO.VideoDTO;
import com.syx.LearningPlatform.model.Video;
import com.syx.LearningPlatform.repository.VideoRepository;
import lombok.Cleanup;
import lombok.SneakyThrows;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletResponse;
import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.OutputStream;
import java.net.URLEncoder;
import java.util.List;

@Service
public class VideoService {
    private final VideoRepository videoRepository;
    private final HttpServletResponse response;

    @Autowired
    public VideoService(VideoRepository videoRepository, HttpServletResponse ht) {
        this.videoRepository = videoRepository;
        this.response = ht;
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

    @SneakyThrows
    public void download(String path) {
        File file = new File(path);
        if (!file.exists()) {
            throw new IllegalArgumentException("noy found file");
        }
        String[] split = path.split("/");
        response.setHeader("Access-Control-Allow-Origin", "*");
        response.setContentType("application/octet-stream;charset=UTF-8");
        response.setHeader("Access-Control-Expose-Headers", "Content-Disposition");
        response.setHeader("Content-Disposition", "attachment;filename=" + URLEncoder.encode(split[split.length - 1], "UTF-8"));
        @Cleanup OutputStream os = response.getOutputStream();
        @Cleanup BufferedInputStream bis = new BufferedInputStream(new FileInputStream(file));
        byte[] buffer = new byte[1024];
        while (bis.read(buffer) != -1) {
            os.write(buffer);
        }
    }
}
