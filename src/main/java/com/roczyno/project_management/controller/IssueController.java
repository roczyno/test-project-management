package com.roczyno.project_management.controller;

import com.roczyno.project_management.request.IssueRequest;
import com.roczyno.project_management.service.IssueService;
import com.roczyno.project_management.service.UserService;
import com.roczyno.project_management.util.ResponseHandler;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/issues")
@RequiredArgsConstructor
@SecurityRequirement(name = "bearerAuth")
public class IssueController {
    private final IssueService issueService;
    private final UserService userService;

    @GetMapping("/{issueId}")
    public ResponseEntity<Object> getIssue(@PathVariable Long issueId){
        return ResponseHandler.successResponse(issueService.getIssue(issueId), HttpStatus.OK);
    }

    @GetMapping("/project/{projectId}")
    public ResponseEntity<Object> getIssueByProjectId(@PathVariable Long projectId) {
        return ResponseHandler.successResponse(issueService.getIssuesByProjectId(projectId),HttpStatus.OK) ;
    }
    @PostMapping("/project/{projectId}")
    public ResponseEntity<Object> createIssue(@RequestBody IssueRequest req,@PathVariable Long projectId,
                                              @RequestHeader("Authorization") String jwt) {
        return ResponseHandler.successResponse(issueService.createIssue(req,projectId,userService.findUserProfileByJwt(jwt)),
                HttpStatus.OK);
    }
    @DeleteMapping("/delete/{issueId}")
    public ResponseEntity<Object> deleteIssue(@PathVariable Long issueId,@RequestHeader("Authorization") String jwt) {
        return ResponseHandler.successResponse(issueService.deleteIssue(issueId,
                userService.findUserProfileByJwt(jwt).getId()),HttpStatus.OK);
    }
    @PostMapping("/add_user/{issueId}")
    public ResponseEntity<Object> addUserToIssue(@PathVariable Long issueId,@RequestHeader("Authorization") String jwt) {
        return ResponseHandler.successResponse(issueService.addUserToIssue(userService.findUserProfileByJwt(jwt).getId(),
                issueId),HttpStatus.OK);
    }

    @PutMapping("/update/{issueId}/{status}")
    public ResponseEntity<Object> updateIssueStatus(@PathVariable Long issueId, @PathVariable String status) {
        return ResponseHandler.successResponse(issueService.updateIssueStatus(issueId,status),HttpStatus.OK);
    }





}
