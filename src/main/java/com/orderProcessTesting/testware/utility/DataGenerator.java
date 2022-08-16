package com.orderProcessTesting.testware.utility;

import java.util.Random;

public class DataGenerator {

	public static long getRandomNumberOfDigits(int length) {
		char[] chars = "123456789".toCharArray();
		Random rnd = new Random();
		StringBuilder sb = new StringBuilder();
		for (int i = 0; i < length; i++)
			sb.append(chars[rnd.nextInt(chars.length)]);

		return Long.valueOf(sb.toString());
	}

	public static String generateRandomStringWithSpaces() {
		Random rd = new Random();
		String aphaNumericString = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ";
		StringBuilder sb = new StringBuilder();

		for (int i = 1; i < 20; i++) {
			sb.append(aphaNumericString.charAt(rd.nextInt(aphaNumericString.length())));
			if(i%5==0) {
				sb.append(" ");
			}
		}
		return sb.toString();
	}


	public static String generateRandomStatus() {
		String[] orderStatus = {"OrderConfirmed","AcceptedByRestaurant","OrderPreparing", "DeliveryAgentAssigned",
								"ReadyForPickup", "OutForDelivery","Delivered"};
		return orderStatus[(int) (Math.random() * orderStatus.length)];
	}
	public static String getCurrentDateTimeTimeStamp() {
		long timestamp = System.currentTimeMillis();
		return String.valueOf(timestamp);
	}

	public static boolean getRandomBoolean() {
		Random rd = new Random();
		return rd.nextBoolean();
	}

}
