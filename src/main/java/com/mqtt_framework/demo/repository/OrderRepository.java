package com.mqtt_framework.demo.repository;

import java.util.Collection;
import java.util.Optional;

import com.mqtt_framework.demo.common.model.OrderEvent;

public interface OrderRepository {

	OrderEvent save(OrderEvent order);

	Optional<OrderEvent> findById(Long orderId);

	Collection<OrderEvent> findAll();

}