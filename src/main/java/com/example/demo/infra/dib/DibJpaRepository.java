package com.example.demo.infra.dib;

import com.example.demo.domain.dib.entity.Dib;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DibJpaRepository extends JpaRepository<Dib, Long> {
	void deleteAllByDibGroupId(Long dibGroupId);
	Dib findByDibGroupIdAndProductProductId(Long dibGroupId, Long productId);
}
