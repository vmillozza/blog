package com.solocoding.blog.queries;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;

/**
 * QueriesConst
 */
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class QueriesConst {

    public static final String INSERT = "Insert into post (post_title, body, owner_id, deleted, post_date) values (?,?,?,?,?)";
    public static final String READ_ALL =  "Select * from post where deleted = 0";
    public static final String READ_BY_ID = "Select * from post where id = ? and deleted = 0";
    public static final String DELETE = "Update post set deleted = 1 where id = ?";
    public static final String UPDATE = "Update post set post_title = ? , body= ? where id = ?";
    public static final String INSERT_COMMENT = "Insert into comment (comment, commenter_id, comment_date, deleted) values (?,?,?,?)";
    public static final String UPDATE_POST_ID_COMMENT = "Update post set comment_id = ? where id = ?";

}