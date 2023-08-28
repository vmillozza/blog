-- V1__Create_post_table.sql
CREATE TABLE comment (
    id SERIAL PRIMARY KEY,
    comment VARCHAR(256) NOT NULL,
    comment_date TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
    deleted BOOLEAN NOT NULL DEFAULT FALSE,
    commenter_id INTEGER
    -- Se esiste una tabella 'users', potresti voler aggiungere un vincolo di chiave esterna come questo:
    -- FOREIGN KEY (commenter_id) REFERENCES users(id)
);
