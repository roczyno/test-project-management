package com.roczyno.project_management.service;

import com.roczyno.project_management.dto.request.ProjectRequest;
import com.roczyno.project_management.dto.response.ProjectResponse;
import com.roczyno.project_management.model.Chat;
import com.roczyno.project_management.model.Project;
import com.roczyno.project_management.model.User;

import java.util.List;

public interface ProjectService {
    ProjectResponse createProject(ProjectRequest project, User user);

    ProjectResponse getProject(Long projectId);
    Project findProjectById(Long projectId);

    List<ProjectResponse> getProjectByTeam(User user, String category, String tag);

    String deleteProject(Long projectId, Long userId);

    ProjectResponse updateProject(Long projectId, ProjectRequest project, Long userId);

    String addUserToProject(Long projectId, Long userId);

    String removeUserFromProject(Long projectId, Long userId);

    Chat getChatByProjectId(Long projectId);
    List<ProjectResponse> searchProject(String keyword,User user);

}
