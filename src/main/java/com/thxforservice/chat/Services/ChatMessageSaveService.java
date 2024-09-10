package com.thxforservice.chat.Services;

import com.thxforservice.chat.controllers.ReqeustMessage;
import com.thxforservice.chat.entities.ChatHistory;
import com.thxforservice.chat.entities.ChatRoom;
import com.thxforservice.chat.exceptions.RoomNotFoundException;
import com.thxforservice.chat.repositories.ChatHistoryRepository;
import com.thxforservice.chat.repositories.ChatRoomRepository;
import com.thxforservice.member.MemberUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
@RequiredArgsConstructor
public class ChatMessageSaveService {

    private final ChatRoomRepository chatRoomRepository;
    private final ChatHistoryRepository chatHistoryRepository;
    private final MemberUtil memberUtil;

    public ChatHistory save(ReqeustMessage message){
        Long roomNo = message.getRoomNo();
        ChatRoom room = chatRoomRepository.findById(roomNo).orElseThrow(RoomNotFoundException::new);

        ChatHistory history = ChatHistory.builder()
                .email(memberUtil.getMember().getEmail())
                .message(message.getMessage())
                .roomNo(room)
                .build();

        chatHistoryRepository.saveAndFlush(history);

        return history;
    }


}
