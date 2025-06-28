package com.roczyno.project_management.util;

import com.roczyno.project_management.dto.response.IssueResponse;
import com.roczyno.project_management.model.Issue;
import org.springframework.stereotype.Service;

@Service
public class IssueMapper {
	public IssueResponse mapToIssueResponse(Issue savedIssue) {
		return new IssueResponse(
				savedIssue.getId(),
				savedIssue.getTitle(),
				savedIssue.getDescription(),
				savedIssue.getStatus(),
				savedIssue.getPriority(),
				savedIssue.getDueDate(),
				savedIssue.getTags()
		);
	}
}
