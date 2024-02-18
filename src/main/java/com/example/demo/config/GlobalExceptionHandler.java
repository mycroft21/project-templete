package com.example.demo.config;

import com.example.demo.common.ApiResponse;
import com.example.demo.common.BusinessException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@Slf4j
@RestControllerAdvice
public class GlobalExceptionHandler {

	@ExceptionHandler(BusinessException.class)
	public ApiResponse handleEmailDuplicateException(BusinessException ex){
		log.error("handleEmailDuplicateException",ex);
		return ApiResponse.fail(ex.getHttpStatus(), ex.getMessage());
	}

	@ExceptionHandler(Exception.class)
	public ApiResponse handleException(Exception ex){
		log.error("handleException",ex);
		return ApiResponse.fail(HttpStatus.BAD_REQUEST, ex.getMessage());
	}

}
