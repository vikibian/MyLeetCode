package com.neu.leetcode.problems.design;

import java.util.LinkedList;
import java.util.List;

public class 设计停车系统_1603 {
    class ParkingSystem {
        private int bigSize;
        private int midSize;
        private int smallSize;
        private List<Integer> bigList;
        private List<Integer> midList;
        private List<Integer> smallList;

        public ParkingSystem(int big, int medium, int small) {
            this.bigSize = big;
            this.midSize = medium;
            this.smallSize = small;

            bigList = new LinkedList<>();
            midList = new LinkedList<>();
            smallList = new LinkedList<>();
        }

        public boolean addCar(int carType) {
            switch (carType){
                case 1:
                    if (bigList.size() >= bigSize){
                        return false;
                    } else {
                        bigList.add(carType);
                        return true;
                    }
                case 2:
                    if (midList.size() >= midSize){
                        return false;
                    } else {
                        midList.add(carType);
                        return true;
                    }
                case 3:
                    if (smallList.size() >= smallSize){
                        return false;
                    } else {
                        smallList.add(carType);
                        return true;
                    }
            }
            return false;
        }
    }
}
