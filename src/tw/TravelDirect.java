import javax.swing.text.html.HTMLDocument;
import java.util.*;

/**
 * 여행경로 문제
 * 1. 시작점에서 갈수 있는 경우의 수 체크
 * 2. 갈 수 있는 곳이 2개 이상 존재 시 알파벳 순으로 가장 큰 배열 선택 후 이동
 * 3. 중복(이미 간 경로)일 경우 제외
 * 4. 경로 추가
 */
public class TravelDirect {


    public static List<String> disData = new ArrayList<String>();

    public static int cnt = 0;
    public static String[] solution(String[][] tickets) {
        String[] answer = {};

        String dot = tickets[0][0];

        disData.add(dot);

        search(dot, tickets);

        return answer;
    }

    public static void search(String dot, String[][] tickets){

        boolean check = false;

        List<String> listData = new ArrayList<String>();

        List<Map<String, Object>> listData2 = new ArrayList<Map<String, Object>>();


        Map<String, Object> mapData = new HashMap<String, Object>();

        Set< Map<String,String>> setRail = new HashSet< Map<String,String>>();

        //시작점과 동일한 도시 찾은 후 방문할 수 있는 도시 리스트에 저장(추후 함수로 빼낼 예정 => 반복해야 되기 때문)
        for (int i = 0; i < tickets.length; i++) {

            for (int j = 0; j < tickets[i].length; j++) {
                if (dot.equals(tickets[i][j])) {  //시작점과 동일할 경우
                    if(j == 0){
                        listData.add(tickets[i][j+1]);
                       // mapData.put(tickets[i][j+1], dot);

                        //System.out.println(mapData);
                        //listData2.add(mapData);

                    }
                }
            }
        }

        //System.out.println(listData);
        //알파벳 체크 함수 호출 ->
        String str = alphabetCheck(listData);

        if(str.equals("no")){
            return;
        }else{
            check = distinctCheck(str);
            if(!check){
                disData.add(str);
                search(str, tickets);
            }

        }




    }
    /**
     * 2.알파벳 순으로 가장 큰 문자 하나 뽑아내기
     * @param args
     * @return
     */
    public static String alphabetCheck(List<String> args) {
        String strCheck= "";

        String[] array = new String[args.size()];

        //방문할 수 있는 도시가 한개인 경우 그대로 리턴
        if(args.size() == 1){
            strCheck = args.get(0);
        }
        //방문할 수 있는 도시가 2개 이상인 경우 알파벳 순으로 가장 높은 도시 리턴
        else if(args.size() > 1){
            for (int i = 0; i < args.size(); i++) {
                array[i] = args.get(i);

            }
            Arrays.sort(array);


            strCheck = array[0];

            //알파벳 추출한 도시가 이미 최종경로에 있을 경우
            if(distinctCheck(strCheck)){
                strCheck = array[1];
            }
        }

        //없을 경우 no 리턴(추후 예외 처리)
        else{
            strCheck ="no";
        }


        return strCheck;
    }

    /**
     * 3. 알파벳 순 체크 후 중복이 있는지 확인, 없으면 리스트에 저장
     * @return
     */
    public static Boolean distinctCheck(String city){
        boolean disCheck = false;

        //최종 경로에 추출한 도시가 이미 있을 경우
        if(disData.contains(city)){
            disCheck = true;
        }

        return disCheck;
    }

    /**
     * 메인 함수
     * @param args
     */
    public static void main(String[] args) {
        //System.out.println("hello world");

        String[][] tickets = {{"ICN", "SFO"}, {"ICN", "ATL"}, {"SFO", "ATL"}, {"ATL", "ICN"}, {"ATL", "SFO"}};

        String[] result = solution(tickets);
        System.out.println(disData);
    }
}
