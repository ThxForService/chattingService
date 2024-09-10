package com.thxforservice.chat.entities;

import com.thxforservice.global.entities.BaseEntity;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data @Builder
@NoArgsConstructor @AllArgsConstructor
public class ChatRoom extends BaseEntity {

    @Id @GeneratedValue
    private Long roomNo;

    @Column(length=60, nullable = false)
    private String roomNm;

    @Column(nullable = false)
    private String userEmail;

    @Column
    private String counselorEmail;
}
