package leetcode.easy;

import java.util.Arrays;

/**
 * Created by hong-pyo on 21. 4. 12.
 * Time : 오후 9:58
 */

public class TwoSum {
    public static void main(String[] args) {
        int[] request = new int[] {2, 7, 11, 15};
        TwoSum twoSum = new TwoSum();
        System.out.printf(Arrays.toString(twoSum.twoSum(request, 9)));
    }
    public int[] twoSum(int[] nums, int target) {
        int[] result = new int[] {0,0};
        for (int i=0; i < nums.length; i++) {
            for (int j=i+1; j < nums.length; j++) {
                if (nums[i] + nums[j] == target) {
                    result[0] = i;
                    result[1] = j;
                    break;
                }
            }
        }
        return result;
    }
}
