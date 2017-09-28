CREATE DATABASE IF NOT EXISTS scheduler_db;

USE scheduler_db;

CREATE TABLE IF NOT EXISTS headers
(
    header_id     INT PRIMARY KEY AUTO_INCREMENT,
    content_type  VARCHAR(255),
    accept        VARCHAR(255),
    authorization VARCHAR(255) NOT NULL
);

CREATE TABLE IF NOT EXISTS tasks
(
    task_id    INT PRIMARY KEY AUTO_INCREMENT,
    header_id  INT,
    method     VARCHAR(255) NOT NULL,
    url        VARCHAR(255) NOT NULL,
    data       VARCHAR(255),

    FOREIGN KEY (header_id) REFERENCES headers (header_id)
);

CREATE TABLE IF NOT EXISTS jobs
(
    job_id         INT PRIMARY KEY AUTO_INCREMENT,
    task_id        INT,
    start_time     DATETIME ,
    end_time       DATETIME ,
    scheduled_at   CHAR(20),
    type           CHAR(10),
    timezone       VARCHAR(255),
    callback_url   TEXT,
    execute_times  INT,
    next_run_at    DATETIME ,
    last_run_at    DATETIME ,

    FOREIGN KEY (task_id)   REFERENCES tasks   (task_id)
);






