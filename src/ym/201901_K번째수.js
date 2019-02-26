let sortFn = function (a, b) {
    if (a > b) return 1;
    else if (a < b) return -1;
    else return 0;
};

function solution(array, commands) {
    var answer = [];
    for (let i = 0; i < commands.length; i++) {

        let start = commands[i][0] - 1;
        let end = commands[i][1];
        let th = commands[i][2] - 1;

        let tempArr = array.slice(start, end);

        tempArr.sort(sortFn);

        answer.push(tempArr[th]);
    }
    return answer;
}