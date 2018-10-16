function solution(strings, n) {

  // sort의 기준을 만들어 줄 수 있음
  return strings.sort((a, b) => {
      // a와 b는 문자열
      // chr1, chr2 는 문자
    var chr1 = a.charAt(n);
    var chr2 = b.charAt(n);

    if (chr1 === chr2) {
      return (a > b) - (a < b);
    } else {
      return (chr1 > chr2) - (chr1 < chr2);
    }
  });

  // 1. strings의 n번째 문자만 모아놓은 배열을 만든다. [ u, e, a]
  // 2. u, e, a를 정렬한다. (이 때, u, e, a의 index도 따로 관리 map처럼)
  // 3. 그럼 2번째 예제 처럼 문자가 같은경우는???....
}

console.log(solution(["sun", "bed", "car"], 1));
console.log(solution(["abce", "abcd", "cdx"], 2));
console.log(solution(["abcf", "abce", "abcd", "cdx"], 2));

// docker 의 장점 환경 그대로 image화 가능하다
// image를 github처럼 remote repository에 올리고 다른 사람과 공유가 가능하다.(dockerhub)
// image를 다시 docker engine 위에서 실행 --> container


jvm만 있으면 .class 파일은 동일하게 실행 가능
docker engine만 있으면 image 파일을 동일하게 container로 실행 가능


// 1. 로컬에서 작성한 코드 (주로 ide를 이용해 작성(intellij 같은...)) << 이 문제면 jenkins의 쉘 스크립트에서 docker image를 딸 필요 없음
// 2. docker 환경에 어떻게 배포???????? -------> jenkins, gradle 같은 오픈소스가 나온 이유

jenkins는 로컬 코드 <==> remote 코드 위한 자동 배포??? 솔루션
--> 쉘 스크립트 구성

- docker container 내부에서 git pull 스크립트 작성

개발 ------------------------------- 운영
local
test
gitlab push
                            (jenkins가 할 일)
                            1. git pull (docker container 환경 내)
                            2. pull 한 코드 build