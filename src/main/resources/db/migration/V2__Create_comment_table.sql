-- V1__Create_post_table.sql
CREATE TABLE post (
    id SERIAL PRIMARY KEY,
    comment VARCHAR(256) NOT NULL,
    comment_date timestamp ,
    deleted smallint,
    commenter_id integer

);
