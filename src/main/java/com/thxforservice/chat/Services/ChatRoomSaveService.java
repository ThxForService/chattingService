package com.thxforservice.chat.Services;

import com.thxforservice.chat.controllers.RequestChatRoom;
import com.thxforservice.chat.entities.ChatRoom;
import com.thxforservice.chat.exceptions.RoomNotFoundException;
import com.thxforservice.chat.repositories.ChatRoomRepository;
import com.thxforservice.global.exceptions.UnAuthorizedException;
import com.thxforservice.member.MemberUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
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

        if(memberUtil.isLogin()){
            room.setRoomNm(memberUtil.getMember().getUserName().concat("님의 채팅방"));
            room.setUserEmail(memberUtil.getMember().getEmail());
        }else{
            throw new UnAuthorizedException();
        }

        chatRoomRepository.saveAndFlush(room);

        return room;
    }


}
