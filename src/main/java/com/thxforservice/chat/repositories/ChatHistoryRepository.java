package com.thxforservice.chat.repositories;

import com.thxforservice.chat.entities.ChatHistory;
import com.thxforservice.chat.entities.ChatRoom;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface ChatHistoryRepository extends CrudRepository<ChatHistory, Long>, QuerydslPredicateExecutor<ChatHistory> {
    List<ChatHistory> findByEmail(String email);
    List<ChatHistory> findByRoomNo(ChatRoom roomNo);
}
