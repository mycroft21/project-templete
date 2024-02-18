package com.example.demo.common;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.springframework.http.HttpStatus;

@Getter
@NoArgsConstructor
@ToString
public class ApiResponse<T> {

	private HttpStatus status;
	@JsonInclude(JsonInclude.Include.NON_NULL)
	private String message;
	@JsonInclude(JsonInclude.Include.NON_NULL)
	private T data;

	@Builder
	public ApiResponse(HttpStatus status, String message, T data) {
		this.status = status;
		this.message = message;
		this.data = data;
	}

	public static ApiResponse success() {
		return ApiResponse.builder()
			.status(HttpStatus.OK)
			.build();
	}

	public static <T> ApiResponse<T> success(T data) {
		return ApiResponse.<T>builder()
			.status(HttpStatus.OK)
			.data(data)
			.build();
	}

	public static ApiResponse fail(HttpStatus status, String message) {
		return ApiResponse.builder()
			.status(status)
			.message(message)
			.build();
	}
}

