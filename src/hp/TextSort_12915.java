package programmers;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class TextSort_12915 {
	public static void main(String[] args) {
		String[] str = {"abce", "abcd", "cdx"};
		str = solution(str, 2);

		for(String string : str) {
			System.out.println(string);
		}
	}

	public static String[] solution(String[] strings, int n) {
		List<String> stringList = new ArrayList<>();
		stringList.addAll(Arrays.asList(strings));

		stringList =  stringList.stream()
			.sorted((String a, String b) -> stringComparing(a, b, n))
			.collect(Collectors.toList());

		return stringList.toArray(new String[stringList.size()]);
	}

	public static int stringComparing(String first, String second, int n) {
		char a = first.charAt(n);
		char b = second.charAt(n);

		if ( a-b == 0 ) {
			return first.compareTo(second);
		}

		return a-b;
	}
}

/**
 *
 * Stream 을 이용해서 custom Comparing 을 만들었습니다.
 * custom comparing 을 만들으므로, 나중에 정렬 기준이 바뀌더라도 내부로직은 수정하지 않고 string comparing 만 수정하면 됩니다
 *
 * stringComparing 의 기본 핵심은 n 번째 문자를 빼와서 a-b 를 하여 정렬을 하는것입니다.
 * 문제에서 해당 기준의 문자가 같으면 기존의 사전순 정렬을 이용하라고 했으므로
 *
 * a-b 의 값이 0 일 경우에 compareTo 를 이용하여 사전 정렬을 하도록 합니다
 * */
