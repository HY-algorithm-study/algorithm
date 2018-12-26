/*
 * 2016년 a월 b일을 입력하면 무슨 요일인지 알려주는 function 작성
 * 1월 1일은 금요일
 * 1. a월 b일은 1월 1일로부터 몇 일 지나있는가???
 * 2. a월 b일 - 1월 1일 ==> 일자 구하기(윤년까지 계산)
 * 3. 7로 나누기 --> 나머지로 배열 index로 구한다.
 */

function solution(a, b) {
  var answer = ["FRI", "SAT", "SUN", "MON", "TUE", "WED", "THU"];
  var monthMap = [0, 31, 29, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31];
  var remain = 0;
  var month = a - 1;
  var day = b - 1;
  for (let i = 0; i < month + 1; i++) {
    remain += monthMap[i];
  }
  remain += day;

  return answer[remain % 7];
}

console.log(solution(1, 1));
console.log(solution(5, 24));


// 홍표 풀이 --> 둠스데이 알고리즘
// 둠스데이 --> 어떤 날짜