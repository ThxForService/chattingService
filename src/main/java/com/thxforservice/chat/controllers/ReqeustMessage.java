package com.thxforservice.chat.controllers;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class ReqeustMessage {
    @NotBlank
    private String email;

    @NotBlank(message="메세지를 입력하세요.")
    private String message;

    @NotBlank
    private Long roomNo;

}
