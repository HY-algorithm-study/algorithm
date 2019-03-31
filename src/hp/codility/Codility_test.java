package codility;


/**
 * Created by hong2 on 27/03/2019
 * Time : 11:47 PM
 */

public class Codility_test {
    public static void main(String[] args) {
        int a[] = {1,3,6,4,1,2};

        Codility_test codility_test = new Codility_test();
        System.out.println(codility_test.solution(a));
    }
    public int solution(int[] A) {

        for (int i=1; i<A.length; i++) {
             if (!checkContain(A, i))
                 return i;
        }
        return A.length+1;
    }

    private boolean checkContain(int[]A, int number) {
        for (int i = 0; i<A.length; i++) {
            if (A[i] == number)
                return true;
        }
        return false;
    }
}
