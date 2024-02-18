package com.example.demo.infra.user;

import com.example.demo.domain.user.entity.Users;
import com.example.demo.domain.user.repo.UserRepository;
import jakarta.persistence.EntityExistsException;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class UserRepositoryImpl implements UserRepository {

	private final UserJpaRepository userJpaRepository;

	@Override
	public int countByEmail(String userEmail) {
		return userJpaRepository.countByUserEmail(userEmail);
	}

	@Override
	public Optional<Users> checkLogin(String userEmail, String userPassword) {
		return userJpaRepository.findByUserEmailAndUserPassword(userEmail, userPassword);
	}

	@Override
	public Users getByUserId(Long userId) {
		return userJpaRepository.findById(userId).orElseThrow(EntityExistsException::new);
	}

	@Override
	public Users save(Users entity) {
		return userJpaRepository.save(entity);
	}

	@Override
	public Users update(Users patch) {
		Users target = userJpaRepository.findById(patch.getUserId()).orElseThrow(EntityExistsException::new);
		target.patch(patch);
		return userJpaRepository.save(target);
	}
}
