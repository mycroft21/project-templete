package com.example.demo.ui.user;

import com.example.demo.application.user.dto.UserCrudDto.Modify;
import com.example.demo.application.user.service.UserService;
import com.example.demo.common.ApiResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
@RequestMapping("api/v1/ably/users")
public class UsersController {

	private final UserService userService;

	@GetMapping("{userId}")
	public ApiResponse findUser(@PathVariable(name = "userId") Long userId){
		return ApiResponse.success(userService.getByUserId(userId));
	}


	@PatchMapping("{userId}")
	public ApiResponse updateUser(@PathVariable(name = "userId") Long userId, @RequestBody Modify modify){
		modify.setId(userId);
		return ApiResponse.success(userService.modifyUser(modify));
	}

}
