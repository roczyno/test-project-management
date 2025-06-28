package com.roczyno.project_management.repository;

import com.roczyno.project_management.model.Message;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface MessageRepository extends JpaRepository<Message, Long> {
    List<Message> findByChatIdOrderByCreatedAtDesc(Long chatId);
}
