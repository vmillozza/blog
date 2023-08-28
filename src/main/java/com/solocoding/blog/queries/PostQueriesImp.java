package com.solocoding.blog.queries;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.sql.Timestamp;
import java.time.Instant;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javax.sql.DataSource;

import com.solocoding.blog.entities.Comment;
import com.solocoding.blog.entities.Post;
import com.solocoding.blog.queries.interfaces.PostQueries;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.crossstore.ChangeSetPersister.NotFoundException;
import org.springframework.stereotype.Repository;

import lombok.extern.slf4j.Slf4j;

/**
 * PostQueriesImp
 */
@Slf4j
@Repository
public class PostQueriesImp implements PostQueries {

    private final DataSource db;

    public PostQueriesImp(@Qualifier("postgre") DataSource db) {
        this.db = db;
    }

    @Override
    public int insertPost(Post input) {
        int res;

        try (Connection conn = db.getConnection();
                PreparedStatement stmt = conn.prepareStatement(QueriesConst.INSERT);) {
            stmt.setString(1, input.getPostTitle());
            stmt.setString(2, input.getBody());
            stmt.setLong(3, input.getOwnerId());
            stmt.setInt(4, input.getDeleted());
            stmt.setTimestamp(5, input.getPostDate());

            res = stmt.executeUpdate();
            return res;
        } catch (Exception e) {
            log.error("Insert error {}", e.getMessage());
        }
        return 0;
    }

    @Override
    public Optional<Post> readPost(long id) {
        ResultSet res;
        Post output = null;

        try (Connection conn = db.getConnection();
                PreparedStatement stmt = conn.prepareStatement(QueriesConst.READ_BY_ID);) {
            stmt.setLong(1, id);
            res = stmt.executeQuery();
            while (res.next()) {
                output = new Post(res);
            }
        } catch (Exception e) {
            log.error("Read error {}", e.getMessage());
        }
        return Optional.ofNullable(output);
    }

    @Override
    public Optional<List<Post>> readAll() {
        ResultSet res;
        List<Post> output = null;

        try (Connection conn = db.getConnection();
                PreparedStatement stmt = conn.prepareStatement(QueriesConst.READ_ALL);) {
            res = stmt.executeQuery();
            output = new ArrayList<>();
            while (res.next()) {
                output.add(new Post(res));
            }
        } catch (Exception e) {
            log.error("Error reading all {}", e.getMessage());
        }
        return Optional.ofNullable(output);
    }

    @Override
    public int deletePost(long id) {
        int res;

        try (Connection conn = db.getConnection();
                PreparedStatement stmt = conn.prepareStatement(QueriesConst.DELETE);) {
            stmt.setLong(1, id);
            res = stmt.executeUpdate();
            return res;
        } catch (Exception e) {
            log.error("Error deleting post {}", e.getMessage());
        }
        return 0;
    }

    @Override
    public int updatePost(Post input) {
        int res;

        try (Connection conn = db.getConnection();
                PreparedStatement stmt = conn.prepareStatement(QueriesConst.UPDATE);) {

            stmt.setString(1, input.getPostTitle());
            stmt.setString(2, input.getBody());
            stmt.setLong(3, input.getId());
            res = stmt.executeUpdate();
            return res;
        } catch (Exception e) {
            log.error("Error deleting post {}", e.getMessage());
        }
        return 0;
    }

    @Override
    public int insertComment(long idPost, Comment comment) {
        long idComment = 0;
        int res;

        try (Connection conn = db.getConnection();
                PreparedStatement stmtComment = conn.prepareStatement(QueriesConst.INSERT_COMMENT,
                        Statement.RETURN_GENERATED_KEYS);
                PreparedStatement stmtPost = conn.prepareStatement(QueriesConst.UPDATE_POST_ID_COMMENT);) {

            conn.setAutoCommit(false);
            stmtComment.setString(1, comment.getComment());
            stmtComment.setLong(2, comment.getCommenterId());
            stmtComment.setTimestamp(3, new Timestamp(Instant.now().toEpochMilli()));
            stmtComment.setInt(4, 0);
            stmtComment.execute();
            ResultSet resCom = stmtComment.getGeneratedKeys();
            if (resCom.next()) {
                idComment = resCom.getInt(1);
            }

            Post toUPdate = readPost(idPost).orElseThrow(NotFoundException::new);
            if (toUPdate.getCommentId() != null && idComment != 0) {
                String comments = toUPdate.getCommentId().concat(idComment + ",");
                stmtPost.setString(1, comments);
            } else {
                stmtPost.setString(1, String.valueOf(idComment).concat(","));
            }
            stmtPost.setLong(2, toUPdate.getId());
            res = stmtPost.executeUpdate();

            conn.commit();

            conn.setAutoCommit(true);
            return res;
        } catch (Exception e) {
            log.error("Error transaction comment {}", e.getMessage());
        }
        return 0;
    }

}