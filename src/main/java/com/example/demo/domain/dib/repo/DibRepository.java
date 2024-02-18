package com.example.demo.domain.dib.repo;

import com.example.demo.application.dib.dto.DibCrudDto.DibGroupDto;
import com.example.demo.domain.dib.entity.Dib;
import com.example.demo.domain.dib.entity.DibGroup;
import java.util.List;
import java.util.Optional;
import org.springframework.stereotype.Repository;

@Repository
public interface DibRepository {

	List<DibGroup> findDibGroups(Long userId, Long pageIndex, int numberOfPages);

	DibGroup createDibGroup(DibGroup entity);

	void deleteByGroupId(Long dibGroupId);

	List<Dib> findDibs(Long dibGroupId, Long pageIndex, int numberOfPages);

	void addDib(Long dibGroupId, Long productId);

	void removeDib(Long dibGroupId, Long productId);

	Dib findDibByUserIdAndProductId(Long userId, Long productId);

	int countDibGroups(Long userId);

	int countByUserIdAndDibName(Long userId, String dibName);
}
