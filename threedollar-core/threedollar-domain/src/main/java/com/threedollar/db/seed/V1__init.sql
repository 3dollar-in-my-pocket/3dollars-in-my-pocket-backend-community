CREATE TABLE `sticker`
(
    `id`    BIGINT  NOT NULL AUTO_INCREMENT,
    `sticker_group` VARCHAR(100)    NOT NULL,
    `workspace_id`  VARCHAR(10)     NOT NULL,
    `image_url`     VARCHAR(200)    NOT NULL,
    `status`        VARCHAR(30)     NOT NULL,
    `priority`      INTEGER         NOT NULL,
    `created_at`    DATETIME(6)     DEFAULT NULL,
    `updated_at`    DATETIME(6)     DEFAULT NULL,
    PRIMARY KEY (`id`)

) ENGINE = InnoDB;

CREATE TABLE `sticker_action`
(
    `id`    BIGINT  NOT NULL AUTO_INCREMENT,
    `sticker_group`     VARCHAR(50) NOT NULL,
    `workspace_id`      VARCHAR(100) NOT NULL,
    `sticker_ids`       VARCHAR(500) NOT NULL,
    `account_id`        VARCHAR(100)  NOT NULL,
    `target_id`         VARCHAR(100)  NOT NULL,
    `created_at`        DATETIME(6)     DEFAULT NULL,
    `updated_at`        DATETIME(6)     DEFAULT NULL,
    PRIMARY KEY (`id`),
    UNIQUE KEY `uni_sticker_action_1` (`account_id`, `target_id`, `sticker_group`, `workspace_id`)

) ENGINE = InnoDB;


CREATE TABLE `api_key`
(
    `id`    BIGINT  NOT NULL AUTO_INCREMENT,
    `workspace_id`  VARCHAR(100)    NOT NULL,
    `api_key`       VARCHAR(100)    NOT NULL,
    `description`   VARCHAR(200)    NOT NULL,
    `status`        VARCHAR(30)     NOT NULL,
    `created_at`    DATETIME(6)     DEFAULT NULL,
    `updated_at`    DATETIME(6)     DEFAULT NULL,
    PRIMARY KEY(`id`),
    UNIQUE KEY (`api_key`)
) ENGINE = InnoDB;
