package com.neu.leetcode.problems.array;

import java.util.*;

public class 合并区间_0056 {

    public static void main(String[] args) {
//        int[][] nums= new int[][]{{1,4},{4,5}};
//        int[][] nums= new int[][]{{1,3}};
//        int[][] nums= new int[][]{{1,4},{0,1}};
        int[][] nums= new int[][]{{1,4},{0,0}};
        System.out.println((merge(nums)).length);
        System.out.println((merge(nums))[0][0]);
        System.out.println((merge(nums))[0][1]);
    }

//    public static int[][] merge(int[][] intervals) {
//        if ( intervals == null){
//            return intervals;
//        }
//
//        int num = intervals.length,arrNum = intervals[0].length;
//        List<int[]> list = new LinkedList<>();
//
//        int left1=intervals[0][0],right1=intervals[0][1];
//        int left2=intervals[0][0],right2=intervals[0][1];
//
//        for (int i=1;i<num;i++){
//           left2=intervals[i][0];
//           right2=intervals[i][1];
//
//           if (right1<left2){
//               list.add(new int[]{left1,right1});
//               if (i==num-1){
//                   list.add(new int[]{left2,right2});
//                   continue;
//               }
//           } else if (left1 <= left2 &&left2<right1){
//               right1 = right2;
//               continue;
//           } else if (left1 < left2 &&left2<=right1){
//               right1 = right2;
//               continue;
//           }
//
//           left1 = left2;
//           right1 = right2;
//
//        }
//
//        if (right1>=left2){
//            list.add(new int[]{left1,right2});
//        }else if (left1 == left2 && right1 == right2){
//            list.add(new int[]{left1,right1});
//        }
//
//
//
//
//        int[][] ans = new int[list.size()][arrNum];
//        for (int i=0;i<list.size();i++){
//            ans[i][0] = list.get(i)[0];
//            ans[i][1] = list.get(i)[1];
//        }
//        return ans;
//
//
//    }

//    public static int[][] merge(int[][] intervals) {
//        if ( intervals == null){
//            return intervals;
//        }
//
//        int num = intervals.length,arrNum = intervals[0].length;
//        List<List<Integer>> list = new LinkedList<>();
//
//        for (int i=0;i<num;i++){
//            int left = intervals[i][0];
//            int right = intervals[i][1];
//            List<Integer> listNum = new LinkedList<>();
//            listNum.add(left);
//            listNum.add(right);
//            if (list.size() == 0){
//                list.add(listNum);
//            } else {
//                boolean isInsert = false;
//                for (int j=0;j<list.size();j++){
//                    int left1 = list.get(j).get(0);
//                    int right1 = list.get(j).get(1);
//
//                    if (!(left1 < left && right1 <left)){
//                        list.get(j).set(0,Math.min(left1,left));
//                        list.get(j).set(1,Math.max(right1,right));
//                        isInsert = true;
//                    } else if (!(left1 > left && left1 >right)){
//                        list.get(j).set(0,Math.min(left1,left));
//                        list.get(j).set(1,Math.max(right1,right));
//                        isInsert = true;
//                    }
//                }
//
//                if (!isInsert){
//                    list.add(listNum);
//                }
//            }
//
//
//        }
//
//
//        int[][] ans = new int[list.size()][arrNum];
//        for (int i=0;i<list.size();i++){
//            ans[i][0] = list.get(i).get(0);
//            ans[i][1] = list.get(i).get(1);
//        }
//        return ans;
//
//
//    }

    //官方题解
    public static int[][] merge(int[][] intervals) {
        if ( intervals.length == 0){
            return new int[0][2];
        }

        Arrays.sort(intervals, new Comparator<int[]>() {
            @Override
            public int compare(int[] o1, int[] o2) {
                return o1[0] - o2[0];
            }
        });

        List<int[]> merged = new ArrayList<>();
        for (int i=0;i<intervals.length;i++){
            int L = intervals[i][0],R = intervals[i][1];
            if (merged.size() == 0 || merged.get(merged.size()-1)[0] < L){
                merged.add(new int[]{L,R});
            } else {
                merged.get(merged.size()-1)[1] = Math.max(merged.get(merged.size()-1)[1],R);
            }
        }
        return merged.toArray(new int[merged.size()][]);


    }

}
