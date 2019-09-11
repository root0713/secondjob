package net.bestmember.isjung.lotto;

import java.util.Scanner;

public class Powerball {
	public static void main(String[] args) {
		Scanner inp = new Scanner(System.in);
		final int MAX = 69;
		int[] set = new int[MAX]; // Holds the set of numbers
		int cnt, // General purpose loop counter
				elem; // What is the range of numbers?
		int temp; // Random number generated
		String response; // Another set of numbers?

		do {
			elem = MAX;
			System.out.print("\nNumbers:  ");

			// Fill the array with the possible numbers.
			for (cnt = 0; cnt < MAX; cnt++)
				set[cnt] = cnt + 1;

			// Pick 5 numbers.
			for (cnt = 0; cnt < 5; cnt++) {
				// Random number points to an element of the array.
				temp = (int) (Math.random() * elem);

				// Add the number picked to the output string
				System.out.print(set[temp] + "  ");

				// "Close down" the array to remove the number selected.
				for (int i = temp; i < elem - 1; i++)
					set[i] = set[i + 1];

				// Decrease the maximum number
				elem--;
			}

			int powerball = (int) (Math.random() * 26) + 1;
			System.out.println(" Powerball: " + powerball);

			System.out.print("\nEnter \"y\" for another set of numbers," + "\"n\" to quit: ");
			response = inp.next();
			response = response.toLowerCase();
		} while (response.equals("y"));
	}
}
