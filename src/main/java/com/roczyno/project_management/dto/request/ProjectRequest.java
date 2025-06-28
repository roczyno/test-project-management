package com.roczyno.project_management.dto.request;

import java.util.List;

public record ProjectRequest(
		String name,
		String description,
		String category,
		List<String> tags) {
}
