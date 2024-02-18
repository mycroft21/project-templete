package com.example.demo.domain.user.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.util.StringUtils;

@Getter
@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Users {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long userId;
	private String userName;
	private String userEmail;
	private String userPassword;

	public void patch(Users patch) {
		this.userName = StringUtils.hasLength(patch.getUserName()) ? patch.getUserName() : this.userName;
		this.userEmail = StringUtils.hasLength(patch.getUserEmail()) ? patch.getUserEmail() : this.userEmail;
		this.userPassword = StringUtils.hasLength(patch.getUserPassword()) ? patch.getUserPassword() : this.getUserPassword();
	}
}
