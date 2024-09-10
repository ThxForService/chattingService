package com.thxforservice.chat.Services;

import com.thxforservice.chat.entities.ChatHistory;
import com.thxforservice.chat.entities.ChatRoom;
import com.thxforservice.chat.exceptions.RoomClosedException;
import com.thxforservice.chat.exceptions.RoomNotFoundException;
import com.thxforservice.chat.repositories.ChatHistoryRepository;
import com.thxforservice.chat.repositories.ChatRoomRepository;
import com.thxforservice.member.MemberUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ChatRoomInfoService {

    private final ChatRoomRepository chatRoomRepository;
    private final ChatHistoryRepository chatHistoryRepository;

    private final MemberUtil memberUtil;


    //로그인한 유저의 채팅방 목록 조회
    public List<ChatRoom> getList(){

        String email = memberUtil.getMember().getEmail();
        List<ChatHistory> chatHistories = chatHistoryRepository.findByEmail(email);

        if(chatHistories.isEmpty()){
            return null;
        }

        List<ChatRoom> chatRooms = chatHistories.stream()
                .map(ChatHistory::getRoomNo)
                .distinct()
                .collect(Collectors.toList());

        return chatRooms;
    }


    //채팅방의 메세지 가져오기.
    public List<ChatHistory> get(Long roomNo){

        //채팅방 정보 가져오기
        ChatRoom chatRoom = chatRoomRepository.findById(roomNo).orElseThrow(RoomNotFoundException::new);

        //채팅방 상태(종료) 검증
        if(chatRoom.getDeletedAt().equals("")) throw new RoomClosedException();

        List<ChatHistory> chatHistories = chatHistoryRepository.findByRoomNo(chatRoom);

        return chatHistories;
    }

}
