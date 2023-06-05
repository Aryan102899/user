DROP TABLE IF EXISTS PCCW_USER;
CREATE TABLE PCCW_USER
(
    ID          bigint PRIMARY KEY AUTO_INCREMENT,
    name   VARCHAR(50) NOT NULL COMMENT '用户名',
    password    VARCHAR(50) NOT NULL COMMENT '密码（加盐）',
    email       VARCHAR(70) DEFAULT NULL COMMENT '邮箱',
    mobile      VARCHAR(20) DEFAULT NULL COMMENT '电话',
    status      int          DEFAULT 0,
    create_time timestamp,
    update_time timestamp
);