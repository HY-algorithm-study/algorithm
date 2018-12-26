/*
 * 주어진 문자열의 가운데 글자를 반환(단 길이가 짝수면 가운데 두글자...)
 * s는 1 이상 100이하인 스트링
 * 1. s.length / 2 반환...(단 s.length %2 == 0이면 가운데 2글자 반환...)
 */

function solution(s) {
  var answer = "";
  if (s.length % 2 === 0) {
    answer = s.substring(s.length / 2 - 1, s.length / 2 + 1);
  } else {
    answer = s.charAt(s.length / 2);
  }
  return answer;
}

console.log(solution("qwer"));
console.log(solution("abcde"));
