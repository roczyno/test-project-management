package com.roczyno.project_management.repository;

import com.roczyno.project_management.model.Issue;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface IssueRepository extends JpaRepository<Issue, Long> {
   List<Issue> findByProjectId(Long projectId);
}
