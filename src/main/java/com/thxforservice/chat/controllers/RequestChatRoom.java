package com.thxforservice.chat.controllers;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class RequestChatRoom {

    @NotBlank
    private Long roomNo;

    private String roomNm;

    @NotBlank
    private String userEmail;

}
