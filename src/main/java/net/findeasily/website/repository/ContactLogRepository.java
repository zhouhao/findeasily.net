package net.findeasily.website.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import net.findeasily.website.entity.ContactLog;

public interface ContactLogRepository extends JpaRepository<ContactLog, Integer>, JpaSpecificationExecutor<ContactLog> {

}