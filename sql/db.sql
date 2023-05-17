CREATE TABLE users (
                       id BIGINT AUTO_INCREMENT PRIMARY KEY,
                       username VARCHAR(255) NOT NULL,
                       password VARCHAR(255) NOT NULL
);

CREATE TABLE videos (
                        id BIGINT AUTO_INCREMENT PRIMARY KEY,
                        title VARCHAR(255) NOT NULL,
                        description VARCHAR(255),
                        url VARCHAR(255) NOT NULL
);

CREATE TABLE user_video (
                            user_id BIGINT NOT NULL,
                            video_id BIGINT NOT NULL,
                            favorited BIGINT NOT NULL default 0,
                            liked BIGINT NOT NULL default 0,
                            PRIMARY KEY (user_id, video_id),
                            FOREIGN KEY (user_id) REFERENCES users(id),
                            FOREIGN KEY (video_id) REFERENCES videos(id)
);