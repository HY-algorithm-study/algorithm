import java.util.*;

public class KindCnt {

    public int solution(String[][] clothes){
        int answer = 1;

        if(clothes == null){
            return answer - 1;
        }

        Map<String, Integer> mapData = new HashMap<String, Integer>();

        check(clothes, mapData);

        Iterator<String> iter = mapData.keySet().iterator();

        while(iter.hasNext()){
            String strKey = iter.next();

            if(mapData.get(strKey) == 0) {              //의상 종류가 아무것도 없을 경우
                continue;                               //다음으로
            }else{
                answer *= (mapData.get(strKey) + 1);    //의상 종류 존재 시
            }

        }

        return answer-1;                                //아무것도 입지 않는 경우의수 제거
    }

    public static void check(String[][] clothes, Map<String, Integer> mapData){
        int cnt;

        for(String[] str : clothes){
            if(mapData.get(str[1]) == null){            //의상 종류의 최초 일때
                mapData.put(str[1], 1);
            }else{
                cnt = mapData.get(str[1]) + 1;          //기존 카운트에 +1

                mapData.put(str[1], cnt);               //누적으로 put
            }
        }

    }
    public static void main(String[] args) {
        String[][] clothes = {{"yellow_hat", "headgear"}, {"blue_sunglasses", "eyewear"}, {"green_turban", "headgear"}};

        //System.out.println("hello world");

        KindCnt kc = new KindCnt();

        int res = kc.solution(clothes);

        System.out.println(res);

    }
}
