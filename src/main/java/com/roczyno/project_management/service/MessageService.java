package com.roczyno.project_management.service;

import com.roczyno.project_management.model.Message;

import java.util.List;

public interface MessageService {
    Message sendMessage(String content,Long senderId,Long chatId);
    List<Message> getMessagesByProjectId(Long projectId);
}
