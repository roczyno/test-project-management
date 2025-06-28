package com.roczyno.project_management.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;


@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity
public class Project {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String description;
    private String category;
    @ElementCollection(fetch = FetchType.EAGER)
    private List<String> tags = new ArrayList<>();
    @OneToOne(mappedBy = "project",cascade = CascadeType.REMOVE, orphanRemoval = true)
    private Chat chat;
    @ManyToOne(fetch = FetchType.LAZY)
    private User owner;
    @OneToMany(mappedBy = "project",cascade = CascadeType.REMOVE, orphanRemoval = true)
    private List<Issue> issues = new ArrayList<>();
    @ManyToMany
    private List<User> team = new ArrayList<>();


}
