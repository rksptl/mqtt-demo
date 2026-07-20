package com.mqtt_framework.demo.controller;

import com.mqtt_framework.demo.common.model.OrderEvent;
import com.mqtt_framework.demo.dto.CreateOrderRequest;
import com.mqtt_framework.demo.service.OrderService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/orders")
@RequiredArgsConstructor
public class OrderController {

	private final OrderService orderService;

	@PostMapping
	public OrderEvent create(@Valid @RequestBody CreateOrderRequest request) {

		return orderService.create(request);

	}

}