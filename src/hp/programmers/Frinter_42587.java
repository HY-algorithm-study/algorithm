package programmers;

import javafx.util.Pair;

import java.util.ArrayList;
import java.util.List;


public class Frinter_42587 {
	public static void main(String[] args) {
		Frinter_42587 frinter_42587 = new Frinter_42587();
		int arr [] = {2,2,2,1,3,4};
		System.out.println(frinter_42587.solution(arr, 3));
	}

	public int solution(int[] priorities, int location) {
		List<Pair<Integer, Integer>> pairList = new ArrayList<>();
		int result = 0;


		for (int i=0; i<priorities.length; i++) {
			pairList.add(new Pair<>(i, priorities[i]));
		}

		for (int i=0; i<priorities.length; i++) {
			while (sortByPriority(pairList.subList(i, pairList.size()), pairList.get(i).getValue())) {
				pairList.add(pairList.get(i));
				pairList.remove(i);
			}
		}


		for (int i=0; i<pairList.size(); i++) {
			if (pairList.get(i).getKey() == location) {
				result = i;
			}
		}
		return result+1;
	}
	private boolean sortByPriority(List<Pair<Integer, Integer>> list, int target) {

		for (int i=0; i<list.size(); i++) {
			if (list.get(i).getValue() > target) {
				return true;
			}
		}

		return false;
	}

}
