package com.roczyno.project_management.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;


@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String username;
    private String email;
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private String password;
    private int projectSize;
    @OneToMany(mappedBy = "user",cascade = CascadeType.REMOVE)
    private List<Comment>comments;
    @OneToMany(mappedBy = "owner",cascade = CascadeType.REMOVE)
    private List<Project> projects;
    @OneToOne(mappedBy = "user",cascade = CascadeType.REMOVE)
    private Subscription subscription;
}
