package programmers;

import java.util.stream.LongStream;

public class SumOfTwoNumber_12912 {

	public static void main(String[] args) {
		System.out.println(solution(3,5));
	}

	public static long solution(int a, int b) {
		return a < b ? LongStream.rangeClosed(a,b).sum() :
			LongStream.rangeClosed(b,a).sum();
	}
}

/**
 *
 * stream 을 이용하여 rangeClosed (닫힌)
 * ex) 1,3 = 1,2,3 까지를 구함
 *
 * 즉 3,5 = 3,5
 * 를 구합니다
 *
 * sum 메소드를 이용함으로써 그들의 합을 구하였습니다
 * */