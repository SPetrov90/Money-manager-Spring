package ru.sergey90.util.exteption;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * свое исключение которое может прилететь в ответ
 */
@ResponseStatus(value = HttpStatus.NOT_FOUND, reason = "No data found")  // 404
public class NotFoundException extends RuntimeException {
    public NotFoundException(String message) {
        super(message);
    }
}
