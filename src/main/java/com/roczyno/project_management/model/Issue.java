package com.roczyno.project_management.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;


@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Issue {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String title;
    private String description;
    private String status;
    private String priority;
    private LocalDate dueDate;
    @ElementCollection(fetch = FetchType.EAGER)
    private List<String> tags = new ArrayList<>();
    @ManyToOne(fetch = FetchType.LAZY)
    private User assignee;
    @ManyToOne(fetch = FetchType.LAZY)
    private Project project;
    @OneToMany(mappedBy = "issue",cascade = CascadeType.REMOVE, orphanRemoval = true)
    private List<Comment> comments = new ArrayList<>();


}
