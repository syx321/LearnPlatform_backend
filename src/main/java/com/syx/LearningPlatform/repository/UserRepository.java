package com.syx.LearningPlatform.repository;

import com.syx.LearningPlatform.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    // 根据用户名查询用户
    User findByUsername(String username);

    // 添加关注关系
    @Transactional
    @Modifying
    @Query(value = "insert into user_following(user_id,following_video_id) value (?1,?2)", nativeQuery = true)
    void addFollowing(@Param("userId") Long userId, @Param("videoId") Long videoId);

    // 移除关注关系
    @Transactional
    @Modifying
    @Query(value = "delete from user_following where user_id =?1 and following_video_id=?2", nativeQuery = true)
    void removeFollowing(@Param("userId") Long userId, @Param("videoId") Long videoId);

    // 添加收藏关系
    @Transactional
    @Modifying
    @Query(value = "insert into user_favorites(user_id,favorites_video_id) value (?1,?2)", nativeQuery = true)
    void addFavorite(@Param("userId") Long userId, @Param("videoId") Long videoId);

    // 移除收藏关系
    @Transactional
    @Modifying
    @Query(value = "delete from user_favorites where user_id =?1 and favorites_video_id=?2", nativeQuery = true)
    void removeFavorite(@Param("userId") Long userId, @Param("videoId") Long videoId);
}