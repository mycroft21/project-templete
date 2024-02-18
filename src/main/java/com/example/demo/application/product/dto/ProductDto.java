package com.example.demo.application.product.dto;

import com.example.demo.domain.product.entity.Product;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
@AllArgsConstructor
public class ProductDto {
	private Long productId;
	private String productName;
	private String productThumbnail;
	private Long productPrice;

	public static ProductDto of(Product entity){
		return ProductDto.builder()
			.productId(entity.getProductId())
			.productName(entity.getProductName())
			.productThumbnail(entity.getProductThumbnail())
			.productPrice(entity.getProductPrice())
		.build();
	}
}
