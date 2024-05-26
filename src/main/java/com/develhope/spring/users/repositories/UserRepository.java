package com.develhope.spring.users.repositories;

import com.develhope.spring.users.models.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
}
