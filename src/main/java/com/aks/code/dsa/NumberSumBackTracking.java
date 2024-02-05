package com.aks.code.dsa;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

public class NumberSumBackTracking {

	private static final Set<Integer> used = new HashSet<>();
	private static final int[] f = new int[5];
	private static final int[] s = new int[4];

	static {
		Arrays.fill(f, -1);
		Arrays.fill(s, -1);
	}

	public static void main(String[] args) {

		for (int i = 1; i < 9; i++) {
			f[0] = i;
			used.add(i);
			if (process(i, 0)) {
				System.out.println(Arrays.toString(f));
				System.out.println(Arrays.toString(s));
				break;
			}
			used.remove(i);
		}
		System.out.println("Done");

	}

	private static boolean process(int i, int j) {

		if (allFill()) {
			return true;
		}
		if (i > 4 || j > 3) {
			return false;
		}

		for (int n = 1; n <= 9; n++) {
			if (!used.contains(n)) {
				int sum = f[i - 1] + n;
				if (sum <= 9 && !used.contains(sum)) {
					used.add(n);
					used.add(sum);
					f[i] = n;
					s[j] = sum;
//					print();
					boolean res = process(i + 1, j + 1);
					if (res) {
						return res;
					} else {
						used.remove(n);
						used.remove(sum);
						f[i] = -1;
						s[j] = -1;
					}
				}
			}
		}
		return false;
	}

	private static boolean allFill() {
		for (int i : f) {
			if (i == -1)
				;
			return false;
		}
		for (int i : s) {
			if (i == -1)
				;
			return false;
		}
		return true;
	}
	
	private static void print() {
		System.out.println("Print----");
		System.out.println("used: " + used);
		System.out.println("F: " + Arrays.toString(f));
		System.out.println("S: " + Arrays.toString(s));
		System.out.println("-----------------------------");
	}
}
