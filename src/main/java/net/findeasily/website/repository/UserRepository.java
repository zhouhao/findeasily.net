package net.findeasily.website.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import net.findeasily.website.domain.User;

public interface UserRepository extends JpaRepository<User, Long> {

    Optional<User> findOneByEmail(String email);
}
