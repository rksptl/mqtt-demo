package com.mqtt_framework.demo.service;

import java.time.LocalDateTime;
import java.util.concurrent.atomic.AtomicLong;

import org.springframework.stereotype.Service;

import com.mqtt_framework.demo.common.enums.OrderStatus;
import com.mqtt_framework.demo.common.model.OrderEvent;
import com.mqtt_framework.demo.dto.CreateOrderRequest;
import com.mqtt_framework.demo.order.OrderPublisher;
import com.mqtt_framework.demo.repository.OrderRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class OrderService {

	private final AtomicLong sequence = new AtomicLong(1000);

	private final OrderRepository repository;
	private final OrderPublisher publisher;

	public OrderEvent create(CreateOrderRequest request) {

		OrderEvent order = OrderEvent.builder().orderId(sequence.incrementAndGet())
				.restaurantId(request.getRestaurantId()).branchId(request.getBranchId()).tableNo(request.getTableNo())
				.items(request.getItems()).status(OrderStatus.CREATED).timestamp(LocalDateTime.now()).build();

		repository.save(order);

		publisher.publishCreated(order);

		return order;
	}

}