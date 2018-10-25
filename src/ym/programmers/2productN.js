/**
 * 2 * n 타일링
 *
 * @param n : 완성된 타일의 가로의 길이
 * @returns {number} : 타일을 이어 붙여 채울 수 있는 경우의 수
 *
 *
 * 가로의 길이가 2이고 세로의 길이가 1인 직사각형 모양의 타일이 있다.
 * 이 직사각형 타일을 이용하여 세로의 길이가 2이고 가로의 길이가 n인 바닥을 가득 채우려고 한다.
 *
 * - 타일을 가로로 배치 하는 경우
 * - 타일을 세로로 배치 하는 경우
 *
 * 직사각형의 가로의 길이 n이 매개변수로 주어질 때, 이 직사각형을 채우는 방법의 수를
 * return 하는 solution 함수를 작성하세요.
 */
function solution(n) {
    let answer;
    let arr = [];
    arr[0] = 1;
    arr[1] = 2;

    for (let i = 2; i < n; i++) {
        arr[i] = (arr[i - 1] + arr[i - 2]) % 1000000007;
    }
    answer = arr[n - 1];
    return answer;
}


console.log(solution(4)); // 5
console.log(solution(5)); // 13
console.log(solution(53)); // 13