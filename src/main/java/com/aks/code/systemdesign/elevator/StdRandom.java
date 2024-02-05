package com.aks.code.systemdesign.elevator;

import java.util.Random;

public class StdRandom {
	private static final Random random = new Random();

	public static void shuffle(int[] elevatorIDs) {
		int n = elevatorIDs.length;
		for (int i = 0; i < n; i++) {
			int r = i + random.nextInt(n - i);
			int temp = elevatorIDs[i];
			elevatorIDs[i] = elevatorIDs[r];
			elevatorIDs[r] = temp;
		}
	}
}
