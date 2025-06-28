package com.roczyno.project_management.dto.response;

import java.time.LocalDate;
import java.util.List;

public record IssueResponse(
		 Long id,
		 String title,
		 String description,
		 String status,
		 String priority,
		 LocalDate dueDate,
		 List<String> tags) {
}
