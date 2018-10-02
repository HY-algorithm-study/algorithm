function solution(n) {
    var answer = "";
    var jarisu = [];
    var answerIndex = 0;
    var jisuThird = [];
    var jisuThirdSum = [0];
    var i = 0;

    // 3의 제곱수를 저장하는 배열
    jisuThird[0] = Math.pow(3, 0);
    // 3의 제곱수를 더한 값 
    // jusiThirdSum[3] = 3^0 + ... +3^3
    jisuThirdSum[0] = jisuThird[0];
    while (n >= jisuThirdSum[i]) {
        i++;
        jisuThird[i] = Math.pow(3, i);
        jisuThirdSum[i] = jisuThirdSum[i - 1] + jisuThird[i];
    }


    while (n >= 4) {
        jarisu[answerIndex] = parseInt(
            (n - jisuThirdSum[i - 2]) / jisuThird[i - 1]
        );
        n = n - jarisu[answerIndex] * jisuThird[i - 1];
        i--;
        answerIndex++;
    }
    jarisu[answerIndex] = n % 3;

    jarisu.forEach(function(element) {
        if (element === 0 || element === 3) {
            element = "4";
        }
        answer += element;
    });
    return answer;
}

// console.log(solution(3));
// console.log(solution(1));
// console.log(solution(2));
console.log(solution(64));
console.log(solution(9));

// 3 진법 --> 0, 1, 2
// 124 숫자 --> 4, 1, 2