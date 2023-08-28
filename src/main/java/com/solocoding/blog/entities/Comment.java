package com.solocoding.blog.entities;

import java.sql.ResultSet;
import java.sql.Timestamp;

import org.hibernate.tool.schema.spi.SqlScriptException;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;

@Entity
public class Comment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String comment;
    private long commenterId;
    private Timestamp commentDate;
    private int deleted;

    public Comment(ResultSet result) throws SqlScriptException{
      this.id=result.getLong("id");
      this.comment=result.getString("comment");
      this.commenterId=result.getLong("commenter_id");
      this.commentDate= result.getTimestamp("commenter_date");
      this.deleted=result.getInt("deleted");
      
    }

}
