package programmers;

import java.util.Arrays;

public class DivisorNumber_12910 {
	public static void main(String[] args) {
		int arr[] = {2, 36, 1, 3};
		int divisor = 1;

		solution(arr, divisor);
	}


	public static int[] solution(int[] arr, int divisor) {

		int[] answer = {-1};

		int[] result = Arrays.stream(arr)
			.filter(e -> e % divisor == 0)
			.sorted()
			.toArray();

		if (result.length != 0) {
			return result;
		} else {
			return answer;
		}
	}
}

/**
 * 	stream 을 이용해서 풀었습니다.
 * 	divisor 로 나누어 떨어지는 것을 구해야 함으로 filter 를 걸어서 나머지가 0인것들을 걸러내고
 * 	순서대로 정렬을 해줘야 하기 때문에 정렬을 이용하였습니다
 * */