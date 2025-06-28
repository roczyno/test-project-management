package com.roczyno.project_management.service;

import com.roczyno.project_management.model.Chat;
import com.roczyno.project_management.model.Project;
import com.roczyno.project_management.model.User;

public interface ChatService {
    Chat createChat(Project project);

    void addUserToChat(Chat chat, User user);

    void removeUserFromChat(Chat chat, User user);
}
