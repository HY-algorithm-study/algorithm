// 1. 다익스트라 알고리즘을 적용
// 2. 알고리즘 실행 결과 중 distance 배열을 순차탐색
// 2-1. 순차탐색하면서 map에 (거리, 갯수)형태로 put, 이 때, 최대거리를 산정
// 3. map에서 가장 큰 거리의 갯수를 반환

function solution(n, edge) {
    var answer = 0;

    return answer;
}

function dijkstra(graph, Source) {
    let dist = [];
    dist[source] = 0; // 소스와 소스 사이의 거리 초기화 --출발지와 출발지의 거리는 당연히 0 --

    let prev = [];
    prev[source] = undefined; // 출발지 이전의 최적 경로 추적은 없으므로 Undefined

    let vertexSet = new Set(); // 노드들의 집합 Q(방문되지 않는 노드들의 집합) 생성 시작

    // foreach vertex v in Graph... // Graph안에 있는 모든 노드들을 초기화
    if ( v !== source) {
        dist[v] = Number.POSITIVE_INFINITY;
        prev[v] = undefined;
        vertexSet.add(v);
    }


    // 여기까지 요약, Graph에 존재하는 모든 노드들을 초기화한 뒤, Q에 추가함.
    while(vertexSet.size !== 0) { // Q 집합이 공집합이 아닐 경우, 루프 안으로!

    }
}

console.log(solution(6, [[3, 6], [4, 3], [3, 2], [1, 3], [1, 2], [2, 4], [5, 2]]));