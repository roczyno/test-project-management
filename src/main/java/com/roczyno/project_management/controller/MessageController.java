package com.roczyno.project_management.controller;

import com.roczyno.project_management.request.SendMessageRequest;
import com.roczyno.project_management.service.MessageService;
import com.roczyno.project_management.service.UserService;
import com.roczyno.project_management.util.ResponseHandler;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequiredArgsConstructor
@RequestMapping("/api/message")
@SecurityRequirement(name = "bearerAuth")
public class MessageController {


    private final MessageService messageService;
    private final UserService userService;

    @PostMapping("/send/chat/{chatId}")
    public ResponseEntity<Object> sendMessage(@PathVariable Long chatId, @RequestBody SendMessageRequest req,
                                               @RequestHeader("Authorization") String jwt)  {

        return ResponseHandler.successResponse(messageService.sendMessage(req.getContent(),
                userService.findUserProfileByJwt(jwt).getId(),chatId),HttpStatus.OK);
    }

    @GetMapping("/project/{projectId}")
    public ResponseEntity<Object> getMessagesByProjectId(@PathVariable Long projectId){
        return ResponseHandler.successResponse(messageService.getMessagesByProjectId(projectId), HttpStatus.OK);
    }

}
