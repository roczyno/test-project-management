package com.roczyno.project_management.service;

import com.roczyno.project_management.dto.request.ProjectRequest;
import com.roczyno.project_management.dto.response.ProjectResponse;
import com.roczyno.project_management.exception.ProjectException;
import com.roczyno.project_management.model.Chat;
import com.roczyno.project_management.model.Project;
import com.roczyno.project_management.model.User;
import com.roczyno.project_management.repository.ProjectRepository;
import com.roczyno.project_management.util.AppConstants;
import com.roczyno.project_management.util.ErrorConstants;
import com.roczyno.project_management.util.ProjectMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ProjectServiceImpl implements ProjectService {

	private final ProjectRepository projectRepository;
	private final UserService userService;
	private final ChatService chatService;
	private final ProjectMapper projectMapper;

	@Override
	@Transactional
	public ProjectResponse createProject(ProjectRequest project, User user) {
		if (project == null || user == null) {
			throw new IllegalArgumentException("Project or User cannot be null.");
		}

		Project createdProject = new Project();
		createdProject.setName(project.name());
		createdProject.setDescription(project.description());
		createdProject.setOwner(user);
		createdProject.setCategory(project.category());
		createdProject.setTags(project.tags());
		createdProject.getTeam().add(user);
		Chat projectChat = chatService.createChat(createdProject);
		createdProject.setChat(projectChat);
		Project savedProject= projectRepository.save(createdProject);

		return projectMapper.mapToProjectResponse(savedProject);

	}


	@Override
	public ProjectResponse getProject(Long projectId) {
		 Project project=findProjectById(projectId);
		 return projectMapper.mapToProjectResponse(project);
	}

	@Override
	public Project findProjectById(Long projectId) {
		return projectRepository.findById(projectId).orElseThrow();
	}

	@Override
	public List<ProjectResponse> getProjectByTeam(User user, String category, String tag) {
		List<Project> projects = projectRepository.findByTeamContainingOrOwner(user, user);
		if (category != null) {
			projects = projects.stream().filter(project -> project.getCategory().equals(category))
					.toList();
		}
		if (tag != null) {
			projects = projects.stream().filter(project -> project.getTags().contains(tag))
					.toList();
		}
		return projects.stream()
				.map(projectMapper::mapToProjectResponse)
				.toList();
	}

	@Override
	public String deleteProject(Long projectId, Long userId) {
		Project project = findProjectById(projectId);
		verifyOwnerShip(project, userId);
		projectRepository.delete(project);
		return AppConstants.PROJECT_DELETE_SUCCESSFUL;
	}

	@Override
	public ProjectResponse updateProject(Long projectId, ProjectRequest updateProject, Long userId) {
		Project project = findProjectById(projectId);
		verifyOwnerShip(project, userId);
		if (updateProject.name() != null) {
			project.setName(updateProject.name());
		}
		if (updateProject.description() != null) {
			project.setDescription(updateProject.description());
		}
		if (updateProject.category() != null) {
			project.setCategory(updateProject.category());
		}
		if (updateProject.tags() != null) {
			project.setTags(updateProject.tags());
		}

		Project savedUpdatedProject=projectRepository.save(project);
		return projectMapper.mapToProjectResponse(savedUpdatedProject);
	}

	@Override
	public String addUserToProject(Long projectId, Long userId) {

		Project project =findProjectById(projectId);
		User user = userService.findUserById(userId);
		if (!project.getTeam().contains(user)) {
			project.getTeam().add(user);
			chatService.addUserToChat(project.getChat(),user);
		}
		projectRepository.save(project);
		return AppConstants.USER_ADDED;

	}

	@Override
	public String removeUserFromProject(Long projectId, Long userId) {
		Project project = findProjectById(projectId);
		User user = userService.findUserById(userId);
		if (project.getTeam().contains(user)) {
			project.getTeam().remove(user);
			chatService.removeUserFromChat(project.getChat(),user);
		}
		projectRepository.save(project);
		return AppConstants.USER_REMOVED_FROM_PROJECT;
	}

	@Override
	public Chat getChatByProjectId(Long projectId) {
		Project project = findProjectById(projectId);
		return project.getChat();
	}

	@Override
	public List<ProjectResponse> searchProject(String keyword, User user) {
		List<Project> projects= projectRepository.findByNameContainingAndTeamContains(keyword, user);
		return projects.stream()
				.map(projectMapper::mapToProjectResponse)
				.toList();
	}

	private void verifyOwnerShip(Project project, Long userId) {
		if (!project.getOwner().getId().equals(userId)) {
			throw new ProjectException(ErrorConstants.NOT_OWNER_OF_PROJECT);
		}
	}
}
