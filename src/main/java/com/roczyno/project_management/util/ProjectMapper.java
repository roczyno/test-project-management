package com.roczyno.project_management.util;

import com.roczyno.project_management.dto.response.ProjectResponse;
import com.roczyno.project_management.model.Project;
import org.springframework.stereotype.Service;

@Service
public class ProjectMapper {
	public ProjectResponse mapToProjectResponse(Project savedProject) {
		return new ProjectResponse(
				savedProject.getId(),
				savedProject.getName(),
				savedProject.getDescription(),
				savedProject.getCategory(),
				savedProject.getTags()
		);
	}
}
