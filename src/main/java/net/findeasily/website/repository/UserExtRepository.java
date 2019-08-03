package net.findeasily.website.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import net.findeasily.website.entity.UserExt;

public interface UserExtRepository extends JpaRepository<UserExt, String>, JpaSpecificationExecutor<UserExt> {

}