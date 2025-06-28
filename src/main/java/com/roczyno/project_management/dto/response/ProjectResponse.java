package com.roczyno.project_management.dto.response;

import java.util.List;

public record ProjectResponse(
		Long id,
		String name,
		String description,
		String category,
		List<String> tags
) {
}
