package com.roczyno.project_management.service;

import com.roczyno.project_management.model.Chat;
import com.roczyno.project_management.model.Project;
import com.roczyno.project_management.model.User;
import com.roczyno.project_management.repository.ChatRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
public class ChatImpl implements ChatService {
    private final ChatRepository chatRepository;
    @Override
    public Chat createChat(Project project) {
        Chat chat= Chat.builder()
                .project(project)
                .name(project.getName())
                .createdAt(LocalDateTime.now())
                .build();
        return chatRepository.save(chat);
    }

    @Override
    public void addUserToChat(Chat chat, User user) {
        chat.getUsers().add(user);
        chatRepository.save(chat);
    }

    @Override
    public void removeUserFromChat(Chat chat, User user) {
        chat.getUsers().remove(user);
        chatRepository.save(chat);
    }
}
