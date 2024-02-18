package com.example.demo.ui.product;

import com.example.demo.application.product.service.ProductService;
import com.example.demo.common.ApiResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
@RequestMapping("api/v1/ably/products")
public class ProductController {

	private final ProductService productService;

	@GetMapping
	public ApiResponse getProducts(
		@RequestParam(name = "pageIndex", required = false) Long pageIndex,
		@PageableDefault Pageable pageable) {
		return ApiResponse.success(productService.getProducts(pageIndex, pageable.getPageSize()));
	}
}
