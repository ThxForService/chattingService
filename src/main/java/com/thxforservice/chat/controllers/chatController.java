package com.thxforservice.chat.controllers;

import com.thxforservice.global.rests.JSONData;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@Tag(name = "Chat", description = "채팅 API")
@RestController
@RequiredArgsConstructor
public class chatController {

    @Operation(summary = "채팅방 목록", method = "GET")
    @ApiResponse(responseCode = "200", description = "로그인 한 계정의 (email)로 채팅방 목록 조회")
    @GetMapping("/rooms")
    public JSONData getRoomList(){
        return null;
    }

    @Operation(summary = "채팅방 정보", method = "GET")
    @ApiResponse(responseCode = "200", description = "채팅방 번호(roomNo)로 채팅방 목록 조회")
    @GetMapping("/room/{roomNo}")
    public JSONData getRoomInfo(@PathVariable Long roomNo) {
        return null;
    }
}
