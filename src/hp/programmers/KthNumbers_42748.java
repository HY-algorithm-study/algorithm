package programmers;

import java.util.Arrays;
import java.util.stream.IntStream;

/**
 * Created by hong2 on 29/12/2018
 * Time : 5:15 PM
 */

public class KthNumbers_42748 {
    public static void main(String[] args) {
        KthNumbers_42748 kthNumbers_42748 = new KthNumbers_42748();
        int[] array = {1, 5, 2, 6, 3, 7, 4};
        int[][] command = {{2, 5, 3}, {4, 4, 1}, {1, 7, 3}};
        System.out.println(Arrays.toString(kthNumbers_42748.solution(array, command)));
    }

    public int[] solution(int[] array, int[][] commands) {

        int round = commands.length;
        int[] answer = new int[round];

        for(int i=0; i<round; i++) {

            answer[i] = getKthNumbers(array, commands[i][0], commands[i][1], commands[i][2]);
        }

        return IntStream.of(answer).toArray();
    }

    private int getKthNumbers(int[] array, int bigin, int end, int target) {
        return Arrays.stream(Arrays.copyOfRange(array, bigin-1, end))
                .sorted()
                .toArray()[target-1];
    }
}
