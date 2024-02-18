package com.example.demo.common;

import lombok.Builder;
import lombok.Getter;
import lombok.ToString;
import org.springframework.http.HttpStatus;

@Getter
@ToString
public class ApiException {

	private String errorCode;
	private String errorMessage;

	@Builder
	public ApiException(HttpStatus status, String errorCode, String errorMessage) {
		this.errorCode = errorCode;
		this.errorMessage = errorMessage;
	}

}
