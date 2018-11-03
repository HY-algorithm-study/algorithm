package programmers;

import java.util.Arrays;

public class Landing_12913 {
	public static void main(String[] args) {
		int land[][] = {
			{1, 2, 3, 5},
			{5, 6, 7, 8},
			{4, 3, 2, 1}
		};

		int land2[][] = {
			{1, 3, 3, 5},
			{5, 6, 7, 8},
			{4, 3, 2, 1},
			{1000, 0, 9, 8}
		};

		Landing_12913 landing_12913 = new Landing_12913();
		System.out.println(landing_12913.solution(land));
	}

	int solution(int[][] land) {
		int x,y;
		x = land.length;
		y = land[0].length;

		int [][] board = new int[x][y];
		// first line copy
		for (int i = 0; i<=y-1; i++) {
			board[0][i] = land[0][i];
		}

		for (int i=1; i<x; i++) {
			for (int j=0; j<y; j++) {
				board[i][j] = land[i][j] + maxInArraysExcludeIndex(board[i-1],j);
			}
		}

		return maxInArryas(board[x-1]);
	}
	private int maxInArryas(int [] arrays) {
		return Arrays.stream(arrays).max().getAsInt();

	}
	private int maxInArraysExcludeIndex(int [] arrays, int index) {
		int size = arrays.length;
		int max = 0;
		for (int i = 0; i<size; i++) {
			if (index == i) {
				continue;
			}

			if (arrays[i] > max) {
				max = arrays[i];
			}
 		}
 		return max;
	}
}


/**
 *  풀이 방법 . 엑셀로
 *
 *
 *
 * */