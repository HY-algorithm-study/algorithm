function solution(N) {
  let answer = 0;
  let fibonacci = [];
  fibonacci[0] = 0;
  fibonacci[1] = 1;
  fibonacci[2] = 2;

  for (let i = 3; i < N; i++) {
    fibonacci[i] = fibonacci[i - 1] + fibonacci[i - 2];
  }
  fibonacci.forEach(element => {
    if((element % 2) === 0 && (answer + element < N) ) {
      answer += element;
    }
  });
  return answer;
}

console.log(solution(12)); // 10 // 0, 1, 2, 3, 5, 8 중 짝수인 2 + 8 = 10.
