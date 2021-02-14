package com.neu.leetcode.problems.array;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

public class 插入区间_0057 {
//    public int[][] insert(int[][] intervals, int[] newInterval) {
//        List<int[]> list = new ArrayList<>();
//        for (int i=0;i<intervals.length;i++){
//            int L = intervals[i][0],R=intervals[i][1];
//
//            if (list.size() == 0 || list.get(list.size()-1)[1] < L){
//                list.add(new int[]{L,R});
//                if ( i+1 < intervals.length && L < newInterval[0] && intervals[i+1][0] > newInterval[0]){
//                    list.add(new int[]{newInterval[0],newInterval[1]});
//                } else {
//                    list.get(list.size()-1)[1] = Math.max(list.get(list.size()-1)[1],newInterval[1]);
//                }
//            } else {
//                list.get(list.size()-1)[1] = Math.max(list.get(list.size()-1)[1],R);
//            }
//        }
//
//        if (list.size() == intervals.length){
//            if (list.get(list.size()-1)[1] < newInterval[0]){
//                list.add(new int[]{newInterval[0],newInterval[1]});
//            } else {
//                list.get(list.size()-1)[1] = Math.max(list.get(list.size()-1)[1],newInterval[1]);
//            }
//        }
//
//        return list.toArray(new int[list.size()][]);
//    }


    public int[][] insert(int[][] intervals, int[] newInterval) {
        List<int[]> list = new ArrayList<>();
        int len = intervals.length;
        int count=0;

        int[][] nums = new int[intervals.length+1][2];

        for (int i=0;i<len;i++){
            nums[i][0] = intervals[i][0];
            nums[i][1] = intervals[i][1];
        }

        nums[len][0] = newInterval[0];
        nums[len][1] = newInterval[1];
        Arrays.sort(nums, new Comparator<int[]>() {
            @Override
            public int compare(int[] o1, int[] o2) {
                return o1[0] - o2[0];
            }
        });
        for (int i=0;i<len+1;i++){
            int L = nums[i][0],R = nums[i][1];
            if (list.isEmpty() || list.get(list.size()-1)[1] <L){
                list.add(new int[]{L,R});
            } else {
                list.get(list.size()-1)[1] = Math.max(list.get(list.size()-1)[1],R);
            }
        }

        return list.toArray(new int[list.size()][]);
    }

    //官方题解
    public int[][] insert1(int[][] intervals, int[] newInterval) {
        int left = newInterval[0];
        int right = newInterval[1];
        int len = intervals.length;
        boolean isPlaced = false;
        List<int[]> list = new ArrayList<>();
        for (int[] inter:intervals) {
            if (inter[0] > right){
                //在插入区间的右侧且无交集
                if (!isPlaced){
                    list.add(new int[]{left,right});
                    isPlaced = true;
                }
                list.add(inter);
            } else if (left>inter[1]){
                //在插入区间的左侧无交集
                list.add(inter);
            } else {
                //有交集
                left = Math.min(left,inter[0]);
                right = Math.max(right,inter[1]);
            }
        }

        if (!isPlaced){
            list.add(new int[]{left,right});
        }

        return list.toArray(new int[list.size()][]);
    }
}
