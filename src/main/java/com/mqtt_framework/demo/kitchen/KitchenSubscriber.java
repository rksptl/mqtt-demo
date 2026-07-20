package com.mqtt_framework.demo.kitchen;

import org.springframework.stereotype.Component;

import com.mqtt_framework.demo.common.constants.TopicConstants;
import com.mqtt_framework.demo.common.enums.OrderStatus;
import com.mqtt_framework.demo.common.model.OrderEvent;
import com.mqtt_framework.demo.util.ConsolePrinter;
import com.rkp.framework.mqtt.model.PublishRequest;
import com.rkp.framework.mqtt.model.Qos;
import com.rkp.framework.mqtt.model.SubscribeRequest;
import com.rkp.framework.mqtt.publisher.MqttPublisher;
import com.rkp.framework.mqtt.subscriber.MqttSubscriber;

import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class KitchenSubscriber {

	private final MqttSubscriber subscriber;
	private final MqttPublisher publisher;

	@PostConstruct
	public void subscribe() {

		String topic = "restaurant/+/branch/+/order/created";

		subscriber.subscribe(SubscribeRequest.<OrderEvent>builder().topic(topic).messageType(OrderEvent.class)
				.listener(this::process).qos(Qos.AT_LEAST_ONCE).build());
	}

	private void process(String topic, OrderEvent order) {

		try {

			ConsolePrinter.title("Kitchen");

			System.out.println("Topic : " + topic);
			System.out.println("Received Order : " + order.getOrderId());

			publish(order, OrderStatus.ACCEPTED);

			Thread.sleep(1000);

			publish(order, OrderStatus.PREPARING);

			Thread.sleep(2000);

			publish(order, OrderStatus.READY);

		} catch (InterruptedException e) {
			Thread.currentThread().interrupt();
		}
	}

	private void publish(OrderEvent order, OrderStatus status) {

		order.setStatus(status);

		String topic = switch (status) {

		case ACCEPTED -> TopicConstants.ACCEPTED.formatted(order.getRestaurantId(), order.getBranchId());

		case PREPARING -> TopicConstants.PREPARING.formatted(order.getRestaurantId(), order.getBranchId());

		case READY -> TopicConstants.READY.formatted(order.getRestaurantId(), order.getBranchId());

		default -> throw new IllegalArgumentException();
		};

		publisher.publish(PublishRequest.builder().topic(topic).payload(order).qos(Qos.AT_LEAST_ONCE).build());

		System.out.printf("Published %s -> %s%n", status, topic);
	}
}