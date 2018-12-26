package programmers;

import java.util.stream.IntStream;

public class FindMonth {

	public enum Days {
		MON(4),
		TUE(5),
		WED(6),
		THU(0),
		FRI(1),
		SAT(2),
		SUN(3);

		int dayOfNumber;

		Days(int dayOfNumber) {
			this.dayOfNumber = dayOfNumber;
		}

		int getDayOfNumber() {
			return this.dayOfNumber;
		}

		public static String findDays(int days) {
			for(Days day : Days.values()) {
				if (day.getDayOfNumber() == days) {
					return day.name();
				}
			}
			return "";
		}

	}

	public static void main(String[] args) {

		System.out.println(solution(10,31));
	}

	public static String solution(int a, int b) {
		int totalDays = IntStream.rangeClosed(1, a-1)
			.map(FindMonth::findDays)
			.sum();

		totalDays = totalDays + b;
		return Days.findDays(totalDays % 7);
	}

	static int findDays(int month) {
		if (month == 1) {
			return 31;
		} else if (month == 2) {
			return 29;
		}

		if (month > 7) {
			return findDays(month - 5);
		}

		if (month % 2 == 0) {
			return 30;
		} else {
			return 31;
		}
	}
}

/**
 *
 * 윤달 구하기,
 * 1,2 월은 31, 28 로 고정을 시켜두고
 * 	3월 	4월	5월	6월	7월 		=> 방향
 * 	31	30	31	30	31
 *
 * 	12월	11월	10월 9월 8월		<= 방향
 * 	31	30	31	30	31
 *
 * 	일상에서 해당 달에 몇일 까지 있을때 손가락으로 세는 방식을 이용해 봤다. 우리 손가락은 5개의 마디로 구성되어 있으므로, 5개를 기준으로 넘어가게 되어있다.
 * 	하지만 7월이 왔을때는 반대로 돌기 때문에 7을 기준으로 -5 를 해주는 방식으로 해당 달의 일을 구할 수 있게 되었다.
 *
 *
 * */