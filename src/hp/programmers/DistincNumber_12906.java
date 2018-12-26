package programmers;

import java.util.ArrayList;
import java.util.List;

public class DistincNumber_12906 {
	public static void main(String[] args) {

		int[] arr = {0,1,1,2,2,2};
		int[] abc = solution(arr);

		for(int i=0; i< abc.length; i++) {
			System.out.println(abc[i]);
		}
	}

	public static int[] solution(int []arr) {
		int[] answer;
		List<Integer> list = new ArrayList<>();
		int temp = arr[0];
		// [실행] 버튼을 누르면 출력 값을 볼 수 있습니다.

		list.add(arr[0]);
		for (int i=0; i<arr.length; i++) {
			if (temp != arr[i] && i > 0) {
				list.add(arr[i]);
			}
			temp = arr[i];
		}

		answer = new int[list.size()];

		for (int i=0; i<list.size(); i++) {
			answer[i] = list.get(i);
		}

		return answer;
	}
}

/**
 * 풀이 방법 : 우선 첫번째 숫자는 빠질수 없는 숫자임으로 첫번째 숫자를 리스트에 넣어줍니다
 * 첫번쨰 숫자를 temp 변수에 넣어주는데 이 temp 변수는 prior number 를 의미합니다
 *
 * 그 다음에 temp 1번째에 들어가 있는 숫자부터 temp (prior number) 를 비교해서 같지않으면 증가를 하고 같으면 해당 숫자를 넘깁니다.
 * 그렇게 리스트를 구한뒤 다시 배열로 옮깁니다 (배열의 비효율성 .... ㅠㅠ)
 * */
