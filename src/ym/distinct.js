/*
 * 같은 숫자 제거
 */

function solution(arr) {
    // 정답용 배열
  var answer = [];
  // answer 용 index 변수
  var index = 1;
  answer[0] = arr[0];
  for (var i = 1; i < arr.length; i++) {
    if (answer[index - 1] === arr[i]) {
      continue;
    } else {
      answer[index++] = arr[i];
    }
  }
  return answer;
}

console.log(solution([1, 1, 3, 3, 0, 1, 1]));
