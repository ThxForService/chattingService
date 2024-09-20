package com.thxforservice.chat.controllers;

import com.thxforservice.chat.Services.ChatRoomInfoService;
import com.thxforservice.chat.entities.ChatHistory;
import com.thxforservice.chat.entities.ChatRoom;
import com.thxforservice.global.rests.JSONData;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@Tag(name = "ChatAdmin", description = "관리자 채팅 API")
@RestController
@RequestMapping("/admin/chat")
@RequiredArgsConstructor
public class ChatAdminController {

    private final ChatRoomInfoService chatRoomInfoService;

    @Operation(summary = "(관리자) 채팅방 목록 조회", method = "GET")
    @ApiResponse(responseCode = "200", description = "모든 채팅방 목록 조회")
    @GetMapping("/rooms")
    public JSONData getRoomList(){
        List<ChatRoom> chatRooms = chatRoomInfoService.getList();
        return new JSONData(chatRooms);
    }

    @Operation(summary = "채팅방 조회(메세지)", method = "GET")
    @ApiResponse(responseCode = "200", description = "채팅방 번호(roomNo)로 채팅방 조회")
    @Parameter(name="roomNo", required = true, description = "경로변수, 채팅방 번호", example = "100")
    @GetMapping("/room/{roomNo}")
    public JSONData getRoomInfo(@PathVariable("roomNo") Long roomNo) {

        List<ChatHistory> message = chatRoomInfoService.get(roomNo);

        return new JSONData(message);
    }


}
