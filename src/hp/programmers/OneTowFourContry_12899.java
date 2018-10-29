package programmers;

public class OneTowFourContry_12899 {

	public static void main(String[] args) {
		System.out.println(solution(64));
		System.out.println(2 % 3);
	}

	public static String solution(int n) {
		String answer = "";
		int arr[] = {4,1,2};
		int remain;

		while(n > 0) {
			remain = n % 3;
			n = n / 3;
			if (remain == 0) {
				n -= 1;
			}
			answer = arr[remain] + answer;
		}
		return answer;
	}

}


/**
 *  풀이 , 숫자를 1,2,4 만 쓸수 있기 때문에 3진수를 사용하면 된다는 것을 알 수 있다
 *  n 진수 정의 : n 가지의 숫자를 사용할 수 있다
 *
 *  but 여기에서 문제점은 0 을 쓸수가 없는것이다
 *  그렇기 때문에
 *
 *  ex ) 3진수로 9 는 100(3) 이지만
 *  0을 못쓰기 때문에 한칸씩 내려가게 되어있다
 *
 *  [3-1] [0-1(4)]
 *  24 가 정답이 나온다
 *
 *  이거를 토대로 풀어보게 되면
 *  우선 3진수를 구하기 위해서

  	while(n > 0) {
 	remain = n % 3;
 	n = n / 3;

 	answer = remain + answer;
 }

 * 이런 알고리즘을 구할 수 있을것이다
 * 하지만 여기에서 문제점은 0 을 못쓰는것 0 을 쓸경우에는 앞에서 하나씩 떙겨와야 한다
 * 그렇기 때문에 0 이 되었을경우 n 을 하나씩 빼주면 나머지가 1씩 줄어들게 될것이다
 *
 * ex )
 * 9 / 3 = 3 ...0
 * 3 / 3 = 1 ...0
 * -> 100 ()
 *
 * 9 / 3 = 3...0
 * 2 / 3 = 0 ...2
 *
 * 0 번째 배열  4
 * 2 번째 배열  2
 *  24 가 정답이 되어진다.
 *
 * 3진수를 써야겠다는 생각과 , 124 라는 숫자를 배열로 만들어서 인덱스로 접근함으로써 해당 숫자들만 써야한다는 생각은 있었으나,
 * 아이디어를 구체화 하는 부분에서 생각정리가 먼저 되어지지 않아서 인터넷을 참고하였습니다..
 * */

