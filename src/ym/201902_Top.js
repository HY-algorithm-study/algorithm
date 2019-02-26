function solution(heights) {
    let answer = [];
    let towerStack = heights;
    let tempStack = [];
    let candidate;

    while (towerStack.length !== 1) {
        let source = towerStack.pop();
        while (towerStack.length !== 0) {
            candidate = towerStack[towerStack.length - 1];
            if (candidate > source) {
                // 수신 가능
                answer.push(towerStack.length);
                while (tempStack.length !== 0) {
                    towerStack.push(tempStack.pop());
                }
                break;
            } else if (towerStack.length === 1) {
                answer.push(0);
                while (tempStack.length !== 0) {
                    towerStack.push(tempStack.pop());
                }
                break;
            } else {
                tempStack.push(towerStack.pop());
            }
        }
    }
    answer.push(0);
    return answer.reverse();
}

console.log(solution([6, 9, 5, 7, 4]));
console.log(solution([3, 9, 9, 3, 5, 7, 2]));