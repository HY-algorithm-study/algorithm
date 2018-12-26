package programmers;

public class FineSquare_12905 {
	public static void main(String[] args) {
		FineSquare_12905 fineSquare_12905 = new FineSquare_12905();
		//fineSquare_12905.solution();
		int board[][] = {
			{0,1,1,1},
			{1,1,1,1},
			{1,1,1,1},
			{0,0,1,0}
		};

		int board2[][] = {
			{1,1,1,0},
			{1,1,0,0},
			{1,0,1,0},
		};

		System.out.println(fineSquare_12905.solution(board2));

	}
	public int solution(int [][]board)
	{
		int sum = 0, temp = 0;
		for (int i = 0; i < board.length; i++) {
			for (int j = 0; j < board[i].length ; j++) {
				if (board[i][j] == 1) {
					temp = check(board, i, j, 1);
				}
				if (temp > sum) {
					sum = temp;
				}
			}
		}

		return (sum) * (sum);
	}

	private boolean isSquare(int [][]board, int x, int y, int n) {
		int height = board[x].length;
		int weight = board.length;
		if (x + n > weight - 1 || y + n > height - 1) {
			return false;
		}

		if (board[x][y+n] == 1 && board[x+n][y] == 1 && board[x+n][y+n] == 1) {
			return true;
		}
		return false;
	}

	private int check(int [][]board, int x, int y, int total) {

		if (isSquare(board, x, y, total)) {
			return check(board, x, y, total + 1) ;
		} else {
			return total ;
		}
	}


}
/**
 * 	{0,0,0,0},
 	{0,0,0,0},
 	{0,1,0,0},
 	{0,0,0,0},
 	{0,0,0,0}
 * */



/**
 *  	0 	1	2	3
 *  0	0	1	1	1
 *  1	1	1	1	1
 *  2	1	1	1	1
 *  3	0	0	1	0
 *
 *  풀이 방법 , 시작 지점
 *
 *  [0,0] [0,1] [0,2] [0,3]
 *  [1,0] [1,1] [1,2] [1,3]
 *
 *  즉 시작 지점으로 오른쪽, 아래, 대각선 아래를 탐색을 한다
 *  이들이 모두가 1이라면 현재까지 간거리를 1씩 증가, 그다음에 최종적으로 도달한 거리에 n^2 을 합니다
 *
 *
 *
 *
 * */