package com.example.demo.domain.product.repo;

import com.example.demo.domain.product.entity.Product;
import java.util.List;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductRepository {

	List<Product> findAll(Long pageIndex, int numberOfPages);
}
