package com.mqtt_framework.demo.order;

import org.springframework.stereotype.Component;

import com.mqtt_framework.demo.common.constants.TopicConstants;
import com.mqtt_framework.demo.common.model.OrderEvent;
import com.rkp.framework.mqtt.publisher.MqttPublisher;

import lombok.RequiredArgsConstructor;
import com.rkp.framework.mqtt.model.PublishRequest;
import com.rkp.framework.mqtt.model.Qos;

@Component
@RequiredArgsConstructor
public class OrderPublisher {

	private final MqttPublisher mqttPublisher;

	public void publishCreated(OrderEvent order) {

		String topic = TopicConstants.CREATED.formatted(order.getRestaurantId(), order.getBranchId());

		mqttPublisher.publish(
				PublishRequest.builder().topic(topic).payload(order).qos(Qos.AT_LEAST_ONCE).retained(false).build());

		System.out.printf("[Order Service] Published CREATED -> %s%n", topic);
	}
}