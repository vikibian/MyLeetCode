package com.neu.leetcode.problems;

public class 正则表达式匹配_0010 {
    public static void main(String[] args) {
        //false
        String s1 = "aa";
        String p1 = "a";

        //true
        String s2 = "aa";
        String p2 = "a*";

        //true
        String s3 = "ab";
        String p3 = ".*";

        //true
        String s4 = "aab";
        String p4 = "c*a*b";

        //false
        String s5 = "mississippi";
        String p5 = "mis*is*p*.";

        //true
        String s6 = "mississippi" ;
        String p6 = "mis*is*ip*.";

        //false
        String s7 = "ab";
        String p7 = ".*c";

        //true
        String s8 = "aaa";
        String p8 = "ab*a*c*a";

        System.out.println("false:"+isMatch3(s1,p1));
        System.out.println("true:"+isMatch3(s2,p2));
        System.out.println("true:"+isMatch3(s3,p3));
        System.out.println("true:"+isMatch3(s4,p4));
        System.out.println("false:"+isMatch3(s5,p5));
        System.out.println("true:"+isMatch3(s6,p6));
        System.out.println("false:"+isMatch3(s7,p7));
        System.out.println("true:"+isMatch3(s8,p8));

//        String[] split = p2.split("\\*");
//
//        System.out.println(split.length);
//        System.out.println(split[0]);

    }

    public static boolean isMatch(String s, String p) {

        //不包含* 则进行长度判断 长度不等则一定是false
        if (!p.contains("*")){
            return p.length() == s.length() ? true : false;
        }



        String[] split = p.split("\\*");

        int head = 0;
        int tail = 0;
//        int index  = 0;
        String target = "";
//        String targetPre = "";
//        String targetSuffix = "";

        for (int i=0;i<split.length;i++){
            target=split[i];
//            targetSuffix = split[i].substring(split[i].length()-1,split[i].length());
//            targetPre = split[i].substring(0,split[i].length()-1);

            int l = 0;
            while (l<target.length()){
                if (l != target.length() -1){
                    if (target.charAt(l) == s.charAt(head)){
                        l++;
                        head++;
                    }else {
                        return false;
                    }
                }else {


                    if (head<s.length() && (target.charAt(l) == s.charAt(head) || target.charAt(l) == '.' )){
                        //判断是否是最后一个数组
                        if (i == split.length-1 && p.charAt(p.length()-1) != '*'){
                            //最后不是*则l需要加一 否则不加
                            head++;
                            l++;
                        }
                        else {
                            head++;
                        }

                    }
                    else if (head == s.length() && p.charAt(p.length()-1) != '*' && p.charAt(p.length()-1) != '.' && p.charAt(p.length()-1) != s.charAt(head-1) ){
                        return false;
                    }
                    else {
                        //break while
                        l++;
                    }


                }
            }
//            tail = tail + split[i].length() ;

        }
//        tail = tail + split.length-1;

//        while (tail < p.length()){
//
//            if (chars[tail] == '*'){
//                target = p.substring(head,tail+1);
//
//                if (target.equals(".")){
//                    //target可以代表任意字符
//                    target = p.substring(head,tail+1);
//
//                    head = tail + 1;
//                }else {
//
//                }
//
//                int index = 0;
//                int count = 0;
//
//                head = tail+1;
//            }
//            tail++;
//        }

//        for (int i=0;i<split.length;i++){
//            target=split[i];
//            targetSuffix = split[i].substring(split[i].length()-1,split[i].length());
//            targetPre = split[i].substring(0,split[i].length()-1);
//            while (head<s.length()){
//                String temp = "";
//                if (head + target.length()-1 >=s.length()){
//                    tail = s.length();
//                    temp = s.substring(head,tail);
//                } else {
//                   temp = s.substring(head,head+target.length()-1);
//                }
//
//                if (i == 0){
//                    //当第一次循环判断时 如果有相等的string 则记录匹配的位置
//                   if (temp.equals(targetPre) && index == 0){
//                       index = target.length();
//                   } else {
//                       break;
//                   }
//                } else if (i == split.length-1){
//                    if (target!= "."){
//                        if (!temp.equals(target)){
//                            return false;
//                        }
//                    }
//                } else {
//                    if (index!=0){
//                        //index 不为0 则表示 前面的spilt中的string匹配过
//                        if (!temp.equals(target)){
//                            return false;
//                        }
//                    }
//                }
//                head = head + target.length();
//            }
//        }

//        for (int i=0;i<split.length;i++){
//            target=split[i];
//            targetSuffix = split[i].substring(split[i].length()-1,split[i].length());
//            targetPre = split[i].substring(0,split[i].length()-1);
//            while (head<s.length()){
//                String temp = "";
//            }
//        }

//        int index1 = 0;
//        int index2 = 0;
//        int index3 = 0;
//
//        while (index2 < p.length()){
//
//        }

        if (head == s.length() ){
            return true;
        }else {
            return false;
        }
    }

    public static boolean isMatch2(String s, String p) {
        int m = s.length();
        int n = p.length();
        boolean[][] f = new boolean[m+1][n+1];

        f[0][0] = true;
        for (int i=0;i<=m;i++){
            for (int j =1;j<=n;j++){
                if (p.charAt(j-1) == '*'){
                    f[i][j] = f[i][j-2];
                    if (matches(s,p,i,j-1)){
                        f[i][j] = f[i][j] || f[i-1][j];
                    }
                }else {
                    if (matches(s,p,i,j)){
                        f[i][j] = f[i-1][j-1];
                    }
                }
            }
        }



        return f[m][n];
    }

    private static boolean matches(String s, String p, int i, int j) {
        if (i == 0){
            return false;
        }

        if (p.charAt(j-1) == '.'){
            return true;
        }

        return s.charAt(i-1) == p.charAt(j-1);

    }

    public static boolean isMatch3(String s,String p){
        if (p.isEmpty()){
            return s.isEmpty();
        }
        boolean first = !s.isEmpty() && (p.charAt(0)==s.charAt(0) || p.charAt(0) =='.');

        if (p.length() >=2 && p.charAt(1) == '*'){
            return isMatch3(s,p.substring(2) ) || (first && isMatch3(s.substring(1),p));
        }else {
            return first && isMatch3(s.substring(1),p.substring(1));
        }
    }
}
