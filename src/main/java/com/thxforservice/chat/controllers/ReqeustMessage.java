package com.thxforservice.chat.controllers;

import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class ReqeustMessage {
    private String email;

    @NotNull(message="메세지를 입력하세요.")
    private String message;

    @NotNull
    private Long roomNo;

}
