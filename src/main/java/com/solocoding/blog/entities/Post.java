package com.solocoding.blog.entities;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Post
 */
@Data
@NoArgsConstructor
@Entity
public class Post {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String postTitle;
    private String body;
    private Timestamp postDate;
    private int deleted;
    private long ownerId;
    @Column(nullable = true)
    private String commentId;

    public Post (ResultSet result) throws SQLException {
        this.id = result.getLong("id");
        this.postTitle = result.getString("post_title");
        this.body = result.getString("body");
        this.postDate = result.getTimestamp("post_date");
        this.deleted = result.getInt("deleted");
        this.ownerId = result.getLong("owner_id");
        this.commentId = result.getString("comment_id");
    }
    
}