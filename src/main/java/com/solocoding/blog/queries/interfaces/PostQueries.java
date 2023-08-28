package com.solocoding.blog.queries.interfaces;

import java.util.List;
import java.util.Optional;

import com.solocoding.blog.entities.Comment;
import com.solocoding.blog.entities.Post;

/**
 * PostQueries
 */
public interface PostQueries {

    /**
     * save a post in db
     */
    int insertPost (Post input);

    Optional <Post> readPost (long id);

    Optional <List<Post>> readAll();

    /**
     * delete a post by setting deleted to zero
     */
    int deletePost (long id);

    int updatePost (Post input);

    /**
     * insert comment in a transaction 
     */

    int insertComment(long idPost, Comment comment);

}