package com.example.demo.application.user.dto;

import com.example.demo.domain.user.entity.Users;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

public class UserCrudDto {

	@Getter
	@Builder
	@AllArgsConstructor
	public static class UserDto{
		private Long userId;
		private String userName;
		private String userEmail;

		public static UserDto of(Users entity){
			return UserDto.builder()
				.userId(entity.getUserId())
				.userName(entity.getUserName())
				.userEmail(entity.getUserEmail())
				.build();
		}
	}

	@Getter
	@NoArgsConstructor
	public class Modify{
		private Long userId;
		private String userName;
		private String userEmail;
		private String userPassword;

		public void setId(Long userId){
			this.userId = userId;
		}
		public Users toEntity(){
			return Users.builder()
				.userId(this.userId)
				.userName(this.userName)
				.userEmail(this.userEmail)
				.userPassword(this.userPassword)
				.build();
		}
	}

}
