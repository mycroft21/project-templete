package com.example.demo.ui.login;

import com.example.demo.application.login.dto.LoginDto.Check;
import com.example.demo.application.login.dto.LoginDto.Join;
import com.example.demo.application.login.service.LoginService;
import com.example.demo.common.ApiResponse;
import com.example.demo.common.JwtTokenUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
@RequestMapping("api/v1/ably/auth/")
public class LoginController {

	private final LoginService loginService;

	@PostMapping("login")
	public ApiResponse userLogin(@RequestBody Check request) {
		String token = loginService.checkLogin(request);
		if (StringUtils.hasLength(token)) {
			return ApiResponse.success(token);
		}
		return ApiResponse.fail(HttpStatus.UNAUTHORIZED, "로그인에 실패했습니다. 이메일과 비밀번호를 확인해주세요.");
	}

	@PostMapping("join")
	public ApiResponse userJoin(@RequestBody Join request) {
		return ApiResponse.success(loginService.joinUser(request));
	}
}
