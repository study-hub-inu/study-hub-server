DROP TABLE IF EXISTS user_study;
DROP TABLE if EXISTS apply;
DROP TABLE if EXISTS comment;
DROP TABLE if EXISTS bookmark;
DROP TABLE if EXISTS notification;
DROP TABLE if EXISTS post;
DROP TABLE if EXISTS study;
DROP TABLE if EXISTS users;
DROP TABLE IF EXISTS fcm_token;


CREATE TABLE users
(
    user_id            BIGINT NOT NULL AUTO_INCREMENT ,
    email              VARCHAR(55)  DEFAULT NULL,
    nickname           VARCHAR(20)  DEFAULT NULL,
    password           VARCHAR(100)  DEFAULT NULL,
    image_url          VARCHAR(255) DEFAULT NULL,
    gender             VARCHAR(6)   DEFAULT NULL,
    major              VARCHAR(50)  DEFAULT NULL,
    created_date       TIMESTAMP(3)    DEFAULT NULL,
    modified_date      TIMESTAMP(3)    DEFAULT NULL,
    PRIMARY KEY (user_id)
);

CREATE TABLE post
(
    post_id            BIGINT NOT NULL AUTO_INCREMENT,
    posted_user_id     BIGINT        NOT NULL,
    title              VARCHAR(55)   DEFAULT NULL,
    content            VARCHAR(255)  DEFAULT NULL,
    chat_url           VARCHAR(255)  DEFAULT NULL,
    major              VARCHAR(50)   DEFAULT NULL,
    study_person       INTEGER       DEFAULT NULL,
    filtered_gender    VARCHAR(6)    DEFAULT NULL,
    study_way          VARCHAR(7)    DEFAULT NULL,
    penalty            INTEGER       DEFAULT NULL,
    penalty_way        VARCHAR(30)   DEFAULT NULL,
    close              TINYINT       DEFAULT 0,
    study_start_date   DATE          DEFAULT NULL,
    study_end_date     DATE          DEFAULT NULL,
    remaining_seat     INTEGER       DEFAULT NULL,
    study_id           BIGINT        NOT NULL,
    created_date       TIMESTAMP(3)     DEFAULT NULL,
    modified_date      TIMESTAMP(3)     DEFAULT NULL,
    PRIMARY KEY (post_id)
);

CREATE TABLE study
(
    study_id           BIGINT NOT NULL AUTO_INCREMENT,
    title              VARCHAR(55)   DEFAULT NULL,
    content            VARCHAR(255)  DEFAULT NULL,
    study_start_date   DATE          DEFAULT NULL,
    study_end_date     DATE          DEFAULT NULL,
    chat_url           VARCHAR(100)  DEFAULT NULL,
    master_user_id     BIGINT NOT NULL,
    PRIMARY KEY (study_id)
);

CREATE TABLE notification
(
    notification_id    BIGINT           NOT NULL AUTO_INCREMENT,
    post_id            BIGINT           NOT NULL,
    receiver_id         BIGINT           NOT NULL,
    sender_id          BIGINT           NOT NULL,
    content            VARCHAR(255)     NOT NULL,
    notification_type  VARCHAR(15)      DEFAULT NULL,
    checked            TINYINT          DEFAULT 0,
    created_date       TIMESTAMP(3)     DEFAULT NULL,
    modified_date      TIMESTAMP(3)     DEFAULT NULL,
    PRIMARY KEY (notification_id)
);

CREATE TABLE bookmark
(
    bookmark_id        BIGINT NOT NULL AUTO_INCREMENT,
    post_id            BIGINT        NOT NULL,
    user_id            BIGINT        NOT NULL,
    created_date       TIMESTAMP(3)     DEFAULT NULL,
    modified_date      TIMESTAMP(3)     DEFAULT NULL,
    PRIMARY KEY (bookmark_id)
);

CREATE TABLE apply (
      apply_id BIGINT AUTO_INCREMENT PRIMARY KEY,
      approve boolean,
      study_id BIGINT,
      user_id BIGINT,
      FOREIGN KEY (study_id) REFERENCES study(study_id),
      FOREIGN KEY (user_id) REFERENCES users(user_id)
);

CREATE TABLE comment (
    comment_id      BIGINT AUTO_INCREMENT PRIMARY KEY,
    user_id         BIGINT      NOT NULL,
    post_id         BIGINT      NOT NULL,
    content         VARCHAR(100)  DEFAULT NULL,
    created_date    TIMESTAMP(3)     DEFAULT NULL,
    modified_date   TIMESTAMP(3)     DEFAULT NULL
);

CREATE TABLE fcm_token (
    fcm_token_id    BIGINT       AUTO_INCREMENT,
    user_id         BIGINT       NOT NULL,
    token           VARCHAR(255) NOT NULL,
    PRIMARY KEY (fcm_token_id)
);