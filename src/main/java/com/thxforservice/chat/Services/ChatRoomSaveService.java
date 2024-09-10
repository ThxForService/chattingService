package com.thxforservice.chat.Services;

import com.thxforservice.chat.controllers.RequestChatRoom;
import com.thxforservice.chat.entities.ChatRoom;
import com.thxforservice.chat.exceptions.RoomNotFoundException;
import com.thxforservice.chat.repositories.ChatRoomRepository;
import com.thxforservice.member.MemberUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ChatRoomSaveService {

    private final MemberUtil memberUtil;
    private final ChatRoomRepository chatRoomRepository;

    public ChatRoom save(RequestChatRoom form){
        Long roomNo = form.getRoomNo();
        ChatRoom room = null;
        if(roomNo != null){
            room = chatRoomRepository.findById(roomNo).orElseThrow(RoomNotFoundException::new);
        }else{
            room = new ChatRoom();
        }

        room.setRoomNo(roomNo);
        room.setRoomNm(form.getRoomNm());
        room.setUserEmail(form.getUserEmail());

        chatRoomRepository.saveAndFlush(room);

        return room;
    }


}
