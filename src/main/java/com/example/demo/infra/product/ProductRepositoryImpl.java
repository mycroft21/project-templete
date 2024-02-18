package com.example.demo.infra.product;

import static com.example.demo.domain.product.entity.QProduct.product;

import com.example.demo.domain.product.entity.Product;
import com.example.demo.domain.product.repo.ProductRepository;
import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.jpa.impl.JPAQueryFactory;
import java.util.List;
import java.util.Objects;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

@RequiredArgsConstructor
@Repository
public class ProductRepositoryImpl implements ProductRepository {

	private final ProductJpaRepository productJpaRepository;
	private final JPAQueryFactory jpaQueryFactory;


	@Override
	public List<Product> findAll(Long pageIndex, int numberOfPages) {
		return jpaQueryFactory.selectFrom(product)
			.where(cursorProduct(pageIndex))
			.orderBy(product.productId.desc())
			.limit(numberOfPages)
			.fetch();
	}

	private static BooleanExpression cursorProduct(Long pageIndex) {
		return Objects.isNull(pageIndex) ? null : product.productId.lt(pageIndex);
	}
}
