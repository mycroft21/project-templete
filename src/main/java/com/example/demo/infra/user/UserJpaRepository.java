package com.example.demo.infra.user;

import com.example.demo.domain.user.entity.Users;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserJpaRepository extends JpaRepository<Users, Long> {
	Optional<Users> findByUserEmailAndUserPassword(String userEmail, String userPassword);

	int countByUserEmail(String userEmail);
}
