package com.thxforservice.chat.Services;

import com.thxforservice.chat.entities.ChatHistory;
import com.thxforservice.chat.entities.ChatRoom;
import com.thxforservice.chat.exceptions.RoomNotFoundException;
import com.thxforservice.chat.repositories.ChatHistoryRepository;
import com.thxforservice.chat.repositories.ChatRoomRepository;
import com.thxforservice.global.exceptions.UnAuthorizedException;
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
    //상담자 모든 채팅방 목록 조회
    public List<ChatRoom> getList() {

        List<ChatRoom> chatRooms = null;
        List<ChatHistory> chatHistories = null;

        if(!memberUtil.isLogin()) throw new UnAuthorizedException();

        if(memberUtil.isStudent()){
            String email = memberUtil.getMember().getEmail();
            chatHistories = chatHistoryRepository.findByEmail(email);
            if (chatHistories.isEmpty()) {
                return null;
            }

            chatRooms = chatHistories.stream()
                    .map(ChatHistory::getRoomNo)
                    .distinct()
                    .collect(Collectors.toList());
        }else{
            chatRooms = chatRoomRepository.findAll();
        }


        return chatRooms;
    }


    /**
     * 특정 채팅방 가져오기
     *
     * @param roomNo
     * @return
     */
    public List<ChatHistory> get(Long roomNo) {

        //채팅방 정보 가져오기
        ChatRoom chatRoom = chatRoomRepository.findById(roomNo).orElseThrow(RoomNotFoundException::new);

        //채팅방 상태(종료) 검증
        //if (chatRoom.getDeletedAt().equals(null)) throw new RoomClosedException();


        List<ChatHistory> chatHistories = chatHistoryRepository.findByRoomNo(chatRoom);

        return chatHistories;
    }

}
