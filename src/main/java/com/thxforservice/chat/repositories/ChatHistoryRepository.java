package com.thxforservice.chat.repositories;

import com.thxforservice.chat.entities.ChatHistory;
import com.thxforservice.chat.entities.ChatRoom;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;

import java.util.List;

public interface ChatHistoryRepository extends JpaRepository<ChatHistory, Long>, QuerydslPredicateExecutor<ChatHistory> {
    List<ChatHistory> findByEmail(String email);
    List<ChatHistory> findByRoomNo(ChatRoom roomNo);
}
