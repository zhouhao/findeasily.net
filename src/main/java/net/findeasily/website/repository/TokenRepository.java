package net.findeasily.website.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import net.findeasily.website.entity.Token;

public interface TokenRepository extends JpaRepository<Token, Integer>, JpaSpecificationExecutor<Token> {

}