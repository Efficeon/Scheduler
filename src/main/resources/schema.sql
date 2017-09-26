CREATE DATABASE IF NOT EXISTS scheduller_db;

USE scheduller_db;

CREATE TABLE IF NOT EXISTS headers
(
    header_id     INT PRIMARY KEY AUTO_INCREMENT,
    content_type  VARCHAR(255) NOT NULL,
    accept        VARCHAR(255) NOT NULL,
    authorization VARCHAR(255) NOT NULL
);

CREATE TABLE IF NOT EXISTS tasks
(
    task_id    INT PRIMARY KEY AUTO_INCREMENT,
    header_id  INT NOT NULL,
    method     VARCHAR(255) NOT NULL,
    url        VARCHAR(255) NOT NULL,
    data       VARCHAR(255) NOT NULL,

    FOREIGN KEY (header_id) REFERENCES headers (header_id)
);

CREATE TABLE IF NOT EXISTS jobs
(
    job_id         INT PRIMARY KEY AUTO_INCREMENT,
    task_id        INT NOT NULL,
    start_time     TIMESTAMP DEFAULT CURRENT_TIMESTAMP NOT NULL,
    end_time       TIMESTAMP DEFAULT CURRENT_TIMESTAMP NOT NULL,
    scheduled_at   CHAR(20) NOT NULL,
    type           CHAR(10) NOT NULL,
    timezone       VARCHAR(255) NOT NULL,
    callback_url   TEXT,
    execute_times  INT,

    FOREIGN KEY (task_id)   REFERENCES tasks   (task_id)
);






