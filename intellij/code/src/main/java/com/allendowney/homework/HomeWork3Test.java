/**
 * 
 */
package com.allendowney.homework;

import org.junit.Before;
import org.junit.Test;

import java.io.IOException;
import java.util.Scanner;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

/**
 * @author downey
 *
 */
public class HomeWork3Test {

	private HomeWork3 ht;

	@Before
	public void setUp() {
		ht = new HomeWork3();
	}

	@Test
	public void testHomeWork3_2() throws IOException {
		Scanner sc = new Scanner(System.in);
		int n = 5;
		int m = 3;
		int[] input = new int[]{4, 5, 5, 6, 7};
		ht.makeCountMap(n, m, input);
		ht.solution(0, new int[m]);

		assertThat(ht.count, is(33));
		assertThat(ht.result, is(
				"4 5 5 \n" +
				"4 5 6 \n" +
				"4 5 7 \n" +
				"4 6 5 \n" +
				"4 6 7 \n" +
				"4 7 5 \n" +
				"4 7 6 \n" +
				"5 4 5 \n" +
				"5 4 6 \n" +
				"5 4 7 \n" +
				"5 5 4 \n" +
				"5 5 6 \n" +
				"5 5 7 \n" +
				"5 6 4 \n" +
				"5 6 5 \n" +
				"5 6 7 \n" +
				"5 7 4 \n" +
				"5 7 5 \n" +
				"5 7 6 \n" +
				"6 4 5 \n" +
				"6 4 7 \n" +
				"6 5 4 \n" +
				"6 5 5 \n" +
				"6 5 7 \n" +
				"6 7 4 \n" +
				"6 7 5 \n" +
				"7 4 5 \n" +
				"7 4 6 \n" +
				"7 5 4 \n" +
				"7 5 5 \n" +
				"7 5 6 \n" +
				"7 6 4 \n" +
				"7 6 5 \n"));
	}

}
