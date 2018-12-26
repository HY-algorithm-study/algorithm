package programmers;

import java.time.LocalDate;

public class FindMonth2 {
	public static void main(String[] args) {
		System.out.println(solution(5,24));
	}

	public static String solution(int a, int b) {
		LocalDate localDate = LocalDate.of(2016,a,b);

		return localDate.getDayOfWeek().toString().substring(0,3);
	}
}
