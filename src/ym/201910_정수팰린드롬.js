function solution(N) {
  if(N < 0) {
    return false;
  }
  let answer = true;
  let mok = Number.POSITIVE_INFINITY;
  let nmg = 0;
  let jariArr = [];
  while(mok !== 0) {
    nmg = N % 10;
    mok = parseInt(N / 10);
    N = mok;
    jariArr.push(nmg);
  }
  for (let i = 0; i < jariArr.length / 2; i++) {
    if(jariArr[i] !== jariArr[jariArr.length - 1 -i]) {
      answer = false;
    }
  }
  return answer;
}

console.log(solution(12345)); // False
console.log(solution(-101)); // False
console.log(solution(11111)); // True
console.log(solution(12421)); // True
