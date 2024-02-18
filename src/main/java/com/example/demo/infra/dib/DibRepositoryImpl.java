package com.example.demo.infra.dib;


import static com.example.demo.domain.dib.entity.QDib.dib;
import static com.example.demo.domain.dib.entity.QDibGroup.dibGroup;
import static com.example.demo.domain.user.entity.QUsers.users;

import com.example.demo.common.BusinessException;
import com.example.demo.domain.dib.entity.Dib;
import com.example.demo.domain.dib.entity.DibGroup;
import com.example.demo.domain.dib.repo.DibRepository;
import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.jpa.impl.JPAQueryFactory;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Repository;

@RequiredArgsConstructor
@Repository
public class DibRepositoryImpl implements DibRepository {

	private final DibGroupJpaRepository dibGroupJpaRepository;
	private final DibJpaRepository dibJpaRepository;
	private final JPAQueryFactory jpaQueryFactory;

	@Override
	public List<DibGroup> findDibGroups(Long userId, Long pageIndex, int numberOfPages) {
		return jpaQueryFactory.selectFrom(dibGroup)
			.where(dibGroup.userId.eq(userId)
				.and(cursorDibGroup(pageIndex)))
			.orderBy(dibGroup.dibGroupId.desc())
			.limit(numberOfPages)
			.fetch();
	}

	private BooleanExpression cursorDibGroup(Long pageIndex) {
		return Objects.isNull(pageIndex) ? null : dibGroup.dibGroupId.lt(pageIndex);
	}


	@Override
	public DibGroup createDibGroup(DibGroup entity) {
		return dibGroupJpaRepository.save(entity);
	}

	@Override
	public void deleteByGroupId(Long dibGroupId) {
		dibJpaRepository.deleteAllByDibGroupId(dibGroupId);
		dibGroupJpaRepository.deleteById(dibGroupId);
	}

	@Override
	public List<Dib> findDibs(Long dibGroupId, Long pageIndex, int numberOfPages) {
		return jpaQueryFactory.selectFrom(dib)
			.where(dib.dibGroupId.eq(dibGroupId)
				.and(cursorDib(pageIndex)))
			.orderBy(dib.dibId.desc())
			.limit(numberOfPages)
			.fetch();
	}

	private BooleanExpression cursorDib(Long pageIndex) {
		return Objects.isNull(pageIndex) ? null : dib.dibId.lt(pageIndex);
	}

	@Override
	public void addDib(Long dibGroupId, Long productId) {
		dibGroupJpaRepository.findById(dibGroupId)
			.orElseThrow(
				() -> new BusinessException(HttpStatus.BAD_REQUEST, "조회한 찜그룹을 찾을 수 없습니다."));
		dibJpaRepository.save(Dib.init(dibGroupId, productId));
	}

	@Override
	public void removeDib(Long dibGroupId, Long productId) {
		dibJpaRepository.delete(Dib.init(dibGroupId, productId));
	}

	@Override
	public Dib findDibByUserIdAndProductId(Long userId, Long productId) {
		return jpaQueryFactory.selectFrom(dib)
			.join(dibGroup).on(dib.dibGroupId.eq(dibGroup.dibGroupId))
			.join(users).on(dibGroup.userId.eq(userId))
			.where(users.userId.eq(userId)
				.and(dib.product.productId.eq(productId)))
			.fetchFirst();
	}

	@Override
	public int countDibGroups(Long userId) {
		return dibGroupJpaRepository.countByUserId(userId);
	}

	@Override
	public int countByUserIdAndDibName(Long userId, String dibName) {
		return dibGroupJpaRepository.countByUserIdAndDibName(userId, dibName);
	}
}
