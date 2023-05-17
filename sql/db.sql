CREATE TABLE users
(
    id           BIGINT AUTO_INCREMENT PRIMARY KEY,
    username     VARCHAR(255) NOT NULL,
    password     VARCHAR(255) NOT NULL,
    created_time timestamp NULL default CURRENT_TIMESTAMP
);

CREATE TABLE videos
(
    id           BIGINT AUTO_INCREMENT PRIMARY KEY,
    title        VARCHAR(255) NOT NULL,
    description  VARCHAR(255),
    url          VARCHAR(255) NOT NULL,
    created_time timestamp NULL default CURRENT_TIMESTAMP
);

CREATE TABLE user_video
(
    user_id      BIGINT NOT NULL,
    video_id     BIGINT NOT NULL,
    favorited    BIGINT NOT NULL default 0,
    liked        BIGINT NOT NULL default 0,
    created_time timestamp NULL default CURRENT_TIMESTAMP,
        PRIMARY KEY (user_id, video_id),
    FOREIGN KEY (user_id) REFERENCES users (id),
    FOREIGN KEY (video_id) REFERENCES videos (id)
);

CREATE TABLE user_following
(
    user_id           BIGINT NOT NULL,
    following_user_id BIGINT NOT NULL,
    created_time      timestamp NULL default CURRENT_TIMESTAMP
);

CREATE TABLE user_favorites
(
    user_id           BIGINT NOT NULL,
    favorites_video_id BIGINT NOT NULL,
    created_time      timestamp NULL default CURRENT_TIMESTAMP
);