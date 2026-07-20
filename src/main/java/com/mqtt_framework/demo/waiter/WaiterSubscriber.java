package com.mqtt_framework.demo.waiter;

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
public class WaiterSubscriber {

	private final MqttSubscriber subscriber;
	private final MqttPublisher publisher;

	@PostConstruct
	public void subscribe() {

		subscriber.subscribe(SubscribeRequest.<OrderEvent>builder().topic("restaurant/+/branch/+/order/ready")
				.messageType(OrderEvent.class).listener(this::serveOrder).build());
	}

	private void serveOrder(String topic, OrderEvent order) {

		ConsolePrinter.title("Waiter");

		System.out.println("Serving Order : " + order.getOrderId());

		order.setStatus(OrderStatus.SERVED);

		String publishTopic = TopicConstants.SERVED.formatted(order.getRestaurantId(), order.getBranchId());

		System.out.println("Received Topic : " + topic);

		publisher.publish(PublishRequest.builder().topic(publishTopic).payload(order).build());

		System.out.println("Published Topic : " + publishTopic);

		System.out.println("Published SERVED");
	}
}