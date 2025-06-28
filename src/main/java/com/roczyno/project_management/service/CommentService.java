package com.roczyno.project_management.service;

import com.roczyno.project_management.model.Comment;
import com.roczyno.project_management.projections.CommentProjection;

import java.util.List;

public interface CommentService {
    String createComment(Comment comment,Long userId,Long issueId);
    String deleteComment(Long commentId,Long userId) ;
    List<CommentProjection> findCommentsByIssueId(Long issueId);
    Comment findCommentById(Long commentId);
    CommentProjection getComment(Long commentId);
}
