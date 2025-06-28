package com.roczyno.project_management.request;

import lombok.Data;

@Data
public class SendMessageRequest {
    private String content;
    private Long senderId;
    private Long chatId;
}
