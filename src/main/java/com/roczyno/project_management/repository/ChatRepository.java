package com.roczyno.project_management.repository;

import com.roczyno.project_management.model.Chat;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ChatRepository extends JpaRepository<Chat, Long> {
}
