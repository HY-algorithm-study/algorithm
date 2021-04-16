package leetcode.easy;

/**
 * Created by hong-pyo on 21. 4. 16.
 * Time : 오후 9:02
 */

public class PalindromeNumber {
    public static void main(String[] args) {
        Solution solution = new Solution();

    }
    public static class Solution {
        public boolean isPalindrome(int x) {
            if (x < 0) {
                return false;
            }

            if (x == reverse(x)) {
                return true;
            } else return false;
        }

        private long reverse(int x) {
            long result = 0;
            while(x != 0) {
                result = result * 10 + x % 10;
                x = x / 10;
            }
            return result;
        }
    }
}
