function solution(strs, t) {
    let strSet = new Set(strs);
    let INF = Number.POSITIVE_INFINITY;
    let dp = [];
    let len = t.length;

    for (let i = 0; i < len; i++) {
        dp[i] = INF;
    }

    dp[len] = 0;

    for (let i = len - 1; i >= 0; --i) {
        let tmp = "";
        for (let j = 0; i + j < len && j < 5; ++j) {
            tmp += t.charAt(i + j);
            if (strSet.has(tmp) && dp[i + j + 1] !== INF) {
                dp[i] = Math.min(dp[i], dp[i + j + 1] + 1);
            }
        }
    }

    if (dp[0] === INF) {
        return -1;
    }

    return dp[0];
}


console.log(solution(["ba", "na", "n", "a"], "banana")); // 3