package com.solocoding.blog;

import java.sql.Timestamp;
import java.time.Instant;

import com.solocoding.blog.entities.Comment;
import com.solocoding.blog.entities.Post;
import com.solocoding.blog.queries.interfaces.PostQueries;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import lombok.extern.slf4j.Slf4j;

@SpringBootApplication
@Slf4j
public class BlogApplication implements CommandLineRunner {

	@Autowired
	private PostQueries postQueries; 
	public static void main(String[] args) {
		SpringApplication.run(BlogApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		
		Post input = new Post();
		input.setPostTitle("Primo post");
		input.setBody("primo body");
		input.setOwnerId(111);
		input.setPostDate(new Timestamp(Instant.now().toEpochMilli()));
		input.setDeleted(0);

		log.info("Insert result 1 :::: {}", postQueries.insertPost(input));

		input.setPostTitle("secondo ppst");
		input.setBody("Secondo body");
		log.info("Insert result 2 :::: {}", postQueries.insertPost(input));

		input.setPostTitle("terzo post");
		input.setBody("terzo body");
		log.info("Insert result 3 :::: {}", postQueries.insertPost(input));

		Comment comm = new Comment();
		comm.setComment("primo comment");
		comm.setCommenterId((long) 222);
		log.info("Insert first comment :::: {}", postQueries.insertComment(2, comm));

	}

}
