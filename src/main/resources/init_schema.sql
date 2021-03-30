CREATE SCHEMA IF NOT EXISTS daymoji DEFAULT CHARACTER SET utf8;
USE daymoji;

CREATE TABLE IF NOT EXISTS diary
(
    id                 BIGINT           NOT NULL AUTO_INCREMENT,
    title              VARCHAR(255),
    contents           VARCHAR(255),
    latitude           DOUBLE PRECISION NOT NULL,
    longitude          DOUBLE PRECISION NOT NULL,
    name_of_location   VARCHAR(255),
    opened             BIT              NOT NULL,
    created_date_time  DATETIME(6),
    modified_date_time DATETIME(6),
    emoji_id           BIGINT,
    PRIMARY KEY (id)
) ENGINE = InnoDB;

CREATE TABLE IF NOT EXISTS emoji
(
    id              BIGINT NOT NULL AUTO_INCREMENT,
    code_of_english VARCHAR(255),
    code_of_korean  VARCHAR(255),
    PRIMARY KEY (id)
) ENGINE = InnoDB;

CREATE TABLE IF NOT EXISTS today_emoji
(
    id       BIGINT NOT NULL AUTO_INCREMENT,
    diary_id BIGINT,
    emoji_id BIGINT,
    PRIMARY KEY (id)
) ENGINE = InnoDB;

ALTER TABLE diary
    ADD
        FOREIGN KEY (emoji_id)
            REFERENCES emoji (id);

ALTER TABLE today_emoji
    ADD
        FOREIGN KEY (emoji_id)
            REFERENCES emoji (id);

ALTER TABLE today_emoji
    ADD
        FOREIGN KEY (diary_id)
            REFERENCES diary (id);
