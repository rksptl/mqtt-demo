package com.mqtt_framework.demo.billing;

import org.springframework.stereotype.Component;

import com.mqtt_framework.demo.common.constants.TopicConstants;
import com.mqtt_framework.demo.common.enums.OrderStatus;
import com.mqtt_framework.demo.common.model.OrderEvent;
import com.mqtt_framework.demo.util.ConsolePrinter;
import com.rkp.framework.mqtt.publisher.MqttPublisher;
import com.rkp.framework.mqtt.subscriber.MqttSubscriber;

import com.rkp.framework.mqtt.model.PublishRequest;
import com.rkp.framework.mqtt.model.SubscribeRequest;

import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class BillingSubscriber {

	private final MqttSubscriber subscriber;
	private final MqttPublisher publisher;

	@PostConstruct
	public void subscribe() {

		subscriber.subscribe(SubscribeRequest.<OrderEvent>builder().topic("restaurant/+/branch/+/order/served")
				.messageType(OrderEvent.class).listener(this::generateBill).build());
	}

	private void generateBill(String topic, OrderEvent order) {

		ConsolePrinter.title("Billing");

		System.out.println("Generating bill for Order : " + order.getOrderId());

		order.setStatus(OrderStatus.PAID);

		String publishTopic = TopicConstants.PAID.formatted(order.getRestaurantId(), order.getBranchId());

		publisher.publish(PublishRequest.builder().topic(publishTopic).payload(order).build());

		System.out.println("Bill Paid");
	}
}