package com.solocoding.blog.appConfig;

import org.springframework.context.annotation.Configuration;
import javax.sql.DataSource;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;

@Configuration
public class DatabaseConfig {
    @Value("${spring.datasource.username}")
    private String postgreUserName;
    @Value("${spring.datasource.password}")
    private String postgrePassword;
    @Value("${spring.datasource.url}")
    private String postgreUrl;

    @Bean("postgre")
    public DataSource createPostgredDataSource() {

        HikariConfig config = new HikariConfig();
        config.setUsername(postgreUserName);
        config.setPassword(postgrePassword);
        config.setJdbcUrl(postgreUrl);

        return new HikariDataSource(config);

    }
}