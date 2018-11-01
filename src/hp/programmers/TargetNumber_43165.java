package programmers;

/**
 * Created by hong2 on 01/11/2018
 * Time : 8:44 PM
 */

public class TargetNumber_43165 {
    int answer = 0;
    public static void main(String[] args) {
        TargetNumber_43165 targetNumber_43165 = new TargetNumber_43165();
        int [] numbers = {1,1};
        int target = 2;

        System.out.println(targetNumber_43165.solution(numbers, target));
    }
    public int solution(int[] numbers, int target) {

        return findTarget(numbers, target, 0);
    }

    private int findTarget(int[] numbers, int target, int index) {
        if (index == numbers.length) {
            return answer;
        }

        findTarget(numbers, target, index+1);
        System.out.println(numbers.toString());
        if (findSum(numbers, target)) {
            answer++;
        }
        numbers[index] *= -1;
        findTarget(numbers, target, index+1);

        return answer;
    }

    private void print(int[] num) {
        for (int i =0; i<num.length; i++) {
            System.out.print(num[i]);
        }
        
        System.out.println();
    }

    private boolean findSum(int[] numbers, int target) {
        int sum = 0;
        for (int i=0; i<numbers.length; i++) {
            sum += numbers[i];
        }

        if (sum == target) {
            return true;
        } else {
            return false;
        }
    }
}
