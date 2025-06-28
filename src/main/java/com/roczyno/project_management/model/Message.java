package com.roczyno.project_management.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.Objects;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Message {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String message;
    private LocalDateTime createdAt;
    @ManyToOne
    private Chat chat;
    @ManyToOne
    private User sender;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Message message1 = (Message) o;
        return Objects.equals(id, message1.id) && Objects.equals(message, message1.message) &&
                Objects.equals(createdAt, message1.createdAt) && Objects.equals(chat, message1.chat)
                && Objects.equals(sender, message1.sender);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, message, createdAt, chat, sender);
    }
}
