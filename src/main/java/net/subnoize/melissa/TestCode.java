package net.subnoize.melissa;

import java.util.Arrays;

public class TestCode {

	public static void main(String... args) {

		double[] sales = { 12.95, 3.99, 5.99 };
		double[] expense = { 1.99, 2.99 };

		double total = 0;

		for (double s : sales) {
			total += s;
		}

		for (double e : expense) {
			total -= e;
		}

		System.out.println("Total: " + total);

		double taxAmount = 0;

		if (total > 0) {
			taxAmount = total * 0.10;
		}

		System.out.println("  Tax: " + taxAmount);

	}

}
