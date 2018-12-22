/*
 * Network
 */

function solution(n, computers) {
    let answer = 0;
    // 1. 각 node를 나타내는 map 혹은 배열 선언(방문 완료)
    let queue = new Array(n).fill(false);

    queue[0] = true; // 0 번째 방문
    answer++;
    bfs(0, computers, queue);
    // 0번 노드에서 연결된 노드 찾기

    while (queue.includes(false)) {
        for (let i = 1; i < computers.length; i++) {
            if (queue[i] === false) {
                queue[i] = true;
                answer++;
                bfs(i, computers, queue);
            }
        }
    }

    return answer;
}

function bfs(i, computers, queue) {
    if (queue.includes(false) === false) { // 모든 노드를 방문한 경우
        return;
    }
    // 방문 안한 노드가 남아 있을 경우
    for (let j = i + 1; j < computers[i].length; j++) {
        if (computers[i][j] === 1) {
            queue[j] = true;
            bfs(j, computers, queue);
        }
    }
}

// console.log(solution(3, [[1, 1, 0], [1, 1, 0], [0, 0, 1]])); // 2
// console.log(solution(3, [[1, 1, 0], [1, 1, 1], [0, 1, 1]])); // 1
// console.log(solution(4, [[1, 1, 0, 1], [1, 1, 0, 0], [0, 0, 1, 0], [1, 0, 0, 1]])); // 2