package com.mqtt_framework.demo.notification;

import org.springframework.stereotype.Component;

import com.mqtt_framework.demo.common.model.OrderEvent;
import com.mqtt_framework.demo.util.ConsolePrinter;
import com.rkp.framework.mqtt.subscriber.MqttSubscriber;

import com.rkp.framework.mqtt.model.SubscribeRequest;

import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class NotificationSubscriber {

	private final MqttSubscriber subscriber;

	@PostConstruct
	public void subscribe() {

		subscriber.subscribe(SubscribeRequest.<OrderEvent>builder().topic("restaurant/+/branch/+/order/paid")
				.messageType(OrderEvent.class).listener(this::notifyCustomer).build());
	}

	private void notifyCustomer(String topic, OrderEvent order) {

		ConsolePrinter.title("Notification");

		System.out.println("SMS Sent");

		System.out.println("Order " + order.getOrderId() + " completed.");

	}
}