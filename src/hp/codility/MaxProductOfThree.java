package codility;

import java.util.List;

/**
 * Created by hong2 on 30/03/2019
 * Time : 12:26 AM
 */

public class MaxProductOfThree {
    public static void main(String[] args) {
        int [] A = {-3, 1, 2, -2, 5,6};
        MaxProductOfThree maxProductOfThree = new MaxProductOfThree();
        System.out.println(maxProductOfThree.solution(A));
    }

    public int solution(int[] A) {
        // write your code in Java SE 8
        int firstMax =  Integer.MIN_VALUE;
        int multipleMax = Integer.MIN_VALUE;
        int maxIndex = 0;
        for(int i=0; i<A.length; i++) {
            if (firstMax < A[i]) {
                firstMax = A[i];
                maxIndex = i;
            }
        }
        A[maxIndex] = 1;

        for (int i=0; i<A.length; i++) {
            for (int j = 0; j<A.length; j++) {
                if (i != j && multipleMax < A[i]*A[j])
                    multipleMax = A[i]*A[j];
            }
        }
        return firstMax * multipleMax;
    }
}
