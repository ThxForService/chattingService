package com.thxforservice.chat.exceptions;

import com.thxforservice.global.exceptions.CommonException;
import org.springframework.http.HttpStatus;

public class RoomClosedException extends CommonException {
    public RoomClosedException() {
        super("Closed.room", HttpStatus.FORBIDDEN);
    }
}
