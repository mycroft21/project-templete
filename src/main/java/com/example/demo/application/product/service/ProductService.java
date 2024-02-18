package com.example.demo.application.product.service;

import com.example.demo.application.product.dto.ProductDto;
import com.example.demo.domain.product.repo.ProductRepository;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class ProductService {

	private final ProductRepository productRepository;
	public List<ProductDto> getProducts(Long pageIndex, int numberOfPages) {
		return productRepository.findAll(pageIndex, numberOfPages).stream().map(ProductDto::of).toList();
	}
}
