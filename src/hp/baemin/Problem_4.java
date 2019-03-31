package baemin;

/**
 * Created by hong2 on 30/03/2019
 * Time : 5:11 PM
 */

public class Problem_4 {
    public static void main(String[] args) {
        int[] A = {1, 2, 3, 4, 5, 9, 9};
        int X = 9;
        Problem_4 problem_4 = new Problem_4();
        System.out.println(problem_4.solution(A,X));
    }

    int solution(int[] A, int X) {
        int N = A.length;
        if (N == 0) {
            return -1;
        }
        int l = 0;
        int r = N - 1;
        while (l < r) {
            int m = (l + r) / 2;
            if (A[m] < X) {
                l = m + 1;
            } else {
                r = m;
            }
        }
        if (A[l] == X) {
            return l;
        }
        return -1;
    }

}

//https://en.wikipedia.org/wiki/Binary_search_algorithm
