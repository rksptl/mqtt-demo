package com.mqtt_framework.demo.dto;

import com.mqtt_framework.demo.common.enums.OrderStatus;
import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.List;

@Data
@Builder
public class OrderResponse {

	private Long orderId;

	private String restaurantId;

	private String branchId;

	private Integer tableNo;

	private List<String> items;

	private OrderStatus status;

	private LocalDateTime timestamp;

}