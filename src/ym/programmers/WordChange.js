/*
 * 단어 변환 문제
 * input : begin(시작단어), target(마지막 단어), words(바꿀 수 있는 단어 리스트)
 * output : begin 에서 target이 될 때 까지 최소 변환 횟수
 *
 * 조건
 * 1. 한번에 한 글자만 치환 가능 ex) hit -> hot, hit -> dit
 * 2. begin과 target은 서로 다른 문자
 */

/* BFS 방법
 * Graph를 Map으로 관리(vertex, edge[])
 * node 를 Graph<List>로 관리
 * begin node부터 target 노드까지 DFS 탐색...
 *
 * 매 스텝
 * Candidate와
 * targetList로 관리..
 */
function solution(begin, target, words) {
    let answer = 0;

    // 예외처리 추가(words에 target이 없으면 그냥 끝)
    if (words.includes(target) === false) {
        return 0;
    }

    // 배열 첫번째에 begin 추가
    words.unshift(begin);


    // graph[0][1] : words의 0번째 단어에서 1번째 단어로 변환할 수 있는가? 있으면 1, 없으면 0
    // hit -> hot 변환 가능한가?

    // graph 선언
    let graph = [];

    // graph 할당
    for (let i = 0; i < words.length; i++) {
        let node = {};
        let key = words[i];
        let edges = [];
        for (let j = i; j < words.length; j++) {
            if (i === j) {
                continue;
            }
            // 추가
            let candidate = words[j];
            if (isPossibleMove(key, candidate)) {
                edges.push(candidate);
            }
        }
        node.vertex = key;
        node.edges = edges;
        graph.push(node);
    }

    // console.log(graph);


    let visited = new Array(words.length).fill(false);
    answer = BFS(["hit"], target, 0, graph, visited);

    return answer;
}

/*
Map { 'hit' => [ 'hot' ] },
Map { 'hot' => [ 'dot', 'lot' ] },
Map { 'dot' => [ 'dog', 'lot' ] },
Map { 'dog' => [ 'log', 'cog' ] },
Map { 'lot' => [ 'log' ] },
Map { 'log' => [ 'cog' ] },
Map { 'cog' => [] }
*/
function BFS(candiArr, target, counter, graph, visited) {
    if (counter >= graph.length) return 0;
    let tempCandiArr = new Set();
    for (let i = 0; i < candiArr.length; i++) {
        let candidate = candiArr[i];

        for (let j = 0; j < graph.length; j++) {
            let node = graph[j];
            if (visited[j]) continue;
            if (candidate === node.vertex) { // 시작점이라면??
                visited[j] = true;
                for (let k = 0; k < node.edges.length; k++) {
                    let tempTarget = node.edges[k];
                    if (tempTarget === target) {
                        counter++;
                        return counter;
                    } else {
                        tempCandiArr.add(tempTarget);
                    }
                }
            }
        }
    }
    tempCandiArr = Array.from(tempCandiArr);
    counter++;
    return BFS(tempCandiArr, target, counter, graph, visited);
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

// console.log(solution("hit", "cog", ["hot", "dot", "dog", "lot", "log", "cog"])); // 4
// console.log(solution("hit", "cog", ["hot", "dot", "dog", "lot", "log"])); // 0
// console.log(solution("hit", "cog", ["hot", "dot", "hog", "lot", "cog"])); // 3