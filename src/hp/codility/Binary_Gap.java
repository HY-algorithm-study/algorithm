package codility;

/**
 * Created by hong2 on 29/03/2019
 * Time : 12:31 AM
 */

public class Binary_Gap {
    public static void main(String[] args) {
        Binary_Gap binary_gap = new Binary_Gap();
        System.out.println(binary_gap.solution(32));
    }

    public int solution(int N) {
        String binary = Integer.toBinaryString(N);
        int temp = 0;
        int result = 0;

        for (int i=1; i<binary.length(); i++) {
            if (binary.charAt(i) == '0') {
                temp++;
            } else {
                if (result < temp) {
                    result = temp;
                    temp = 0;
                }
            }
        }
        return result;
    }
}
