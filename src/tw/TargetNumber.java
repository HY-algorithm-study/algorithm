/**
 * 주어진 number의 배열 숫자를 통해 +,- 를 활용하여 타겟과 일치하게 한다.
 * 타겟 넘버 문제
 * DATE : 18.10.28
 * AUTHOR : KTW
 *
 *
 * 1. 배열 사이즈 만큼 배치 시킨다.(+, - 연산, 재귀함수 사용)
 * 2. 배치된 배열을 바탕으로 합을 구한다.
 * 3. 구한 합이 타겟과 일치할 경우 카운트+1 을 하여 최종 갯수를 구한다.
 */
public class TargetNumber {
    static int resInt = 0;      //갯수
    static int insTarget = 3;   //타겟

    public static int solution(int[] numbers, int target) {
        int answer = 1;
        recursive(numbers, answer, 0, numbers.length);
        return resInt;
    }

    /**
     * 재귀함수; +1과 -1 일때의 값으로 배열 크기까지 반복 후 sum 메소드 호출
     * @param numbers
     * @param insData
     * @param start
     * @param end
     */
    public static void recursive(int[] numbers, int insData, int start, int end){
        if(start < end){
            numbers[start] *= insData;

            recursive(numbers, insData, start+1, numbers.length);

            numbers[start] *= insData * (-1);
            recursive(numbers, insData, start+1, numbers.length);
        }else{
            sum(numbers);
        }

    }


    /**
     * 타겟 값과 배열의 합이 일치할 때 카운트
     * @param numbers
     */
    public static void sum(int[] numbers){
        //ArrayList<Intger> list =
        int sum = 0;
        for(int i = 0 ; i < numbers.length ; i++){
            sum+=numbers[i];
           // System.out.print(sum + ", ");
        }

        print(numbers);
        //System.out.println();
        if(sum == insTarget){
//            for(int i = 0 ; i < numbers.length ; i++){
//                visitList.add(numbers[i]);
//            }
            resInt++;

        }
    }

    public static void print(int[] num) {
        for (int i =0; i<num.length; i++) {
            System.out.print(num[i]);
        }
        System.out.println();
    }

    public static void main(String[] args){
        int[] data ={1,1};

        if(data.length < 2 || data.length > 20) return;

        if(insTarget < 1 || insTarget > 1000) return;

        int returns = solution(data, insTarget);

        System.out.println(returns);
    }
}