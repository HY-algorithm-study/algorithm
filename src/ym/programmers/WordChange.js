/*
 * 단어변환
 * input : begin, target, words
 * output : begin --> target 최소 단어 변환 횟수
 */
function solution(begin, target, words) {
    let answer;

    // 예외처리 추가(words에 target이 없으면 그냥 끝)
    if (words.includes(target) === false) {
        return 0;
    }

    // 배열 첫번째에 begin 추가
    words.unshift(begin);


    // graph[0][1] : words의 0번째 단어에서 1번째 단어로 변환할 수 있는가? 있으면 1, 없으면 0
    // hit -> hot 변환 가능한가?

    // graph 선언
    let graph = new Array(words.length);
    for (let i = 0; i < graph.length; i++) {
        graph[i] = new Array(words.length).fill(0);
    }

    // graph 할당
    for (let i = 0; i < words.length; i++) {
        for (let j = 0; j < words.length; j++) {
            if (i === j) {
                graph[i][j] = 1;
                continue;
            }
            if (isPossibleMove(words[i], words[j])) {
                graph[i][j] = 1;
                graph[j][i] = 1;
            }
        }
    }

    // graph dfs 탐색
    // start index는 0으로 고정
    let distance = new Array(graph.length).fill(Number.MAX_SAFE_INTEGER);

    // dfs
    DFS(0, target, words, graph, distance, 0);

    // arr중 최소값 반환
    answer = Math.min.apply(null, distance);

    // 만약 도달할 수 없는 경로라면...
    if (answer === Number.MAX_SAFE_INTEGER) {
        // words = ['cog' , 'dot'] target = cog, begin = hit
        answer = 0;
    }

    return answer;
}

// index에서 시작해서 target 까지 가는데 걸리는 최소경로
function DFS(idx, target, words, graph, distance, count) {
    if (idx > graph.length - 1) {
        return Number.MAX_SAFE_INTEGER;
    }
    if (words[idx] === target) {
        distance[idx] = distance[idx] > count ?  count : distance[idx];
        return;
    }
    for (let i = idx + 1; i < graph[idx].length; i++) {
        if (graph[idx][i] === 1) {
            // 갈 수 있는 경로
            let tempCount = count + 1;
            DFS(i, target, words, graph, distance, tempCount);
        }
    }
    return;
}

function isPossibleMove(source, target) {
    let rtnFlag = false;
    let count = 0;
    for (let i = 0; i < source.length; i++) {
        if (count > 1) {
            return rtnFlag;
        }
        if (source.charAt(i) !== target.charAt(i)) {
            count++;
        }
    }
    if (count === 1) {
        rtnFlag = true;
    }
    return rtnFlag;
}

console.log(solution("hit", "cog", ["hot", "dot", "dog", "lot", "log", "cog"])); // 4
console.log(solution("hit", "cog", ["hot", "dot", "dog", "lot", "log"])); // 0
console.log(solution("hit", "cog", ["hot", "dot", "hog", "lot", "cog"])); // 3