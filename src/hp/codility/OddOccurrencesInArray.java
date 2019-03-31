package codility;


import java.util.*;

/**
 * Created by hong2 on 29/03/2019
 * Time : 11:46 PM
 */

public class OddOccurrencesInArray {
    public static void main(String[] args) {
        int [] A = {9, 3, 9 ,3, 9, 7, 9};
        OddOccurrencesInArray oddOccurrencesInArray = new OddOccurrencesInArray();
        System.out.println(oddOccurrencesInArray.solution(A));
    }

    public int solution(int[] A) {
        Map<Integer, Integer> occurrencesMap = new HashMap<>();
        for (int i = 0; i<A.length; i++) {
            if (occurrencesMap.get(A[i]) == null) {
                occurrencesMap.put(A[i], 0);
            } else {
                occurrencesMap.remove(A[i]);
            }

        }


        return occurrencesMap.keySet().stream().findFirst().get();
    }
}
