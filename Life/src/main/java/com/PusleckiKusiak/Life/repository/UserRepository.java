package com.PusleckiKusiak.Life.repository;
import com.PusleckiKusiak.Life.entity.User;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
@Repository
public interface UserRepository extends JpaRepository<User, Long> {
	 User findByUsername(String username);
	 boolean existsUserByUsername(String username);
}
