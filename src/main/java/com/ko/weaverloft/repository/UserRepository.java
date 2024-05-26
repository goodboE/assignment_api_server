package com.ko.weaverloft.repository;

import com.ko.weaverloft.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
}
