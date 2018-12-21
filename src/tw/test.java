class test {
    public static int resCnt = 0;
    public static int solution(String begin, String target, String[] words) {
        int answer = 0;

        checkWord(begin, target, words, 0);
        answer = resCnt;
        return answer;
    }

    public static void checkWord(String begin, String target, String[] words, int index) {
        int cnt = 0;
        //시작 값과 타겟이 일치할 경우 리턴
        if (begin.equals(target)) {
            return;

        } else {
            //시작 값과 타겟 값이 일치 하지 않을 때 단어 하나만 다른 배열로 이동
            for (int j = 0; j < words[index].length(); j++) {
                //chr[j] = words[i].charAt(j);

                //hit, hot 비교할 때 h = h, t = t 같은 경우
                if (begin.charAt(j) == words[index].charAt(j)) {
                    cnt++;
                    //System.out.println(begin + "시작 , " + cnt + " cnt");
                }

                //System.out.print(chr[j] + "");
            }

            //단어 하나만 다른 경우 이동 가능
            if (cnt == words[index].length() - 1) {
                begin = words[index];
                //System.out.println(begin + " @@");

                if (begin.equals(target)) {

                    //System.out.println("종료 : " + resCnt);
                    return;
                } else {
                    resCnt++;
                    checkWord(begin, target, words, index + 1);
                }
            }
            else {  //단어 2개이상으로 다를 경우 다시 함수 호출
                checkWord(begin, target, words, index + 1);
                //continue;
            }
        }
    }

    public static void main(String[] args) {
        String[] words = {"hot", "dot", "dog", "lot", "log", "cog"};
        String begin = "hit";
        String target = "cog";
        int result = solution(begin, target, words);

        System.out.println(resCnt);
    }
}