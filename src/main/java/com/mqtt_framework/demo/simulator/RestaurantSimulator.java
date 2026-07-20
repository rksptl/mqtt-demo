package com.mqtt_framework.demo.simulator;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

import com.mqtt_framework.demo.common.enums.OrderStatus;
import com.mqtt_framework.demo.common.model.OrderEvent;
import com.mqtt_framework.demo.order.OrderPublisher;
import com.rkp.framework.mqtt.lifecycle.MqttLifecycleManager;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Component
public class RestaurantSimulator {

	private final MqttLifecycleManager lifecycleManager;
	private final OrderPublisher publisher;

	@EventListener(ApplicationReadyEvent.class)
	public void run() throws Exception {

		lifecycleManager.start();

		Thread.sleep(1000);

		OrderEvent order = OrderEvent.builder().restaurantId("1001").branchId("NOIDA").orderId(101L).tableNo(12)
				.status(OrderStatus.CREATED).items(List.of("Burger", "French Fries", "Coke"))
				.timestamp(LocalDateTime.now()).build();

		publisher.publishCreated(order);
	}
}