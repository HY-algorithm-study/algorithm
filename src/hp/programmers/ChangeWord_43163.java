package programmers;

import com.google.common.collect.Maps;

import java.util.Arrays;
import java.util.List;
import java.util.Map;

/**
 * Created by hong2 on 03/11/2018
 * Time : 5:57 PM
 */

public class ChangeWord_43163 {
    public static void main(String[] args) {
        String begin = "hit";
        String target = "cog";

        String[] words = {"hot", "dot", "dog", "lot", "log", "cog"};

        ChangeWord_43163 changeWord_43163 = new ChangeWord_43163();
        changeWord_43163.solution(begin, target, words);
    }

    public int solution(String begin, String target, String[] words) {
        List<String> strings = Arrays.asList(words);
        Map<String, List<String>> stringListMap = Maps.newHashMap();
        if (!strings.contains(target)) {
            return 0;
        }

        int answer = 0;
        return answer;
    }

    private boolean checkSumString(String a, String b) {
        int result = 0;
        if (a.length() != b.length()) {
            return false;
        }

        for(int i = 0; i<a.length(); i++) {
            if (a.charAt(i) != b.charAt(i)) {
                result++;
            }
        }

        if (result == 1) {
            return true;
        } else {
            return false;
        }
    }
}
