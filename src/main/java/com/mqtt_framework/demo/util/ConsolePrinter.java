package com.mqtt_framework.demo.util;

public final class ConsolePrinter {

	private ConsolePrinter() {
	}

	public static void title(String service) {

		System.out.println();
		System.out.println("================================================");
		System.out.println(service);
		System.out.println("================================================");

	}

}