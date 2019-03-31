package codility;

import java.util.Arrays;

/**
 * Created by hong2 on 28/03/2019
 * Time : 12:51 AM
 */

public class problem_1 {

    public static void main(String[] args) {
        problem_1 problem_1 = new problem_1();
        System.out.println(Arrays.toString(problem_1.soulution(52910)));
    }

    public int[] soulution(int money) {
        int[] answer = new int[9];
        int[] sort_money = new int[]{50000, 10000, 5000, 1000, 500, 100, 50, 10, 1};

        for (int i = 0; i < sort_money.length; i++) {
            if (i==0) {
                answer[i] = (money / sort_money[i]);
            } else {
                answer[i] = (money % sort_money[i-1]) / sort_money[i];
            }
        }

        return answer;
    }
}
