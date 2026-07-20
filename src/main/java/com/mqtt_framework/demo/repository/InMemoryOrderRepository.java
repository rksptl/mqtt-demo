package com.mqtt_framework.demo.repository;

import java.util.Collection;
import java.util.Map;
import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;

import org.springframework.stereotype.Repository;

import com.mqtt_framework.demo.common.model.OrderEvent;

@Repository
public class InMemoryOrderRepository implements OrderRepository {

	private final Map<Long, OrderEvent> orders = new ConcurrentHashMap<>();

	@Override
	public OrderEvent save(OrderEvent order) {
		orders.put(order.getOrderId(), order);
		return order;
	}

	@Override
	public Optional<OrderEvent> findById(Long orderId) {
		return Optional.ofNullable(orders.get(orderId));
	}

	@Override
	public Collection<OrderEvent> findAll() {
		return orders.values();
	}
}