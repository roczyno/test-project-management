package com.roczyno.project_management.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Chat {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    @JsonIgnore
    @OneToMany(mappedBy = "chat", cascade = CascadeType.ALL,orphanRemoval = true)
    private List<Message> messages;
    @OneToOne(fetch = FetchType.LAZY)
    private Project project;
    @ManyToMany
    private List<User> users=new ArrayList<>();
    private LocalDateTime createdAt;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Chat chat = (Chat) o;
        return Objects.equals(id, chat.id) && Objects.equals(name, chat.name) && Objects.equals(messages, chat.messages)
                && Objects.equals(project, chat.project) && Objects.equals(users, chat.users);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, messages, project, users);
    }
}
