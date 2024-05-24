package com.ko.weaverloft.util;

import com.ko.weaverloft.Repository.UserRepository;
import com.ko.weaverloft.entity.User;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class DataInitializer {

    @Autowired
    private UserRepository userRepository;


    @PostConstruct
    public void initData() {
        userRepository.save(new User("delectus aut autem"));
    }
}
