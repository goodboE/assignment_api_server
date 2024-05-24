package com.ko.weaverloft.Service;

import com.ko.weaverloft.Repository.UserRepository;
import com.ko.weaverloft.entity.User;
import lombok.RequiredArgsConstructor;
import org.hibernate.validator.internal.constraintvalidators.hv.EmailValidator;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;

    public User findById(Long userId) {
        return userRepository.findById(userId).orElse(null);
    }

    public User save(String name, int age, String city) {
        return userRepository.save(new User(name, age, city));
    }

}
