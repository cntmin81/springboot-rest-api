package cntmin81.github.io.springbootrestapi.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import cntmin81.github.io.springbootrestapi.entity.User;

public interface UserRepo extends JpaRepository<User, Long> {
    User findByName(String name);
    User findByEmail(String email);
}
