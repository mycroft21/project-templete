package com.example.demo.domain.product.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Product {

	@Id
	@GeneratedValue(strategy= GenerationType.IDENTITY)
	private Long productId;
	private String productName;
	private String productThumbnail;
	private Long productPrice;

	public static Product init(Long productId){
		return Product.builder().productId(productId).build();
	}
}
