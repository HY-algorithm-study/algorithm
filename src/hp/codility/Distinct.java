package codility;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by hong2 on 30/03/2019
 * Time : 12:02 AM
 */

public class Distinct {
    public static void main(String[] args) {
        int [] A = {2, 1, 1, 2, 3, 1,3,3,3,3,5,5};
        Distinct distinct = new Distinct();
        System.out.println(distinct.solution(A));
    }
    public int solution(int[] A) {
        Map<Integer, Integer> distinctMap = new HashMap<>();
        List<Integer> tempList = new ArrayList<>();
        for (int i=0; i<A.length; i++) {
            if (distinctMap.get(A[i]) == null) {
                distinctMap.put(A[i], 0);
                tempList.add(A[i]);
            } else {
                tempList.remove((Integer)A[i]);
            }
        }
        return tempList.get(0);
    }

}
