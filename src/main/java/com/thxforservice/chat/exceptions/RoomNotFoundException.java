package com.thxforservice.chat.exceptions;

import com.thxforservice.global.exceptions.CommonException;
import org.springframework.http.HttpStatus;

public class RoomNotFoundException extends CommonException {
    public RoomNotFoundException() {
        super("NotFound.room", HttpStatus.NOT_FOUND);
        setErrorCode(true);
    }
}
