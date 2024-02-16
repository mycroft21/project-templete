package com.example.demo.domain.user.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class Users {

	@Id
	@GeneratedValue(strategy= GenerationType.IDENTITY)
	private Long userId;
	private String userName;
	private String userEmail;
	private String userPassword;

}
