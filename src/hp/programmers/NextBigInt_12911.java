package programmers;

public class NextBigInt_12911 {
	public static void main(String[] args) {
		NextBigInt_12911 nextBigInt_12911 = new NextBigInt_12911();
		System.out.println(nextBigInt_12911.solution(78));
	}

	public int solution(int n) {
		int temp = Integer.bitCount(n);
		int answer = 0;

		while (temp != answer) {
			answer = Integer.bitCount(++n);
		}

		return n;
	}

	private int findNumber(int n, int count) {
		if (n < 1) {
			return count;
		}
		if (n % 2 == 1) {
			return findNumber(n / 2, count + 1);
		} else {
			return findNumber(n/2, count);
		}
	}
}


/**
 *  처음에 문제를 접했을 때는 자릿수를 어떻게 옮기면 다음 큰 수를 찾을수 있을까 고민을 하였습니다
 *	그러던 중에 1의 갯수가 같다라는 문구를 보고나서, 다음 숫자부터 2진수의 1의 갯수를 세면 되지 않을까 라는 고민을 하게되어
 *	풀게 되엇습니다
 *
 * 밑에 findNumber 라는 메소드는 처음에 1의 비트를 세기위해 만들었던 메소드이고
 * 다음에 다른사람의 풀이를 보고 나서 Integer.bitCount 라는 메서드가 있는것을 보고 이용하게 되었습니다
 *
 * */
