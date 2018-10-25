package programmers;

/**
 * Created by hong2 on 2018. 10. 20.
 * Time : PM 3:20
 */

public class Network_43162 {

    public static void main(String[] args) {
        int[][] computers = {{1,1,0,0,0},
                             {1,1,1,0,1},
                             {0,1,1,1,0},
                             {0,0,1,1,1},
                             {0,0,0,1,1}};

        int[][] computers2 = {
                {1,1,0,1},
                {1,1,1,0},
                {0,1,1,0},
                {1,0,0,1}};

        Network_43162 network_43162 = new Network_43162();
        System.out.println(network_43162.solution(4, computers2));
    }

    public int solution(int n, int[][] computers) {
        int answer = 0;

        for (int i = 0; i < n; i++) {
            if (computers[i][i] == 1 && findNode(i, n, computers)) {
                answer++;
            }
        }

        return answer;
    }

    private boolean findNode(int startPoint, int endPoint, int[][] computers) {
        computers[startPoint][startPoint] = 0;
        for (int i = startPoint + 1; i < endPoint; i++) {
            if (computers[startPoint][i] == 1) {
                computers[startPoint][i] = 0;
                findNode(i, endPoint, computers);
            }
        }
        return true;
    }
}

