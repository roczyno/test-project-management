package com.roczyno.project_management.request;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
public class IssueRequest {
    private String title;
    private String description;
    private String status;
    private String priority;
    private LocalDate dueDate;

}
