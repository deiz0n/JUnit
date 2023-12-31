package com.deiz0n.junit.config;

import com.deiz0n.junit.domain.User;
import com.deiz0n.junit.repositories.UserRepository;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

import java.util.List;

@Configuration
@Profile("local")
public class LocalConfig {

    private UserRepository repository;

    public LocalConfig(UserRepository repository) {
        this.repository = repository;
    }

    @Bean
    public List<User> startDB() {
        var user = new User(1
                ,"Eduardo"
                ,"eduardo@gmail.com"
                ,"123");
        var user2 = new User(2
                ,"Dudu"
                ,"dudu@gmail.com"
                ,"321");
        return repository.saveAll(List.of(user, user2));
    }

}
