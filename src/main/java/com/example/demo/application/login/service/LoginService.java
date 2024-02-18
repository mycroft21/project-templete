package com.example.demo.application.login.service;

import com.example.demo.application.login.dto.LoginDto.Check;
import com.example.demo.application.login.dto.LoginDto.Join;
import com.example.demo.application.user.dto.UserCrudDto.UserDto;
import com.example.demo.common.BusinessException;
import com.example.demo.common.JwtTokenUtil;
import com.example.demo.domain.user.entity.Users;
import com.example.demo.domain.user.repo.UserRepository;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class LoginService {

	private final UserRepository userRepository;
	@Value("${jwt.token.secret}")
	private String secretKey;
	@Value("${jwt.token.expire}")
	private static long expireTimeMs = 1000 * 60 * 60;

	public String checkLogin(Check request) {
		Optional<Users> usersOptional = userRepository.checkLogin(request.getUserEmail(),
			request.getUserPassword());
		if (usersOptional.isEmpty()) {
			return "";
		}
		Users user = usersOptional.get();
		return JwtTokenUtil.createToken(String.valueOf(user.getUserId()), secretKey, expireTimeMs);
	}

	public UserDto joinUser(Join request) {
		if (userRepository.countByEmail(request.getUserEmail()) > 0) {
			throw new BusinessException(HttpStatus.BAD_REQUEST, "중복된 email");
		}
		return UserDto.of(userRepository.save(request.toEntity()));
	}
}
