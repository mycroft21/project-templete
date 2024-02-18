package com.example.demo.application.user.service;

import com.example.demo.application.user.dto.UserCrudDto.Modify;
import com.example.demo.application.user.dto.UserCrudDto.UserDto;
import com.example.demo.domain.user.repo.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class UserService {

	private final UserRepository userRepository;

	public UserDto getByUserId(Long userId){
		return UserDto.of(userRepository.getByUserId(userId));
	}

	public UserDto modifyUser(Modify request){
		return UserDto.of(userRepository.update(request.toEntity()));
	}
}
