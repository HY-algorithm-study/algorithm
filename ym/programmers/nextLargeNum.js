function solution(n) {
    var answer = 0;
    var binary = n.toString(2);
    var oneCount = 0;
    var moreBinary = 0;
    var tempOneCount = 0;
    var index = 1;

    // 주어진 n 2진수 변환해서 1의 갯수 파악하기
    for (let i = 0; i < binary.length; i++) {
        const element = binary.charAt(i);
        if (element === "1") {
            oneCount++;
        }
    }

    // n + i 2진수 변환 후 1의 갯수 파악 (1의 갯수가 같을 때 까지)
    while (true) {
        tempOneCount = 0;
        moreBinary = (n + index).toString(2);
        for (let i = 0; i < moreBinary.length; i++) {
            const element = moreBinary.charAt(i);
            if (element === "1") {
                tempOneCount++;
            }
        }
        if (tempOneCount === oneCount) {
            answer = n + index;
            break;
        }
        index++;
    }
    return answer;
}

// 문제 정리
// 2진수 변환 후 1의 갯수가 같은 다음 숫자 중 가장 작은 숫자 찾기

// 1. n 2진수 변환
// 2. 2진수 변환한 숫자의 1의 갯수 파악

// 3. n + (index++) 추가하면서 2진수변환
// 4. 1의 갯수 같은지 파악

// 5. 같지 않으면 3~4번 반복

// bitCount(Integer 내장 메소드)
// findNumber(재귀함수 방식, --> 2진수 변환 하면서 1의 갯수 count 반환)
