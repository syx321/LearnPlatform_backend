package com.syx.LearningPlatform.repository;

import com.syx.LearningPlatform.model.User;
import com.syx.LearningPlatform.model.Video;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    // 根据用户名查询用户
    User findByUsername(String username);

    // 添加关注关系
    @Modifying
    @Query("UPDATE User u SET u.following = :followedUser WHERE u.id = :userId")
    void addFollowing(@Param("userId") Long userId, @Param("followedUser") User followedUser);

    // 移除关注关系
    @Modifying
    @Query("UPDATE User u SET u.following = :unfollowedUser WHERE u.id = :userId")
    void removeFollowing(@Param("userId") Long userId, @Param("unfollowedUser") User unfollowedUser);

    // 添加收藏关系
    @Modifying
    @Query("UPDATE User u SET u.favorites = :video WHERE u.id = :userId")
    void addFavorite(@Param("userId") Long userId, @Param("video") Video video);
   // 移除收藏关系
   @Modifying
   @Query("UPDATE User u SET u.favorites = :video WHERE u.id = :userId")
   void removeFavorite(@Param("userId") Long userId, @Param("video") Video video);
}