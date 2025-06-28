package com.roczyno.project_management.request;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class InviteRequest {
    private String email;
    private Long projectId;
}
