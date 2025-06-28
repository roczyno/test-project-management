package com.roczyno.project_management.projections;

import java.time.LocalDateTime;

public interface CommentProjection {
	String getComment();
	LocalDateTime getCreatedDate();
	UserDto getUser();

}

