package com.roczyno.project_management.service;

import com.roczyno.project_management.model.Chat;
import com.roczyno.project_management.model.Message;
import com.roczyno.project_management.model.User;
import com.roczyno.project_management.repository.MessageRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class MessageServiceImpl implements MessageService {
	private final MessageRepository messageRepository;
	private final UserService userService;
	private final ProjectService projectService;

	@Override
	public Message sendMessage(String content, Long senderId, Long projectId)  {
		User sender = userService.findUserById(senderId);
		Chat chat = projectService.findProjectById(projectId).getChat();
		Message message = new Message();
		message.setSender(sender);
		message.setChat(chat);
		message.setMessage(content);
		message.setCreatedAt(LocalDateTime.now());
		Message savedMessage = messageRepository.save(message);
		chat.getMessages().add(savedMessage);
		return savedMessage;
	}

	@Override
	public List<Message> getMessagesByProjectId(Long projectId){
		Chat chat = projectService.getChatByProjectId(projectId);
		return messageRepository.findByChatIdOrderByCreatedAtDesc(chat.getId());
	}
}
