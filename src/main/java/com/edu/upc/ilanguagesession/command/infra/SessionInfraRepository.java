package com.edu.upc.ilanguagesession.command.infra;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface SessionInfraRepository extends JpaRepository<SessionInfra, String> {
}
