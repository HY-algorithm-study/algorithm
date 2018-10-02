function solution(land) {
    var answer = 0;
    var ddangDaLand = [];

    // 첫번째 행 데이터는 필요
    ddangDaLand[0] = land[0];
    // 2차원 배열 초기화
    for (let i = 1; i < land.length; i++) {
        ddangDaLand[i] = [0, 0, 0, 0];
    }

    // 나머지 아래로 가면서 채우기
    for (let i = 1; i < ddangDaLand.length; i++) {
        for (let j = 0; j < ddangDaLand[i].length; j++) {
            let max = Number.MIN_SAFE_INTEGER;
            for (let k = 0; k < 4; k++) {
                // 같은 행은 가져올 수 없음
                if (j === k) {
                    continue;
                }
                // 중에 최댓값
                if (max < land[i][j] + ddangDaLand[i - 1][k]) {
                    max = land[i][j] + ddangDaLand[i - 1][k];
                }
            }
            ddangDaLand[i][j] = max;
        }
    }
    // 마지막 행의 최대수 찾기
    for (let k = 0; k < 4; k++) {
        if (answer < ddangDaLand[land.length - 1][k]) {
            answer = ddangDaLand[land.length - 1][k];
        }
    }
    return answer;
}

var arr = [[1, 2, 3, 5], [5, 6, 7, 8], [4, 3, 2, 1]];

console.log(solution(arr));
