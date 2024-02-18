package com.example.demo.domain.dib.entity;

import com.example.demo.domain.product.entity.Product;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToOne;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Entity
public class Dib {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long dibId;

	private Long dibGroupId;
	@ManyToOne
	@JoinColumn(name = "product_id")
	private Product product;

	public static Dib init(Long dibGroupId, Long productId){
		return Dib.builder()
			.dibGroupId(dibGroupId)
			.product(Product.init(productId)).build();
	}
}
