package com.solocoding.blog.entities;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Comment
 */
@Entity
@Data
@NoArgsConstructor
public class Comment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String comment;
    private Long commenterId;
    private Timestamp commentDate;
    private int deleted;

    public Comment (ResultSet result) throws SQLException {
        this.id = result.getLong("id");
        this.comment = result.getString("comment");
        this.commentDate = result.getTimestamp("comment_date");
        this.deleted = result.getInt("deleted");
        this.commenterId = result.getLong("commenter_id");
    }

}