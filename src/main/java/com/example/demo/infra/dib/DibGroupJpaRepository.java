package com.example.demo.infra.dib;

import com.example.demo.domain.dib.entity.DibGroup;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DibGroupJpaRepository extends JpaRepository<DibGroup, Long> {

	int countByUserId(Long userId);
	int countByUserIdAndDibName(Long userId, String DibName);
}
