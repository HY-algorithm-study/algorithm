package programmers;

public class MiddleText {

	public static void main(String[] args) {

		System.out.println(solution("avcd"));
	}

	public static String solution(String s) {
		return s.length() % 2 == 1 ?
			s.substring(s.length()/2, s.length()/2+1) :
			s.substring(s.length()/2-1 , s.length()/2+1);
	}

}

/**
 *
 * 풀이 방법 :
 * 문자열의 길이를 구하여서
 *
 * 홀수 이면 가운데 글자만,
 * 짝수 이면 중간 전, 중간 후 글자 까지를 골라서 리턴한다.
 *
 *
 * ex)
 * 			01234
 *	 s : 	abcde
 *
 * 	홀 수 인경우
 *
 * 	s.length() = 5.
 * 	홀 수 이기 떄문에 5/2 = 2
 * 	즉 2번째 글자만 가져오면 해답을 구할 수 있다
 *
 * 	짝 수 인경우
 *
 * 			0123
 * 	s	:	abcd
 *
 * 	s.length() = 4
 * 	짝수이기 떄문에
 * 	1~2 까지 즉 4/2 -1, 4/2 + 1 (1,3) 의 글자를 가져오면 된다.
 * 	subString 의 결과는 endPoint - startPoint 의 글자수를 startPoint 를 기준으로 가져온다.
 *
 * 	즉, 1~3 이란 의미는 1번 인덱스부터 3번 인덱스 전, 2번 까지 가져오라는 뜻 (1 <= index < 3), ( 1,2 )
 *
 * 	그러므로 bc 가 출력된다
 *
 * */