package com.example.demo.common;

import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
public class BusinessException extends RuntimeException{

	private HttpStatus httpStatus;

	public BusinessException(HttpStatus httpStatus, String message){
		super(message);
		this.httpStatus = httpStatus;
	}
}
