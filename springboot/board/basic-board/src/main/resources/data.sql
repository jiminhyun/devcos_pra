CREATE DATABASE java_basic
    CHARACTER SET utf8mb4
    COLLATE utf8mb4_unicode_ci;

create table member (
    id bigint auto_increment primary key,
    user_id varchar(50) not null,
    password varchar(50) not null,
    user_name varchar(50) not null
);

create table board
(
    id bigint auto_increment primary key,
    title varchar(200) not null,
    content TEXT not null,
    user_id varchar(50) not null,
    file_path varchar(255),
    created DATETIME DEFAULT CURRENT_TIMESTAMP
);