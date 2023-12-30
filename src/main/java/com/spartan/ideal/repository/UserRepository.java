package com.spartan.ideal.repository;

import com.spartan.ideal.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Integer> {
}
