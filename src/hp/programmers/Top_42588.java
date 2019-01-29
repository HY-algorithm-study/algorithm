package programmers;


/**
 * Created by hong2 on 28/01/2019
 * Time : 9:53 PM
 */

public class Top_42588 {
    public static void main(String[] args) {
        Top_42588 top_42588 = new Top_42588();

        int[] heights = {6,9,5,7,4};

        System.out.println(top_42588.solution(heights));
    }

    public int[] solution(int[] heights) {
        int[] answer = new int[heights.length];

        for (int i=heights.length-1; i>0; i--) {
            for (int j = i; j>=0; j--) {
                if (heights[i] < heights[j]) {
                    answer[i] = j+1;
                    break;
                }
            }
        }
        return answer;
    }
}
