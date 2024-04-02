CREATE TABLE `sticker`
(
    `id`    BIGINT  NOT NULL AUTO_INCREMENT,
    `sticker_group`    VARCHAR(100) NOT NULL,
    `workspace_id`  VARCHAR(10)     NOT NULL,
    `image_url`     VARCHAR(200)    NOT NULL,
    `status`        VARCHAR(30)     NOT NULL,
    `priority`      VARCHAR(10)     NOT NULL,
    `created_at`    DATETIME(6)     DEFAULT NULL,
    `updated_at`    DATETIME(6)     DEFAULT NULL,
    PRIMARY KEY (`id`)

) ENGINE = InnoDB;

CREATE TABLE `sticker_action`
(
    `id`    BIGINT  NOT NULL AUTO_INCREMENT,
    `sticker_group`     VARCHAR(50) NOT NULL,
    `workspace_id`      VARCHAR(100) NOT NULL,
    `sticker_ids`       JSON,
    `account_id`        BIGINT  NOT NULL,
    `target_id`         BIGINT  NOT NULL,
    `created_at`        DATETIME(6)     DEFAULT NULL,
    `updated_at`        DATETIME(6)     DEFAULT NULL,
    PRIMARY KEY (`id`),
    KEY `uni_sticker_action_1` (`account_id`, `target_id`, `sticker_group`, `workspace_id`)

) ENGINE = InnoDB;
