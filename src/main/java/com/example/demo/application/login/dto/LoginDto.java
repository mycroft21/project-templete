package com.example.demo.application.login.dto;

import com.example.demo.domain.user.entity.Users;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

public class LoginDto {

	@Setter
	@Getter
	@NoArgsConstructor
	public static class Check {
		private String userEmail;
		private String userPassword;
	}

	@Getter
	@NoArgsConstructor
	@AllArgsConstructor
	public static class Join {
		private String userName;
		private String userEmail;
		private String userPassword;

		public Users toEntity() {
			return Users.builder()
				.userName(this.userName)
				.userEmail(this.userEmail)
				.userPassword(this.userPassword)
				.build();
		}
	}
}
