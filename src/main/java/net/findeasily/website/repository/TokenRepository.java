package net.findeasily.website.repository;

import java.sql.Timestamp;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import net.findeasily.website.entity.Token;

public interface TokenRepository extends JpaRepository<Token, Integer>, JpaSpecificationExecutor<Token> {
    long deleteByCreateTimeBefore(Timestamp timestamp);
}