package com.syx.LearningPlatform.repository;

import com.syx.LearningPlatform.model.UserVideo;
import com.syx.LearningPlatform.model.UserVideoId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserVideoRepository extends JpaRepository<UserVideo, UserVideoId> {
    List<UserVideo> findByUserId(Long userId);

    List<UserVideo> findByUserIdAndVideoId(Long userId, Long videoId);

    @Modifying
    @Query(value = "select user_id from user_video where video_id=?1", nativeQuery = true)
    List<Long> findUserId(@Param("videoId") Long videoId);
}