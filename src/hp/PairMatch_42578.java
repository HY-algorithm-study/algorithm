package programmers;

import java.util.*;
import java.util.stream.IntStream;

/**
 * Created by hong2 on 28/11/2018
 * Time : 12:17 AM
 */

public class PairMatch_42578 {
    HashSet<String> keylist = new HashSet<>();
    public static void main(String[] args) {
        PairMatch_42578 pairMatch_42578 = new PairMatch_42578();

        //String[][] clothes = {{"yellow_hat","headgear"}, {"blue_sunglasses", "eyewear"},{"green_turban", "headgear"}};
        String[][] clothes = {{"yellow_hat","headgear"}, {"blue_sunglasses", "headgear"},{"green_turban", "headgear"},
                {"green_turban", "upper"},{"blue_sunglasses", "upper"},{"green_turban", "downner"}};
        System.out.println(pairMatch_42578.solution(clothes));
    }

    public int solution(String[][] clothes) {
        int answer;
        Map<String, List<String>> result = getClothList(clothes);
        List<Integer> clothCountList = new ArrayList<>();

        for (String key : result.keySet()) {
            clothCountList.add(result.get(key).size());
        }

        if (result.keySet().size() == 1) {
            return clothCountList.get(0);
        }

        answer = clothCountList.stream().mapToInt(e -> (e + 1)).reduce(1, (a, b) -> a * b);
        return answer-1;
    }



    private Map<String, List<String>> getClothList(String[][] clothes) {
        Map<String, List<String>> clotheMap = new HashMap<>();

        for (String[] cloth : clothes) {
            String value = cloth[0];
            String key = cloth[1];
            if (clotheMap.get(key) == null) {
                List<String> values = new ArrayList<>();
                values.add(value);
                clotheMap.put(key, values);
            } else {
                List<String> values = clotheMap.get(key);
                values.add(value);
                clotheMap.put(key, values);
            }
            keylist.add(key);
        }

        return clotheMap;
    }
}
