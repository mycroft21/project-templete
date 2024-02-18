package com.example.demo.application.dib.service;

import com.example.demo.application.dib.dto.DibCrudDto.Add;
import com.example.demo.application.dib.dto.DibCrudDto.CreateGroup;
import com.example.demo.application.dib.dto.DibCrudDto.DibDto;
import com.example.demo.application.dib.dto.DibCrudDto.DibGroupDto;
import com.example.demo.application.dib.dto.DibCrudDto.Remove;
import com.example.demo.common.BusinessException;
import com.example.demo.domain.dib.entity.Dib;
import com.example.demo.domain.dib.repo.DibRepository;
import java.util.List;
import java.util.Objects;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class DibService {

	private final DibRepository dibRepository;

	public List<DibGroupDto> findDibGroups(Long userId, Long pageIndex, int numberOfPages) {
		return dibRepository.findDibGroups(userId, pageIndex, numberOfPages).stream()
			.map(DibGroupDto::of).toList();
	}

	public void removeDibGroup(Long dibGroupId) {

		dibRepository.deleteByGroupId(dibGroupId);
	}

	public List<DibDto> findDibs(Long dibGroupId, Long pageIndex, int numberOfPages) {
		return dibRepository.findDibs(dibGroupId, pageIndex, numberOfPages).stream().map(DibDto::of)
			.toList();
	}

	public void addDib(Add request) throws Exception {
		validateAddDib(request);

		dibRepository.addDib(request.getDibGroupId(), request.getProductId());
	}

	private void validateAddDib(Add request) throws Exception {
		if (dibRepository.countDibGroups(request.getUserId()) < 1) {
			throw new Exception("등록된 찜 서랍 없음");
		}

		Dib dib = dibRepository.findDibByUserIdAndProductId(request.getUserId(),
			request.getProductId());
		if (Objects.isNull(dib)) {
			throw new Exception("중복된 상품 찜");
		}
	}

	public void removeDib(Remove request) {
		dibRepository.removeDib(request.getDibGroupId(), request.getProductId());
	}

	public DibGroupDto createGroup(CreateGroup request) {
		if (dibRepository.countByUserIdAndDibName(request.getUserId(), request.getDibName()) > 0) {
			throw new BusinessException(HttpStatus.BAD_REQUEST, "중보된 서랍 이름");
		}

		return DibGroupDto.of(dibRepository.createDibGroup(request.toEntity()));
	}
}
