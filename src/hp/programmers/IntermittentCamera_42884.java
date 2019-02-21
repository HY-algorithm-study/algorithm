package programmers;


import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Created by hong2 on 19/02/2019
 * Time : 10:25 PM
 */

public class IntermittentCamera_42884 {

    public static void main(String[] args) {
        int[][] routes = {{-20,15}, {-14,-5}, {-18,-13}, {-5,-3}};
        IntermittentCamera_42884 camera42884 = new IntermittentCamera_42884();
        System.out.println(camera42884.solution(routes));
    }

    public int solution(int[][] routes) {
        int answer = 0;

        List<int[]> ans = Arrays.asList(routes);
        ans = ans.stream().sorted(this::sort).collect(Collectors.toList());

        int lastCamera = Integer.MIN_VALUE;
        for (int[] a : ans) {
            if (lastCamera < a[0]) {
                answer++;
                lastCamera = a[1];
            }
        }
        return answer;
    }

    public int sort(int[] a, int[] b) {
        if (a[1] > b[1])
            return 1;
        else
            return -1;
    }
}
