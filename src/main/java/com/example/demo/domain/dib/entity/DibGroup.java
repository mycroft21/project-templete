package com.example.demo.domain.dib.entity;

import jakarta.persistence.CollectionTable;
import jakarta.persistence.Column;
import jakarta.persistence.ElementCollection;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import java.util.Set;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Entity
public class DibGroup {

	@Id
	@GeneratedValue(strategy= GenerationType.IDENTITY)
	private Long dibId;
	private String dibName;

	@ElementCollection
	@CollectionTable(name = "dibs", joinColumns = @JoinColumn(name="dibId"))
	@Column(name = "product_id")
	private Set<Long> dibs;
}
