function solution(numbers, target) {
    let answer = 0;

    if (target < numbers.length) {
        numbers[target] *= 1;
        solution()
    }
    return answer;
}


console.log(solution())