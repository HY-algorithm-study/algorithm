package leetcode.easy;

/**
 * Created by hong-pyo on 21. 4. 12.
 * Time : 오후 10:27
 */

// https://leetcode.com/problems/reverse-integer/

public class ReverseInteger {
    public static void main(String[] args) {
        Solution solution = new Solution();
        System.out.println(solution.reverse(1534236469));
        System.out.println((int)Math.log10(Math.abs(-1203))+1);
    }
    public static class Solution {
        public int reverse(int x) {
            int result = 0;
            if (x > 1463847412 || x <= -2147483648) {
                return 0;
            }
            while(x != 0) {
                result = result * 10 + x % 10;
                x = x / 10;
            }
            if (result > 2147483641) {
                return 0;
            }
            return result;
        }
    }
}
