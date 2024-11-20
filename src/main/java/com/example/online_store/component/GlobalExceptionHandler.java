package com.example.online_store.component;

import com.example.online_store.dto.ErrorDto;
import com.example.online_store.exception.ApiException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.nio.file.AccessDeniedException;
import java.util.stream.Collectors;

@ControllerAdvice
public class GlobalExceptionHandler {

    // Обработчик для ApiException
    @ExceptionHandler(ApiException.class)
    public ResponseEntity<ErrorDto> handleApiException(ApiException e) {
        return ResponseEntity.status(e.getStatusCode())  // Код ошибки из ApiException
                .body(new ErrorDto(e.getMessage(), e.getStatusCode().value())); // Сообщение и статус
    }

    // Обработчик для MethodArgumentNotValidException (например, ошибки валидации)
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ErrorDto> handleNotValid(MethodArgumentNotValidException e) {
        // Обрабатываем все ошибки валидации, получаем список полей и ошибок
        String errorMessage = e.getBindingResult()
                .getFieldErrors().stream()
                .map(ex -> ex.getField() + ":" + ex.getDefaultMessage())
                .collect(Collectors.joining(", "));

        return ResponseEntity.status(HttpStatus.BAD_REQUEST) // Используем 400 как статус
                .body(new ErrorDto(errorMessage, HttpStatus.BAD_REQUEST.value())); // Возвращаем ошибки
    }

    // Обработчик для AccessDeniedException (например, если у пользователя нет доступа)
    @ExceptionHandler(AccessDeniedException.class)
    public ResponseEntity<ErrorDto> handleAccessDenied(AccessDeniedException e) {
        return ResponseEntity.status(HttpStatus.FORBIDDEN.value())  // Статус 403
                .body(new ErrorDto("Access Denied: You do not have permission", HttpStatus.FORBIDDEN.value()));  // Сообщение
    }
}
