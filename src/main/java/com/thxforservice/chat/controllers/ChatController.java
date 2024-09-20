package com.thxforservice.chat.controllers;

import com.thxforservice.chat.Services.ChatMessageSaveService;
import com.thxforservice.chat.Services.ChatRoomCloseService;
import com.thxforservice.chat.Services.ChatRoomInfoService;
import com.thxforservice.chat.Services.ChatRoomSaveService;
import com.thxforservice.chat.Validatiors.MessageValidator;
import com.thxforservice.chat.entities.ChatHistory;
import com.thxforservice.chat.entities.ChatRoom;
import com.thxforservice.global.Utils;
import com.thxforservice.global.exceptions.BadRequestException;
import com.thxforservice.global.rests.JSONData;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.Parameters;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Tag(name = "Chat", description = "채팅 API")
@RestController
@RequestMapping("/chat")
@RequiredArgsConstructor
public class ChatController {

    private final ChatRoomInfoService chatRoomInfoService;
    private final ChatRoomSaveService chatRoomSaveService;
    private final ChatMessageSaveService messageSaveService;
    private final ChatRoomCloseService chatRoomCloseService;
    private final MessageValidator messageValidator;

    private final Utils utils;

    @Operation(summary = "채팅방 목록 조회", method = "GET")
    @ApiResponse(responseCode = "200", description = "로그인 한 계정의 (email)로 채팅방 목록 조회")
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

    @Operation(summary = "채팅 시작", method = "POST")
    @ApiResponse(responseCode = "201")
    @PostMapping("/room")
    public ResponseEntity<JSONData> registerRoom(@Valid RequestChatRoom form, Errors errors) {

        if(errors.hasErrors()){
            throw new BadRequestException(utils.getErrorMessages(errors));
        }

        ChatRoom room = chatRoomSaveService.save(form);
        HttpStatus status = HttpStatus.CREATED;

        JSONData jsonData = new JSONData(room);

        return ResponseEntity.status(status).body(jsonData);
    }

    @Operation(summary = "메세지 전송(저장)", method = "POST")
    @ApiResponse(responseCode = "201")
    @Parameters({
            @Parameter(name="message", required = true, description = "메세지", example = "Hi!"),
            @Parameter(name="roomNo", required = true, description = "채팅방 번호", example = "102")
    })
    @PostMapping("/message")
    public ResponseEntity<JSONData> registerMessage(@RequestBody @Valid ReqeustMessage message, Errors errors){
        messageValidator.validate(message, errors);

        if(errors.hasErrors()){
            throw new BadRequestException(utils.getErrorMessages(errors));
        }

        ChatHistory data = messageSaveService.save(message);
        JSONData jsonData = new JSONData(data);
        HttpStatus status = HttpStatus.CREATED;
        jsonData.setStatus(status);

        return ResponseEntity.status(status).body(jsonData);
    }



    @Operation(summary = "채팅방 종료", method = "POST")
    @ApiResponse(responseCode = "200")
    @Parameter(name="roomNo", required = true, description = "경로변수, 채팅방 No(roomNo)", example = "1004")
    @DeleteMapping("/close/{roomNo}")
    public JSONData closeRoom(@PathVariable("roomNo") Long roomNo){

        ChatRoom room = chatRoomCloseService.close(roomNo);

        return new JSONData(room);
    }

}
