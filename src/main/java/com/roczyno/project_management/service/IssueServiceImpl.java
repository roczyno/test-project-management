package com.roczyno.project_management.service;

import com.roczyno.project_management.dto.response.IssueResponse;
import com.roczyno.project_management.exception.IssueException;
import com.roczyno.project_management.model.Issue;
import com.roczyno.project_management.model.Project;
import com.roczyno.project_management.model.User;
import com.roczyno.project_management.repository.IssueRepository;
import com.roczyno.project_management.request.IssueRequest;
import com.roczyno.project_management.util.AppConstants;
import com.roczyno.project_management.util.ErrorConstants;
import com.roczyno.project_management.util.IssueMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class IssueServiceImpl implements IssueService {
	private final IssueRepository issueRepository;
	private final ProjectService projectService;
	private final UserService userService;
	private final IssueMapper issueMapper;


	@Override
	@Transactional
	public IssueResponse createIssue(IssueRequest issue, Long projectId, User user) {
		Project project = projectService.findProjectById(projectId);
		Issue newIssue = new Issue();
		newIssue.setProject(project);
		newIssue.setTitle(issue.getTitle());
		newIssue.setDescription(issue.getDescription());
		newIssue.setDueDate(issue.getDueDate());
		newIssue.setPriority(issue.getPriority());
		newIssue.setStatus(issue.getStatus());
		Issue savedIssue=issueRepository.save(newIssue);

		return issueMapper.mapToIssueResponse(savedIssue);

	}

	@Override
	public IssueResponse getIssue(Long id) {
		Issue issue= issueRepository.findById(id)
				.orElseThrow(() -> new IssueException(ErrorConstants.ISSUE_NOT_FOUND));
		return issueMapper.mapToIssueResponse(issue);
	}


	@Override
	public String deleteIssue(Long id, Long userId) {
		Issue issue = findIssueById(id);
		issueRepository.deleteById(issue.getId());
         return AppConstants.ISSUE_DELETED;
	}

	@Override
	public List<IssueResponse> getIssuesByProjectId(Long projectId) {
		List<Issue> issues= issueRepository.findByProjectId(projectId);
		return issues.stream()
				.map(issueMapper::mapToIssueResponse)
				.toList();
	}

	@Override
	public IssueResponse addUserToIssue(Long userId, Long issueId){
		User user = userService.findUserById(userId);
		Issue issue = findIssueById(issueId);
		issue.setAssignee(user);
		Issue savedIssue=issueRepository.save(issue);
		return issueMapper.mapToIssueResponse(savedIssue);
	}

	@Override
	public IssueResponse updateIssueStatus(Long issueId, String status){
		Issue issue = findIssueById(issueId);
		issue.setStatus(status);
		Issue updatedIssue= issueRepository.save(issue);
		return issueMapper.mapToIssueResponse(updatedIssue);
	}

	@Override
	public Issue findIssueById(Long id) {
		return issueRepository.findById(id).orElseThrow(() -> new IssueException(ErrorConstants.ISSUE_NOT_FOUND));
	}
}
