import java.util.*;

/**
 * 의상 문제
 */
public class ClothesSelect {

    public int solution(String[][] clothes) {
        int answer = 1;

        Map<String, Integer> map = new HashMap<>();

        if(clothes == null){    //배열이 없을 경우 0
            return answer -1;
        }

        for (int i = 0; i <clothes.length ; i++) {
            String strKind = clothes[i][1];

            if(!map.containsKey(strKind)){
                map.put(strKind, 1);
            }else{
                map.put(strKind, map.get(strKind) + 1);
            }
        }
        Iterator<Integer> iter = map.values().iterator();

        while(iter.hasNext()){
            answer *= (iter.next().intValue() + 1);

        }
        return answer - 1;
    }

    public static void main(String[] args) {
        String[][] clothes = {{"yellow_hat", "headgear"}, {"blue_sunglasses", "eyewear"}, {"green_turban", "headgear"}};

        ClothesSelect cs = new ClothesSelect();

        cs.solution(clothes);
    }
}
