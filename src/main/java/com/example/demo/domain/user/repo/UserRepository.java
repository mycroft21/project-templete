package com.example.demo.domain.user.repo;


import com.example.demo.domain.user.entity.Users;
import java.util.Optional;

public interface UserRepository {

	Optional<Users> checkLogin(String userEmail, String userPassword);

	Users getByUserId(Long userId);

	Users save(Users entity);

	Users update(Users entity);

	int countByEmail(String userEmail);
}
