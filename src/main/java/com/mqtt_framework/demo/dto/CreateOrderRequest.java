package com.mqtt_framework.demo.dto;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import lombok.Data;

import java.util.List;

@Data
public class CreateOrderRequest {

	@NotBlank
	private String restaurantId;

	@NotBlank
	private String branchId;

	@Min(1)
	private Integer tableNo;

	@NotEmpty
	private List<String> items;

}