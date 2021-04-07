package com.neu.leetcode.problems.hashtable;

import java.util.HashMap;
import java.util.Map;

public class 森林中的兔子_0781 {
    public int numRabbits(int[] answers) {
        if (answers == null || answers.length == 0){
            return 0;
        }
//        Arrays.sort(answers);
//        Map<Integer,Integer> mapNumber = new HashMap<>();
        Map<Integer,Integer> mapCount = new HashMap<>();
        for (int num : answers) {
//            if (!mapNumber.containsKey(num)) {
//                mapNumber.put(num,num);
//            }

            if (!mapCount.containsKey(num)){
                mapCount.put(num,1);
            } else {
                int n = mapCount.get(num);
                mapCount.put(num,n+1);
            }
        }
        int count = 0;
        for (Map.Entry<Integer,Integer> entry : mapCount.entrySet()){
            //answers中为0时，直接计算个数
            if (entry.getKey() == 0){
                count += entry.getValue();
            } else {
                int num1 = entry.getKey()+1;
                int num2 = entry.getValue();
                if (num2<=num1){
//                    System.out.println("<=");
                    count += num1;
                } else {
//                    System.out.println(">");
//                    System.out.println((num2-num1+1));
//                    int rest = num2-num1;
                    int rest = (num2-num1)/num1;
                    int rest1 = (num2-num1)%num1;

//                    count += num1+(num2-num1+1);
                    count += num1*(rest+1) ;
                    if (rest1 != 0){
                        count += (entry.getKey()+1);
                    }
                }


            }
        }
//        System.out.println(mapCount.toString());
//        System.out.println(mapNumber.toString());
        return count;
    }

    //官方题解 贪心
    public int numRabbits1(int[] answer){
        Map<Integer,Integer> map  = new HashMap<>();
        for (int y : answer){
            map.put(y,map.getOrDefault(y,0)+1);
        }
        int ans = 0;
        for (Map.Entry<Integer,Integer> entry : map.entrySet()){
            int y = entry.getKey(),x = entry.getValue();
            //（x+y) / (y+1)  向上取整
            ans+= (x + y) / (y + 1) * (y + 1);
        }

        return ans;
    }

}
