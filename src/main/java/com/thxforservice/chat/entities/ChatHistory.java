package com.thxforservice.chat.entities;

import com.thxforservice.global.entities.BaseEntity;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Data
@Builder
@NoArgsConstructor @AllArgsConstructor
public class ChatHistory extends BaseEntity {

    @Id @GeneratedValue
    private Long seq;

    @Column(length=40, nullable = false)
    private String email;

    @Lob
    @Column(nullable = false)
    private String message;

    @ToString.Exclude
    @ManyToOne(fetch=FetchType.LAZY)
    @JoinColumn(name="roomNo")
    private ChatRoom room;
}
