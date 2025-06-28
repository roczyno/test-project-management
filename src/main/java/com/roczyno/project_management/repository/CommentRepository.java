package com.roczyno.project_management.repository;

import com.roczyno.project_management.model.Comment;
import com.roczyno.project_management.projections.CommentProjection;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface CommentRepository extends JpaRepository<Comment, Long> {
    List<CommentProjection> findByIssueId(Long issueId);

	@Query("select  c from Comment c where c.id=:commentId")
	CommentProjection getCommentProjectById(@Param("commentId") Long commentId);
}
