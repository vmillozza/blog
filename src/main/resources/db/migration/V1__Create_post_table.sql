-- V1__Create_post_table.sql
CREATE TABLE post (
    id SERIAL PRIMARY KEY,
    post_title VARCHAR(100) NOT NULL,
    body VARCHAR(256) NOT NULL,
    post_date timestamp ,
    deleted smallint,
    owner_id integer,
    commenter_id integer
);
