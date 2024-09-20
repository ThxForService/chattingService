package com.thxforservice.chat.Validatiors;

import com.thxforservice.chat.controllers.ReqeustMessage;
import com.thxforservice.chat.entities.ChatRoom;
import com.thxforservice.chat.exceptions.RoomNotFoundException;
import com.thxforservice.chat.repositories.ChatRoomRepository;
import com.thxforservice.member.MemberUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

@Component
@RequiredArgsConstructor
public class MessageValidator implements Validator {

    private final ChatRoomRepository chatRoomRepository;
    private final MemberUtil memberUtil;

    @Override
    public boolean supports(Class<?> clazz) {
        return clazz.isAssignableFrom(ReqeustMessage.class);
    }

    @Override
    public void validate(Object target, Errors errors) {
        if(errors.hasErrors()) {
            return;
        }

        ReqeustMessage message = (ReqeustMessage) target;

        Long roomNo = message.getRoomNo();
        ChatRoom chatRoom = chatRoomRepository.findById(roomNo).orElseThrow(RoomNotFoundException::new);
    }
}
