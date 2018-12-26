package programmers;

public class DoomsDay {

	public enum Days {
		MON(1),
		TUE(2),
		WED(3),
		THU(4),
		FRI(5),
		SAT(6),
		SUN(7);

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

	public static int year = 1978;
	public static void main(String[] args) {
		System.out.println(findDoosDays(year));
	}

	private static String findDoosDays(int year) {
		int lastTwoYear = year % 100;
		int firstRest = lastTwoYear % 12;
		int firstValue = lastTwoYear / 12;
		int secondValue = firstRest /  4;
		int total = firstRest + firstValue + secondValue + findAnchorDay(year);

		return Days.findDays(total % 7);
	}

	private static int findAnchorDay(int year) {
		if (year >= 1800 && year <= 1899) {
			return 5;
		} else if (year >= 1900 && year <= 1999) {
			return 3;
		} else if (year >= 2000 && year <= 2099) {
			return 2;
		} else if (year >= 2100 && year <= 2199) {
			return 0;
		} else if (year >= 2200 && year <= 2299) {
			return 5;
		}

		return 0;
	}

	public String solution(int a, int b) {
		String answer = "";

		return answer;
	}
}

/***
 * http://fnmj.tistory.com/199
 *
 */
