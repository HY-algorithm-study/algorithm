function solution(arr) {
  let maxSum = arr[0];
  let currentSum = arr[0];
  arr.forEach((element, idx) => {
    if (idx !== 0) {
      currentSum = Math.max(currentSum + element, element);
      maxSum = Math.max(currentSum, maxSum);
    }
  });
  return maxSum;
}

console.log(solution([-1, 3, -1, 5])); // 7 // 3 + (-1) + 5
console.log(solution([-5, -3, -1])); // -1 // -1
console.log(solution([2, 4, -2, -3, 8])); // 9 // 2 + 4 + (-2) + (-3) + 8
