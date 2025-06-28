package com.roczyno.project_management.controller;

import com.roczyno.project_management.model.Comment;
import com.roczyno.project_management.service.CommentService;
import com.roczyno.project_management.service.UserService;
import com.roczyno.project_management.util.ResponseHandler;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/comment")
@RequiredArgsConstructor
@SecurityRequirement(name = "bearerAuth")
public class CommentController {

    private final CommentService commentService;
    private final UserService userService;

    @PostMapping("/issue/{issueId}")
    public ResponseEntity<Object> addComment(@PathVariable Long issueId,@RequestBody Comment req,
                                             @RequestHeader("Authorization") String jwt){
        return ResponseHandler.successResponse(commentService.createComment(req,
                userService.findUserProfileByJwt(jwt).getId(),issueId),HttpStatus.OK);
    }
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Object> deleteComment(@PathVariable Long id, @RequestHeader("Authorization") String jwt)  {
      return ResponseHandler.successResponse(commentService.deleteComment(id,userService.findUserProfileByJwt(jwt).getId()),
              HttpStatus.OK);
    }
    @GetMapping("/issue/{issueId}")
    public ResponseEntity<Object> findCommentsByIssueId(@PathVariable Long issueId) {
        return ResponseHandler.successResponse(commentService.findCommentsByIssueId(issueId),HttpStatus.OK);
    }

   

}
