package com.roczyno.project_management.service;

import com.roczyno.project_management.dto.response.IssueResponse;
import com.roczyno.project_management.model.Issue;
import com.roczyno.project_management.model.User;
import com.roczyno.project_management.request.IssueRequest;

import java.util.List;

public interface IssueService {
    IssueResponse createIssue(IssueRequest issue, Long projectId, User user);
   IssueResponse getIssue(Long id);
    String deleteIssue(Long id,Long userId);
    List<IssueResponse> getIssuesByProjectId(Long projectId);
    IssueResponse addUserToIssue(Long userId, Long issueId);
    IssueResponse updateIssueStatus(Long issueId,String status);
    Issue findIssueById(Long id);




}
