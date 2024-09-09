package com.thxforservice.chat.controllers;

import com.thxforservice.global.rests.JSONData;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
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

    @Operation(summary = "채팅방 조회(메세지)", method = "GET")
    @ApiResponse(responseCode = "200", description = "채팅방 번호(roomNo)로 채팅방 목록 조회")
    @GetMapping("/room/{roomNo}")
    public JSONData getRoomInfo(@PathVariable Long roomNo) {
        return null;
    }

    @Operation(summary = "채팅 시작", method = "POST")
    @ApiResponse(responseCode = "201")
    @PostMapping("/room")
    public ResponseEntity registerRoom() {
        return null;
    }

    @Operation(summary = "메세지 전송", method = "POST")
    @ApiResponse(responseCode = "201")
    @PostMapping("/message")
    public ResponseEntity registerMessage(){
        return null;
    }



    @Operation(summary = "채팅방 종료", method = "POST")
    @ApiResponse(responseCode = "201")
    @Parameter(name="roomNo", required = true, description = "경로변수, 채팅방 No(roomNo)", example = "1004")
    @PostMapping("/close/{roomNo}")
    public ResponseEntity closeRoom(@PathVariable("roomNo") Long roomNo){
        return null;
    }
}
