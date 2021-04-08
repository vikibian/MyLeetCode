package com.neu.leetcode.problems;

import java.math.BigDecimal;
import java.util.*;

public class test {
    public static void main(String[] args) {
        Map<Integer,String> map1 = new LinkedHashMap<>();
        Map<String, String> map = new HashMap<String, String>();
        map.put("1", "value1");
        map.put("2", "value2");
        map.put("3", "value3");
        map.containsKey("1");

//        //第一种：普遍使用，二次取值
//        System.out.println("通过Map.keySet遍历key和value：");
//        for (String key : map.keySet()) {
//            System.out.println("key= "+ key + " and value= " + map.get(key));
//        }
//
//        //第二种
//        System.out.println("通过Map.entrySet使用iterator遍历key和value：");
//        Iterator<Map.Entry<String, String>> it = map.entrySet().iterator();
//        while (it.hasNext()) {
//            Map.Entry<String, String> entry = it.next();
//            System.out.println("key= " + entry.getKey() + " and value= " + entry.getValue());
//        }
//
//        //第三种：推荐，尤其是容量大时
//        System.out.println("通过Map.entrySet遍历key和value");
//        for (Map.Entry<String, String> entry : map.entrySet()) {
//            System.out.println("key= " + entry.getKey() + " and value= " + entry.getValue());
//        }
//
//        //第四种
//        System.out.println("通过Map.values()遍历所有的value，但不能遍历key");
//        for (String v : map.values()) {
//            System.out.println("value= " + v);
//        }

        String str = "AB";
        System.out.println(str.substring(0,2));
        Deque<Integer> deque = new LinkedList<>();
        System.out.println(deque.contains(1));

        String str1 = " ";
        String[] s = str1.split(" ");
        System.out.println(s.length);
        System.out.println(Integer.MIN_VALUE>= -2147483648);
        System.out.println(0 > -2147483648);
        System.out.println((int)-2147483648);
        System.out.println(Integer.MAX_VALUE);

        String a = "a good  example";
        System.out.println(a);
        String[] s1 = a.split(" ");
        System.out.println(s1[2]);
        System.out.println(s1[3]);

        List<Integer> list = new LinkedList<>();
        StringBuffer stringBuffer = new StringBuffer();
        stringBuffer.indexOf(String.valueOf(str.charAt(0)));
        List<Integer> list1 = new ArrayList<>();
        list.add(11);
        list.add(1);
        list.add(2);
        list.add(3);

        Collections.sort(list);
        System.out.println(list.toString());
        list.remove(0);
        System.out.println(list.toString());

        int x = -2147483648;
        System.out.println(x>=0);
        System.out.println(Math.abs(x));

        System.out.println(675/26);
        System.out.println(675%26);
        System.out.println((2147483647%26));
        System.out.println((2147483647-23)%26);
//        System.out.println((1+'A'));
        String test = "ABC";
        System.out.println(test.substring(2,3));

        String num1 = "01",num2 = "001";
        System.out.println("string转int");
        System.out.println(Integer.valueOf(num1));
        System.out.println(Integer.valueOf(num2));
        String string = "0.1";
        String[] split = string.split("\\.");
        System.out.println(split.length);
        System.out.println("位运算");
        int n = 00000000000000000000000000001011;
        int num = 0;
        System.out.println(n&2);

        System.out.println("int 转 char");
        System.out.println((char)1);

        System.out.println("-------");
        System.out.println((31>>4) & (1));

        BigDecimal bigDecimalA = new BigDecimal(1);
        BigDecimal bigDecimalB = new BigDecimal(3);
        System.out.println("BigDecimal");
//        System.out.println(bigDecimalA.divide(bigDecimalB));
//        System.out.println(String.valueOf(1/3));
        double ans = 0;
        ans = 4/333.0;
        System.out.println("ans:"+ans);
        System.out.println(String.valueOf(ans));
        System.out.println(String.valueOf(ans).split("\\.").length);
        System.out.println(Arrays.toString(String.valueOf(ans).split("\\.")));

        System.out.println("测试平方");
        System.out.println(Math.sqrt(6));
        System.out.println(12%9);
        System.out.println(Math.sqrt(16));

        System.out.println("查看最小值");
        System.out.println(Integer.MIN_VALUE < -(int)Math.pow(2,31));
        System.out.println(Long.MIN_VALUE);
        int a1 = 0;
        long b1 = 0;
        System.out.println(a1<b1);

    }
}
