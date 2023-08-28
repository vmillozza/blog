package com.solocoding.blog;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.List;
import java.util.Optional;

import com.solocoding.blog.entities.Comment;
import com.solocoding.blog.entities.Post;
import com.solocoding.blog.queries.interfaces.PostQueries;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import lombok.extern.slf4j.Slf4j;

@SpringBootTest
@Slf4j
class JdbcClassicTest {

	@Autowired
	private PostQueries postQueries;

	@Test
	public void  testQuery() {

		System.out.println(":::: Insert result ::::");
		postQueries.readAll().ifPresent(temp -> temp.forEach(System.out::println));

		Optional<Post> post = postQueries.readPost(3);
		if (post.isPresent()) {
			log.info("read result ::: {}", post.get().toString());
			post.get().setPostTitle("updated post");
			log.info("update result ::: {}", postQueries.updatePost(post.get()));
			log.info("update result ::: {}", postQueries.readPost(3).get());
		}

		log.info("delete result ::: {}", postQueries.deletePost(3));
		Optional<List<Post>> allAfter = postQueries.readAll();
		if (allAfter.isPresent()) {
			log.info("read all count ::: {}", allAfter.get().size());
		}

		assertEquals(2, allAfter.get().size());
	}

	@Test
	public void testTransactionComment() {
		Comment toAdd = new Comment();
		toAdd.setComment("secondo commento test");
		toAdd.setCommenterId((long)222);

		log.info("Insert comment result :::: {}", postQueries.insertComment(2, toAdd));
		log.info("Reading post 2 with comment {}", postQueries.readPost(2).get().toString());

		assertEquals(postQueries.readPost(2).get().getCommentId(), "1,2,");
	}

}
