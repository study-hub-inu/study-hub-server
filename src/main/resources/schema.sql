DROP TABLE if EXISTS users CASCADE;
DROP TABLE if EXISTS vote_result;
DROP TABLE if EXISTS drink;
DROP TABLE if EXISTS vote_drink_content;
DROP TABLE if EXISTS bookmark;
DROP TABLE if EXISTS enjoy_drink;
DROP TABLE if EXISTS comment_emotion;
DROP TABLE if EXISTS comment;

CREATE TABLE users
(
    id                 BIGINT NOT NULL AUTO_INCREMENT,
    email              VARCHAR(55)  DEFAULT NULL,
    nickname           VARCHAR(20)  DEFAULT NULL,
    password           VARCHAR(30)  DEFAULT NULL,
    image_url          VARCHAR(100) DEFAULT NULL,
    gender             VARCHAR(6)   DEFAULT NULL,
    major              VARCHAR(20)  DEFAULT NULL,
    created_date       TIMESTAMP    DEFAULT NULL,
    modified_date      TIMESTAMP    DEFAULT NULL,
    PRIMARY KEY (id)
);

CREATE TABLE post
(
    id                 BIGINT NOT NULL AUTO_INCREMENT,
    posted_user_id     BIGINT        NOT NULL,
    title              VARCHAR(55)   DEFAULT NULL,
    content            VARCHAR(255)  DEFAULT NULL,
    chat_url           VARCHAR(100)  DEFAULT NULL,
    major              VARCHAR(20)   DEFAULT NULL,
    study_person       INTEGER       DEFAULT NULL,
    filtered_gender    VARCHAR(6)    DEFAULT NULL,
    study_way          VARCHAR(6)    DEFAULT NULL,
    penalty            INTEGER       DEFAULT NULL,
    close              TINYINT       DEFAULT 0,
    study_start_date   DATE          DEFAULT NULL,
    study_end_date     DATE          DEFAULT NULL,
    remaining_seat     INTEGER       DEFAULT NULL,
    created_date       TIMESTAMP     DEFAULT NULL,
    modified_date      TIMESTAMP     DEFAULT NULL,
    PRIMARY KEY (id)
);

CREATE TABLE study
(
    id                 BIGINT NOT NULL AUTO_INCREMENT,
    posted_user_id     BIGINT        NOT NULL,
    title              VARCHAR(55)   DEFAULT NULL,
    content            VARCHAR(255)  DEFAULT NULL,
    study_start_date   DATE          DEFAULT NULL,
    study_end_date     DATE          DEFAULT NULL,
    chat_url           VARCHAR(100)  DEFAULT NULL,
    created_date       TIMESTAMP     DEFAULT NULL,
    modified_date      TIMESTAMP     DEFAULT NULL,
    PRIMARY KEY (id)
);

CREATE TABLE alarm
(
    id                 BIGINT NOT NULL AUTO_INCREMENT,
    post_id            BIGINT        NOT NULL,
    alarm_category     VARCHAR(10)   DEFAULT NULL,
    title              VARCHAR(55)  DEFAULT NULL,
    created_date       TIMESTAMP     DEFAULT NULL,
    modified_date      TIMESTAMP     DEFAULT NULL,
    PRIMARY KEY (id)
);

CREATE TABLE bookmark
(
    id                 BIGINT NOT NULL AUTO_INCREMENT,
    post_id            BIGINT        NOT NULL,
    user_id            BIGINT        NOT NULL,
    created_date       TIMESTAMP     DEFAULT NULL,
    modified_date      TIMESTAMP     DEFAULT NULL,
    PRIMARY KEY (id)
);