CREATE TABLE users
(
    id           BIGINT AUTO_INCREMENT PRIMARY KEY,     #用户id
    username     VARCHAR(255) NOT NULL,                 #用户名
    password     VARCHAR(255) NOT NULL,                 #密码
    avatar_url   VARCHAR(255) NULL,                     #头像
    created_time timestamp NULL default CURRENT_TIMESTAMP#创建时间
);

CREATE TABLE videos
(
    id           BIGINT AUTO_INCREMENT PRIMARY KEY,     #视频id
    title        VARCHAR(255) NOT NULL,                 #视频标题
    avatar       VARCHAR(255) NOT NULL,                 #视频封面
    description  VARCHAR(255),                          #视频描述
    url          VARCHAR(255) NOT NULL,                 #视频地址
    created_time timestamp NULL default CURRENT_TIMESTAMP#创建时间
);

CREATE TABLE user_video
(
    user_id      BIGINT NOT NULL,                       #用户id
    video_id     BIGINT NOT NULL,                       #视频id
    favorited    BIGINT NOT NULL default 0,             #收藏数
    liked        BIGINT NOT NULL default 0,             #点赞数
    created_time timestamp NULL default CURRENT_TIMESTAMP,
        PRIMARY KEY (user_id, video_id),                #创建时间
    FOREIGN KEY (user_id) REFERENCES users (id),        #外键
    FOREIGN KEY (video_id) REFERENCES videos (id)       #外键
);

CREATE TABLE user_following
(
    user_id           BIGINT NOT NULL,                  #用户id
    following_user_id BIGINT NOT NULL,                  #关注的用户id
    created_time      timestamp NULL default CURRENT_TIMESTAMP#创建时间
);

CREATE TABLE user_favorites
(
    user_id           BIGINT NOT NULL,                  #用户id
    favorites_video_id BIGINT NOT NULL,                 #收藏的视频id
    created_time      timestamp NULL default CURRENT_TIMESTAMP#创建时间
);