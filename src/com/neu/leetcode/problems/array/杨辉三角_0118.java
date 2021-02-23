package com.neu.leetcode.problems.array;

import java.util.ArrayList;
import java.util.List;

public class 杨辉三角_0118 {
    public List<List<Integer>> generate(int numRows) {
        List<List<Integer>> list = new ArrayList<>();
        if (numRows == 0){
            return list;
        }
        for (int i=1;i<=numRows;i++){
            List<Integer> list1 = new ArrayList<>();
            for (int j=1;j<=i;j++){
                if (j==1 || j==i){
                    list1.add(1);
                    continue;
                }
                list1.add(list.get(i-2).get(j-2)+list.get(i-2).get(j-1));
            }
            list.add(list1);
        }


        return list;

    }
}
