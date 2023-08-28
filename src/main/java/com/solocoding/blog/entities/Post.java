package com.solocoding.blog.entities;

import org.hibernate.tool.schema.spi.SqlScriptException;
import java.sql.ResultSet;
import java.sql.Timestamp;
import javax.persistence.Entity;
import lombok.Data;
import lombok.NoArgsConstructor;
@Data
@NoArgsConstructor
@Entity
public class Post {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    private String postTitle;
    private String body;
    private Timestamp postDate;
    private int deleted;
    private long ownerId;
    @Column(nullable=true)
    private String commentId;
    // Getters, Setters, Constructors, etc.
    private Post(ResultSet result) throws SqlScriptException{
      this.id=result.getLong("id");
      this.postTitle=result.getString("post_title");
      this.body=result.getString("body");
      this.postDate= result.getTimestamp("post_date");
      this.deleted=result.getInt("deleted");
      this.ownerId=result.getLong("owner_id");
    }
}
