package com.deiz0n.junit.repositories;

import com.deiz0n.junit.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
}
