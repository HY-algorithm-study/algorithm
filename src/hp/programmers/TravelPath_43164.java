package programmers;

import java.util.*;
import java.util.stream.Collectors;

/**
 * Created by hong2 on 10/11/2018
 * Time : 5:04 PM
 */

public class TravelPath_43164 {
    int ticketSize = 0;

    public static void main(String[] args) {
        TravelPath_43164 travelPath_43164 = new TravelPath_43164();

        /*[[ICN, JFK], [HND, IAD], [JFK, HND]]*/
        /*[[ICN, SFO], [ICN, ATL], [SFO, ATL], [ATL, ICN], [ATL,SFO]]*/
        //String[][] travelPath = {{"ICN", "JFK"}, {"HND", "IAD"}, {"JFK","HND"}};
        String[][] travelPath = {{"ICN", "SFO"}, {"ICN", "ATL"}, {"SFO","ATL"}, {"ATL", "ICN"}, {"ATL", "SFO"}};
        //String [][] travelPath = {{"ICN", "ZZZ"}, {"ZZZ", "ICN"}, {"ICN", "SSS"}};
        System.out.println(String.join(", ", Arrays.asList(travelPath_43164.solution(travelPath))));
    }

    public String[] solution(String[][] tickets) {
        Map<String, List<String>> travelRoute = getTravelRoute(tickets);
        ticketSize = tickets.length + 1;
        List<String> answer = new ArrayList<>();
        answer.add("ICN");
        answer = findRout2(travelRoute, answer, "ICN");

        return answer.toArray(new String[answer.size()]);
    }

    private List<String> findRoute3(Map<String, List<String>> travelRoute, List<String> answer, String start) {
        List<String> availableDes = travelRoute.get(start);
        if (availableDes == null) {
            return answer;
        }



        return null;
    }

    private List<String> findRout2(Map<String, List<String>> travelRoute, List<String> answer, String start) {


        if (passCondition(travelRoute, answer, start)) {
            return answer;
        }

        for(int i=0; i<travelRoute.get(start).size(); i++) {
            if (passCondition(travelRoute, answer, start)) {
                return answer;
            }
            String destination = travelRoute.get(start).get(i);

            List<String> temp = copyExtractWord(travelRoute.get(start), destination);
            Map<String, List<String>> tempMap = Mapremove(travelRoute, temp, start);

            List<String> newAnswer = new ArrayList<>(answer);
            newAnswer.add(destination);

            answer = findRout2(tempMap, newAnswer, destination);
        }

        return answer;
    }

    private boolean passCondition(Map<String, List<String>> travelRoute, List<String> answer, String start) {
        return travelRoute.get(start) == null || answer.size() == ticketSize;
    }

    private Map<String, List<String>> Mapremove(Map<String, List<String>> map, List<String> value, String key) {
        Map<String, List<String>> stringListMap = new HashMap<>(map);

        stringListMap.put(key, value);
        return stringListMap;
    }

    private List<String> copyExtractWord(List<String> list, String word) {
        List<String> strings = new ArrayList<>();
        for(String st : list) {
            if (st.equals(word)) {
                continue;
            }
            strings.add(st);
        }
        return strings;
    }

    private Map<String, List<String>> getTravelRoute(String[][] tickets) {
        Map<String, List<String>> temp = new HashMap<>();
        for (int i=0; i<tickets.length; i++) {
            addValue(temp, tickets[i][0], tickets[i][1]);
        }

        return temp;
    }

    private void addValue(Map<String, List<String>> map, String key, String value) {
        List<String> values = new ArrayList<>();
        if (map.get(key) != null) {
            values = map.get(key);
            values.add(value);
        } else {
            values.add(value);
        }
        map.put(key, values.stream().sorted().collect(Collectors.toList()));
    }
}
