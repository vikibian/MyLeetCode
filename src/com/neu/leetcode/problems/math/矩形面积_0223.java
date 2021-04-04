package com.neu.leetcode.problems.math;

import java.util.Map;

public class 矩形面积_0223 {
    public int computeArea(int A, int B, int C, int D, int E, int F, int G, int H) {
        int area1 = (C-A)*(D-B);
        int area2 = (G-E) * (H-F);
        int ans = ( area1+ area2);
//        if (((E>=C || G<= A) && (F>=D || H<=B)) || ((E>=C || G<= A) )){
        if (((E>=C || G<= A) ) || ((F>=D || H<= B) )){
            System.out.println("1");
            return ans;
        } else {
            if (A<=E&& G<=C && F>= B && H <= D){
                System.out.println("21");
                // 1 包含 2
                ans -= area2;
            } else if ( E<=A && G>=C && F<=B && H>=D){
                System.out.println("22");
                // 2 包含1
                ans-=area1;
            } else {
                //角包含
                //右下
                if (A<=E && G>=C && D>=H && F <= B ){
                    ans -= (H-B) * (C-E);
                }
                //右上
                if (E>=A && C<= G && D<=H && F>= B ){
                    ans -= (C-E) * (D-F);
                }
                //左上
                if (F>=B && H>=D && G<=C && E<= A){
                    ans -= (D-F) * (G-A);
                }
                //左下
                if (H<=D && F<=B && G<=C && E<=A){
                    ans -= (H-B) * (G-A);
                }

                //1边包含2
                if (A<=E && G<=C && D>=H && F <= B ){
                    ans -= (H-B) * (G-E);
                }
                if (E>=A && C<= G && D>=H && F>= B ){
                    ans -= (C-E) * (H-F);
                }

                if (F>=B && H>=D && G<=C && E>= A){
                    ans -= (D-F) * (G-E);
                }

                if (H<=D && F>=B && G<=C && E<=A){
                    ans -= (G-A) * (H-F);
                }

                //2边包含1
                if (A>=E && G>=C && D<=H && F >= B ){
                    ans -= (C-A) * (D-F);
                }
                if (E>=A && C>= G && D<=H && F>= B ){
                    ans -= (D-B) * (G-A);
                }

                if (F<=B && H<=D && G>=C && E<= A){
                    ans -= (H-B) * (C-A);
                }

                if (H>=D && F<=B && G>=C && E>=A){
                    ans -= (D-B) * (C-E);
                    System.out.println("12");
                }

            }

        }

        return ans;

    }

    //高分题解
    public int computeArea1(int A, int B, int C, int D, int E, int F, int G, int H) {
        if (A > E){
            return computeArea1(E,F,G,H,A,B,C,D);
        }
        if (B >=H || D<=F || C<=E){
            return Math.abs(A-C) * Math.abs(B-D) + Math.abs(E-G) * Math.abs(F-H);
        }
        int down = Math.max(B,F);
        int up = Math.min(H,D);
        int left = Math.max(E,A);
        int right = Math.min(G,B);
        return Math.abs(A-C) * Math.abs(B-D) + Math.abs(E-G) * Math.abs(F-H) - (up-down) * (right-left);
    }

    //高分题解
    public int computeArea2(int A, int B, int C, int D, int E, int F, int G, int H) {
        int sum = 0;
        sum = (C-A) * (D-B) + (G-E) * (H-F);
        if (B >=H || D<=F || C<=E || A>=G){

            return sum;
        }
        int down = Math.max(B,F);
        int up = Math.min(H,D);
        int left = Math.max(E,A);
        int right = Math.min(G,C);
        return sum - Math.abs(up-down) * Math.abs(right-left);
    }
}
