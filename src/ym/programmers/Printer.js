/**
 *
 * @param priorities : 인쇄 우선순위, 대기목록(zero - indexed)
 * @param location  : 인쇄 순서를 알고싶은 문서의 대기목록에서 위치(zero - started)
 * @returns {number}  : 인쇄 순서
 *
 * 일반적인 프린터는 인쇄 요청이 들어온 순서대로 인쇄.
 * --> 중요한 문서가 나중에 인쇄될 수 있음
 * 새롭게 개발한 프린터는
 *
 * 1. 인쇄 대기목록 가장 앞에 있는 문서(J)를 대기목록에서 꺼낸다.
 * 2. 나머지 인쇄 대기목록에서 J보다 중요도가 높은 문서가 한 개라도 존재하면 J를
 *  대기목록의 가장 마지막에 넣는다.
 * 3. 그렇지 않으면 J를 인쇄한다.
 *
 * 예)
 *  A, B, C, D가 순서대로 인쇄목록에 있고, 각각 중요도가 2 1 3 2 라면 C D A B 순으로 인쇄
 *
 * 내가 인쇄를 요청한 문서가 몇 번째로 인쇄되는지 알고 싶습니다.
 *
 * --> 우선순위 큐
 */


function solution(priorities, location) {
    let answer = 0;
    let markingArr = new Array(priorities.length).fill(false);
    let endFlag = true;
    markingArr[location] = true;
    while (endFlag) {
        let J = priorities[0];
        for (let j = 1; j < priorities.length; j++) {
            if (priorities[j] > J) {
                let tempDocFlag = markingArr[0]; // 0번째 삭제
                let tempDocNum = priorities[0];
                priorities.splice(0, 1); // 0번째 삭제
                markingArr.splice(0, 1); // 0번째 삭제


                // 맨 뒤에 다시 삽입
                priorities.push(tempDocNum);
                markingArr.push(tempDocFlag);
                break;
            } else if (j === priorities.length - 1) { // 출력(제일 높은게 없음)
                answer++;
                if(markingArr[0] === true) {
                    endFlag = false;
                } else {
                    markingArr.splice(0,1);
                    priorities.splice(0,1);
                }
            }
        }
    }
    return answer;
}


// console.log(solution([2, 1, 3, 2], 2)) // 1
console.log(solution([1, 1, 9, 1, 1, 1], 0)) // 5
