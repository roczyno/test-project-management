package com.roczyno.project_management.service;

import com.roczyno.project_management.exception.CommentException;
import com.roczyno.project_management.model.Comment;
import com.roczyno.project_management.model.Issue;
import com.roczyno.project_management.model.User;
import com.roczyno.project_management.projections.CommentProjection;
import com.roczyno.project_management.repository.CommentRepository;
import com.roczyno.project_management.util.AppConstants;
import com.roczyno.project_management.util.ErrorConstants;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class CommentServiceImpl implements CommentService{
    private final CommentRepository commentRepository;
    private final UserService userService;
    private final IssueService issueService;

    @Override
    @Transactional
    public String createComment(Comment comment, Long userId, Long issueId) {
        Issue issue=issueService.findIssueById(issueId);
        User user=userService.findUserById(userId);
        Comment newComment= new Comment();
        newComment.setComment(comment.getComment());
        newComment.setUser(user);
        newComment.setIssue(issue);
        newComment.setCreatedDate(LocalDateTime.now());
        commentRepository.save(newComment);
        return "comment created";
    }

    @Override
    public String deleteComment(Long commentId, Long userId)  {
        User user=userService.findUserById(userId);
        Comment comment=findCommentById(commentId);
        if(!comment.getUser().getId().equals(user.getId())){
            throw new CommentException(ErrorConstants.CANT_DELETE_COMMENT);
        }
        commentRepository.delete(comment);
        return AppConstants.COMMENT_DELETED;



    }

    @Override
    public List<CommentProjection> findCommentsByIssueId(Long issueId) {
        return commentRepository.findByIssueId(issueId);
    }

    @Override
    public Comment findCommentById(Long commentId)  {
        return commentRepository.findById(commentId).orElseThrow(()-> new CommentException(ErrorConstants.COMMENT_NOT_FOUND));
    }

    @Override
    public CommentProjection getComment(Long commentId) {
        return commentRepository.getCommentProjectById(commentId);
    }
}
