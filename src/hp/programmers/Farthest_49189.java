package programmers;

import java.util.*;
import java.util.stream.Collectors;

/**
 * Created by hong2 on 30/01/2019
 * Time : 10:31 PM
 */

public class Farthest_49189 {
    Map<Integer, Set<Integer>> routeMap;
    public static void main(String[] args) {
        Farthest_49189 farthest_49189 = new Farthest_49189();
        int n = 6;
        int[][] egds = {{3,6}, {4,3}, {3,2}, {1,3}, {1,2}, {2,4}, {5,2}};
        int[][] egds2 = {{1,2}, {2,3}, {3,4},{2,5},{3,6},{4,7},{6,8}};
        System.out.println(farthest_49189.solution(7, egds2));
    }

    public int solution(int n, int[][] edge) {
        routeMap = getRouteMap(edge);

        Set<Integer> keyList = new HashSet<>();
        keyList.add(1); // start from 1.

        return foundResult(keyList);
    }

    private int foundResult(Set<Integer> keyList) {
        List<Integer> key = new ArrayList<>();
        keyList.forEach(e -> {
            if (routeMap.get(e) != null)
            key.addAll(routeMap.get(e));
        });

        keyList.forEach(key::remove);

        if (key.stream().distinct().collect(Collectors.toList()).size() == 0) {
            return keyList.size();
        }
        return foundResult(new HashSet<>(key));
    }

    private Map<Integer, Set<Integer>> getRouteMap(int[][] edge) {
        Map<Integer, Set<Integer>> routeMap = new HashMap<>();
        Arrays.asList(edge).forEach(e -> {
            Set<Integer> route;
            int key, value;
            key = e[0];
            value = e[1];
            if (e[0] > e[1]) {
                key = e[1];
                value = e[0];
            }
           if (routeMap.get(key) == null) {
               route = new HashSet<>();
           } else {
               route = routeMap.get(key);
           }
            route.add(value);
           routeMap.remove(key);
           routeMap.put(key, route);
        });
        return routeMap;
    }
}
