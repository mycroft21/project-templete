package com.example.demo.application.dib.dto;

import com.example.demo.domain.dib.entity.Dib;
import com.example.demo.domain.dib.entity.DibGroup;
import com.example.demo.domain.product.entity.Product;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

public class DibCrudDto {

	@Getter
	@Builder
	@AllArgsConstructor
	public static class DibGroupDto{
		private Long dibGroupId;
		private String dibName;

		public static DibGroupDto of(DibGroup dibGroup){
			return DibGroupDto.builder()
				.dibGroupId(dibGroup.getDibGroupId())
				.dibName(dibGroup.getDibName())
				.build();
		}
	}

	@Getter
	public static class CreateGroup{
		private Long userId;
		private String dibName;

		public void setId(Long userId) {
			this.userId = userId;
		}

		public DibGroup toEntity() {
			return DibGroup.builder()
				.userId(this.userId)
				.dibName(this.dibName)
				.build();
		}
	}


	@Getter
	@Builder
	@AllArgsConstructor
	public static class DibDto{
		private Long dibId;
		private Long dibGroupId;
		private Long productId;
		private String productName;
		private String productThumbnail;
		private Long productPrice;

		public static DibDto of(Dib entity){
			return DibDto.builder()
				.dibId(entity.getDibId())
				.dibGroupId(entity.getDibGroupId())
				.productId(entity.getProduct().getProductId())
				.productName(entity.getProduct().getProductName())
				.productThumbnail(entity.getProduct().getProductThumbnail())
				.productPrice(entity.getProduct().getProductPrice())
				.build();
		}
	}



	@Getter
	public static class Add{
		private Long userId;
		private Long dibGroupId;
		private Long productId;

		public void setId(Long userId, Long dibGroupId){
			this.userId = userId;
			this.dibGroupId = dibGroupId;
		}
	}

	@Getter
	public static class Remove{
		private Long dibGroupId;
		private Long productId;

		public void setId(Long dibGroupId){
			this.dibGroupId = dibGroupId;
		}
	}
}
