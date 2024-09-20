package com.thxforservice.chat.controllers;

import lombok.Data;

@Data
public class RequestChatRoom {

    private Long roomNo;

    private String roomNm;
    
    private String userEmail;

}
