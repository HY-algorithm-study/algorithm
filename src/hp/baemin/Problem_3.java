package baemin;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by hong2 on 30/03/2019
 * Time : 5:11 PM
 */

public class Problem_3 {
    final static int TAKE_ELEVATOR = 0;
    public static void main(String[] args) {

        Problem_3 problem_3 = new Problem_3();
        int[] A = {40, 40, 100, 80, 20};
        int[] B = {3, 3, 2, 2, 3};
        int M = 3;
        int X = 5;
        int Y = 200;
        System.out.println(problem_3.solution(A, B, M, X, Y));
    }
    public int solution(int[] A, int[] B, int M, int X, int Y) {
        List<People> waitingList = getWaitingList(A, B);
        Elevator elevator = new Elevator(X, M, Y);

        while (waitingList.size() != 0) {
            if (elevator.canEnter(waitingList.get(TAKE_ELEVATOR).getWeight())) {
                elevator.setPeople(waitingList.get(TAKE_ELEVATOR));
                waitingList.remove(TAKE_ELEVATOR);
            } else {
                elevator.setStopCount();
                elevator.initWaitingList();
            }
        }

        elevator.setStopCount();
        elevator.initWaitingList();
        return (int)elevator.getStopCount();
    }

    private List<People> getWaitingList(int[] A, int[] B) {
        List<People> waitngList = new ArrayList<>();
        for (int i = 0; i<A.length; i++) {
            waitngList.add(new People(A[i], B[i]));
        }
        return waitngList;
    }

    public class Elevator {
        private int X;
        private int M;
        private int Y;
        private long stopCount = 0;
        private List<People> peopleList = new ArrayList<>();

        public Elevator(int x, int m, int y) {
            X = x;
            M = m;
            Y = y;
        }

        public long getStopCount() {
            return stopCount;
        }

        public void initWaitingList() {
            this.peopleList.clear();
        }


        public void setStopCount() {
            this.stopCount =
                    this.stopCount // stop 되었던 수
                    + getStopFloorCount() // 올라가면서 멈춘수
                    + 1; // 1층으로 돌아오면서 멈춘수
        }

        private long getStopFloorCount() {
            if (this.peopleList.size() == 0) {
                return 0;
            }
            return this.peopleList.stream().map(People::getTarget).distinct().count();
        }

        private int getPeopleTotalWeight() {
            if (this.peopleList.size() == 0) {
                return 0;
            }
            return this.peopleList.stream().map(People::getWeight).reduce((e1, e2) -> e1 + e2).get();
        }

        public void setPeople(People people) {
           this.peopleList.add(people);
        }

        private boolean canEnter(int weight) {
            return (Y > (getPeopleTotalWeight() + weight)) && X > this.peopleList.size();
        }
    }
    public class People {
        private int weight;
        private int target;

        public People(int weight, int target) {
            this.weight = weight;
            this.target = target;
        }

        public int getWeight() {
            return weight;
        }

        public int getTarget() {
            return target;
        }
    }
    //https://seolhun.github.io/Java-Elevators/
}
