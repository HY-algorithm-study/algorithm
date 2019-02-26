function solution(routes) {
    let answer = 0;
    let cam = [];
    cam.length = routes.length;
    let camPosition = 0;

    cam.fill(false);

    routes.sort(function (a, b) { // 나가는 지점 기준 오름차순 정렬
        if (a[0] < b[0]) {
            return 1;
        } else if (a[0] > b[0]) {
            return -1;
        } else {
            return 0;
        }
    });


    for (let i = 0; i < routes.length; i++) {
        if(cam[i] === false) { // 진입점 기준으로 커버 안되면
            camPosition = routes[i][0]; // 카메라 댓수를 늘린다.
            answer++;
        }
        for (let j = i+1; j < routes.length; j++) { // 진입점 기준으로 커버할 수 있는 곳인지 확인
            if(cam[j] === false && routes[j][1] >= camPosition) {
                cam[j] = true;
            }
            break;
        }
    }

    return answer;
    // return routes;
}

console.log(solution([[-20, 15], [-14, -5], [-18, -13], [-5, -3]]));