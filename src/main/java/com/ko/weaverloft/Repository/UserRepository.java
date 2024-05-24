package com.ko.weaverloft.Repository;

import com.ko.weaverloft.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
}
