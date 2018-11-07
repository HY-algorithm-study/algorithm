package programmers;

import java.util.*;
import java.util.stream.Collectors;

/**
 * Created by hong2 on 03/11/2018
 * Time : 5:57 PM
 */

public class ChangeWord_43163 {
    Map<String, List<String>> wordSet;

    public static void main(String[] args) {
        String begin = "hit";
        String target = "cog";

        String[] words = {"hot", "dot", "dog", "lot", "log", "cog"};
        //String[] words = {"hot", "dot", "dog", "lot", "log", "cog"};

        ChangeWord_43163 changeWord_43163 = new ChangeWord_43163();
        System.out.println(changeWord_43163.solution(begin, target, words));
    }

    public int solution(String begin, String target, String[] words) {
        int answer = 0;
        List<String> strings = Arrays.asList(words);
        if (!strings.contains(target)) {
            return 0;
        }
        wordSet = makeWordSet(begin, words);

        Set<String> candidate = new HashSet<>();
        Map<String, List<String>> available = new HashMap<>();

        candidate.add(begin);
        available.put(begin, wordSet.get(begin));
        answer = findOptimizeRoute(candidate, available, target, answer);

        return answer+1;
    }

   private int findOptimizeRoute(Set<String> candidate, Map<String, List<String>> available, String target, int count) {

        Set<String> temp = new HashSet<>();
        Map<String, List<String>> availMap = new HashMap<>();
        for (String word : candidate) {
            if (available.get(word).contains(target)) {
                return count;
            }
            temp.addAll(available.get(word));
        }

        for (String word : temp) {
            availMap.put(word, wordSet.get(word));
        }
       count = count + 1;

       return findOptimizeRoute(temp, availMap, target, count );
   }

    // 각 단어마다 갈 수 있는 리스트들을 만들어줌.
    private Map<String, List<String>> makeWordSet(String begin, String[] words) {
        Map<String, List<String>> stringListMap = new HashMap<>();

        stringListMap.put(begin, findWordLoad(begin, words));

        for(String word : words) {
            stringListMap.put(word, findWordLoad(word, words));
        }

        return stringListMap;
    }


    // 갈 수 있는 길의 리스트들을 뽑아줌
    private List<String> findWordLoad(String begin, String[] words) {
        return Arrays.asList(words).stream().filter(word -> checkSumString(begin, word)).collect(Collectors.toList());
    }

    // 갈 수 있는 길을 찾는다.
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
