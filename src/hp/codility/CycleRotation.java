package codility;

/**
 * Created by hong2 on 29/03/2019
 * Time : 11:10 PM
 */

public class CycleRotation {
    public static void main(String[] args) {
        int [] A = {3, 8, 9, 7, 6};
        int k = 3;
        CycleRotation cycleRotation = new CycleRotation();
        System.out.println(cycleRotation.solution(A, k));
    }

    public int[] solution(int[] A, int K) {

        for (int i=0; i<K; i++) {
            A = rotation(A);
        }
        return A;
    }

    private int[] rotation(int[] A) {
        int temp = A[A.length-1];
        for (int i=A.length-1; i >= 1; i--) {
            A[i] = A[i-1];
        }
        A[0] = temp;

        return A;
    }
}
