package com.mqtt_framework.demo.common.model;

import java.time.LocalDateTime;
import java.util.List;

import com.mqtt_framework.demo.common.enums.OrderStatus;

import lombok.*;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class OrderEvent {

	private String restaurantId;

	private String branchId;

	private Long orderId;

	private Integer tableNo;

	private List<String> items;

	private OrderStatus status;

	private LocalDateTime timestamp;

}