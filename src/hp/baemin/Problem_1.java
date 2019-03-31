package baemin;

/**
 * Created by hong2 on 30/03/2019
 * Time : 5:10 PM
 */

public class Problem_1 {
    public static void main(String[] args) {
        Problem_1 problem_1 = new Problem_1();
        System.out.println(problem_1.solution("011100"));
    }
    public int solution(String S) {
        int result = 0;
        for (int i=0; i<S.length(); i++) {
            if (invalidFirstNumber(S, result, i)) {
                continue;
            } else if (S.charAt(i) == '1'){
                result++;
            } else {
                result = result + 2;
            }
        }
        return result;
    }

    private boolean invalidFirstNumber(String S, int result, int i) {
        return S.charAt(i) == '0' && result == 0;
    }
}
