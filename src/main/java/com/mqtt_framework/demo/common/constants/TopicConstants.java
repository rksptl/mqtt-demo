package com.mqtt_framework.demo.common.constants;

public final class TopicConstants {

	private TopicConstants() {
	}

	public static final String CREATED = "restaurant/%s/branch/%s/order/created";

	public static final String ACCEPTED = "restaurant/%s/branch/%s/order/accepted";

	public static final String PREPARING = "restaurant/%s/branch/%s/order/preparing";

	public static final String READY = "restaurant/%s/branch/%s/order/ready";

	public static final String SERVED = "restaurant/%s/branch/%s/order/served";

	public static final String PAID = "restaurant/%s/branch/%s/order/paid";

}