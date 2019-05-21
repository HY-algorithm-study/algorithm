function solution(N) {
  const openBrakcket = "(";
  const closeBracket = ")";
  const bracket = "()";
  let sourceBrackets = [];
  let targetBrackets = [];
  sourceBrackets[0] = bracket;
  let answer;

  if (N > 1) {
    for (let k = 1; k < N; k++) {
      for (let i = 0; i < sourceBrackets.length; i++) {
        targetBrackets[i] = openBrakcket + sourceBrackets[i] + closeBracket;
      }
      for (
        let i = sourceBrackets.length, j = 0;
        i < sourceBrackets.length * 2;
        i++, j++
      ) {
        targetBrackets[i] = sourceBrackets[j] + bracket;
      }
      for (
        let i = sourceBrackets.length * 2, j = 0;
        i < sourceBrackets.length * 3;
        i++, j++
      ) {
        targetBrackets[i] = bracket + sourceBrackets[j];
      }
      answer = new Set(targetBrackets.values());
      answer.delete(undefined);
      sourceBrackets = Array.from(answer);
    }
  }
  return Array.from(answer);
}

// console.log(solution(1)); // ["()"]
// console.log(solution(2)); // ["(())", "()()"]
// console.log(solution(3)); // ["((()))", "(()())", "()(())", "(())()", "()()()"]
console.log(solution(5)); 
