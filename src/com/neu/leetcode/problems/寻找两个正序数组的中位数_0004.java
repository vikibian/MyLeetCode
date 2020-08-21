package com.neu.leetcode.problems;

import java.util.Arrays;

public class 寻找两个正序数组的中位数_0004 {
    public static void main(String[] args) {
        int[] nums1 = {1,3,4,9};
        int[] nums2 = {1,2,3,4,5,6,7,8,9};
        double medianSortedArrays = findMedianSortedArrays3(nums1, nums2);
        System.out.println("result:"+medianSortedArrays);
//        int a =3;
//        int b=4;
//        System.out.println("test:"+(double)(a+b)/2);

    }

    public static double findMedianSortedArrays(int[] nums1, int[] nums2) {
        double result = 0;
        int length1 = nums1.length;
        int length2 = nums2.length;
        if (length1 == 0){
            if (length2 % 2 ==1){
                int index = length2 /2;
                return nums2[index];
            }else {
                return  (double)(nums2[length2/2]+nums2[(length2/2)-1])/2;
            }
        }

        if (length2 == 0){
            if (length1 % 2 ==1){
                int index = length1 /2;
                return nums1[index];
            }else {
                return (double) (nums1[length1/2]+nums1[(length1/2)-1])/2;
            }
        }

        int[] sum = new int[length1+length2];

        int pointer1 = 0;
        int pointer2 = 0;
        boolean end1 = false;
        boolean end2 = false;
        for (int i=0;i<length1+length2;i++){
            if ((nums1[pointer1] <= nums2[pointer2])){
//                if (i -  length1 < pointer2){
//                    sum[i] = nums1[pointer1];
//                    if (pointer1 != length1 - 1){
//                        pointer1++;
//                    }
//
//                }else {
//                    sum[i] = nums2[pointer2];
//                    if (pointer2 != length2-1 ){
//                        pointer2++;
//                    }
//
//                }
                if (!end1){
                    sum[i] = nums1[pointer1];
                    if (pointer1 == length1-1){
                        end1 = true;
                    }else {
                        pointer1++;
                    }
                }else {
                    sum[i] = nums2[pointer2];
                    pointer2++;
                }
            }else {

//                if (i - length2 < pointer1){
//                    sum[i] = nums2[pointer2];
//                    if (pointer2 != length2 - 1){
//                        pointer2++;
//                    }
//
//                }else {
//                    sum[i] = nums1[pointer1];
//                    if (pointer1 != length1 - 1){
//                        pointer1++;
//                    }
//
//                }
                if (!end2){
                    sum[i] = nums2[pointer2];
                    if (pointer2 == length2-1){
                        end2 = true;
                    }else {
                        pointer2++;
                    }
                }else {
                    sum[i] = nums1[pointer1];
                    pointer1++;
                }

            }
        }


        if ((length1 + length2) % 2 ==1){
             result = (double)sum[(length1 + length2)/2];
        }else {
            result = ((double)(sum[(length1 + length2) / 2] + sum[((length1 + length2) / 2) -1]) / 2);
        }

//        int[] sum2 = {1,2,3,4};
//        if (sum2.length % 2 ==1){
//            return (double)sum2[sum2.length/2];
//        }else {
//            System.err.println("double1:"+((sum2[sum2.length / 2] + sum2[(sum2.length / 2) -1]) / 2));
//            System.err.println("double2:"+(sum2[sum2.length / 2] + sum2[(sum2.length / 2) -1]));
//            return  ((double)(sum2[sum2.length / 2] + sum2[(sum2.length / 2) -1]) / 2);//注意类型转换的位置
//        }


        return result;
    }

    /**
     * 力扣官方题解
     */
    public double findMedianSortedArrays2(int[] nums1, int[] nums2){
        if (nums1.length >= nums2.length){
            int[] temp = nums1;
            nums1 = nums2;
            nums2 = temp;
        }

        int m = nums1.length;
        int n = nums2.length;

        //分割线左边的所有元素需要满足的 m + （n - m + 1） / 2
        int totalLeft = (m + n +1)/2;

        //在nums1的区间[0,m]里查找恰当的分割线
        //使得nums1[i-1] <= nums2[j] && nums2[j - 1] <= nums1[i]
        int left=0;
        int right = m;
        while (left < right){
            int i = left + (right - left + 1) /2;
            int j=totalLeft - i;
            if (nums1[i-1] > nums2[j]){
                //下一轮搜索区间[left, i-1]
                right = i;
            }else {
                //下一轮搜索区间[i, right]
                // [left,right] 当区间只有2个元素时 一旦进入这个分支，左边届成为i的时候 这个区间就不会再缩小了 循坏就如死循环
                left = i;
            }
        }

        int i = left;
        int j = totalLeft - i;

        int nums1LeftMax = i==0?Integer.MIN_VALUE:nums1[i-1];
        int nums1RightMin = i==m?Integer.MAX_VALUE:nums1[i];
        int nums2LeftMax = j==0?Integer.MIN_VALUE:nums2[j-1];
        int nums2RightMin = j==n?Integer.MAX_VALUE:nums2[j];

        if (((m + n)%2)==1){
            return  Math.max(nums1LeftMax,nums2LeftMax);
        }else {
            return (double) ((Math.max(nums1LeftMax,nums2LeftMax))+(Math.max(nums1RightMin,nums2RightMin))) / 2;
        }

    }


        public static double findMedianSortedArrays3(int[] nums1, int[] nums2) {
            int length1 = nums1.length, length2 = nums2.length;
            int totalLength = length1 + length2;
            if (totalLength % 2 == 1) {
                int midIndex = totalLength / 2;
                double median = getKthElement(nums1, nums2, midIndex + 1);
                return median;
            } else {
                int midIndex1 = totalLength / 2 - 1, midIndex2 = totalLength / 2;
                double median = (getKthElement(nums1, nums2, midIndex1 + 1) + getKthElement(nums1, nums2, midIndex2 + 1)) / 2.0;
                return median;
            }
        }

        public static int getKthElement(int[] nums1, int[] nums2, int k) {
            /* 主要思路：要找到第 k (k>1) 小的元素，那么就取 pivot1 = nums1[k/2-1] 和 pivot2 = nums2[k/2-1] 进行比较
             * 这里的 "/" 表示整除
             * nums1 中小于等于 pivot1 的元素有 nums1[0 .. k/2-2] 共计 k/2-1 个
             * nums2 中小于等于 pivot2 的元素有 nums2[0 .. k/2-2] 共计 k/2-1 个
             * 取 pivot = min(pivot1, pivot2)，两个数组中小于等于 pivot 的元素共计不会超过 (k/2-1) + (k/2-1) <= k-2 个
             * 这样 pivot 本身最大也只能是第 k-1 小的元素
             * 如果 pivot = pivot1，那么 nums1[0 .. k/2-1] 都不可能是第 k 小的元素。把这些元素全部 "删除"，剩下的作为新的 nums1 数组
             * 如果 pivot = pivot2，那么 nums2[0 .. k/2-1] 都不可能是第 k 小的元素。把这些元素全部 "删除"，剩下的作为新的 nums2 数组
             * 由于我们 "删除" 了一些元素（这些元素都比第 k 小的元素要小），因此需要修改 k 的值，减去删除的数的个数
             */

            int length1 = nums1.length, length2 = nums2.length;
            int index1 = 0, index2 = 0;
            int kthElement = 0;

            while (true) {
                // 边界情况
                //说明该数组中的所有元素都被排除，我们可以直接返回另一个数组中第 k 小的元素
                if (index1 == length1) {
                    //不只是看当一个数组为空时，也考虑到当指针移动到数组最后的情况
                    return nums2[index2 + k - 1];
                }
                if (index2 == length2) {
                    return nums1[index1 + k - 1];
                }
                if (k == 1) {
                    return Math.min(nums1[index1], nums2[index2]);
                }

                // 正常情况
                int half = k / 2;
                int newIndex1 = Math.min(index1 + half, length1) - 1;
                int newIndex2 = Math.min(index2 + half, length2) - 1;
                int pivot1 = nums1[newIndex1], pivot2 = nums2[newIndex2];
                //如果 \text{A}[k/2-1]A[k/2−1] 或者 \text{B}[k/2-1]B[k/2−1] 越界，那么我们可以选取对应数组中的最后一个元素。在这种情况下，我们必须根据排除数的个数减少 kk 的值，而不能直接将 kk 减去 k/2k/2
                //
                if (pivot1 <= pivot2) {
                    k -= (newIndex1 - index1 + 1);
                    index1 = newIndex1 + 1;
                } else {
                    k -= (newIndex2 - index2 + 1);//k=k-k/2=
                    index2 = newIndex2 + 1;
                }
            }
        }


        //思路同上面的解法
    public static double findMedianSortedArrays4(int[] nums1, int[] nums2) {
        int n = nums1.length;
        int m = nums2.length;
        int left = (n + m + 1) / 2;
        int right = (n + m + 2) / 2;
        //将偶数和奇数的情况合并，如果是奇数，会求两次同样的 k 。
        return (getKth(nums1, 0, n - 1, nums2, 0, m - 1, left) + getKth(nums1, 0, n - 1, nums2, 0, m - 1, right)) * 0.5;
    }

    private static int getKth(int[] nums1, int start1, int end1, int[] nums2, int start2, int end2, int k) {
        int len1 = end1 - start1 + 1;
        int len2 = end2 - start2 + 1;
        //让 len1 的长度小于 len2，这样就能保证如果有数组空了，一定是 len1
        if (len1 > len2) return getKth(nums2, start2, end2, nums1, start1, end1, k);
        if (len1 == 0) return nums2[start2 + k - 1];

        if (k == 1) return Math.min(nums1[start1], nums2[start2]);

        int i = start1 + Math.min(len1, k / 2) - 1;
        int j = start2 + Math.min(len2, k / 2) - 1;

        if (nums1[i] > nums2[j]) {
            return getKth(nums1, start1, end1, nums2, j + 1, end2, k - (j - start2 + 1));
        }
        else {
            return getKth(nums1, i + 1, end1, nums2, start2, end2, k - (i - start1 + 1));
        }
    }

        //
        public double findMedianSortedArrays5(int[] A, int[] B) {
            int m = A.length;
            int n = B.length;
            if (m > n) {
                return findMedianSortedArrays5(B,A); // 保证 m <= n
            }
            int iMin = 0, iMax = m;
            while (iMin <= iMax) {
                int i = (iMin + iMax) / 2;
                int j = (m + n + 1) / 2 - i;
                if (j != 0 && i != m && B[j-1] > A[i]){ // i 需要增大
                    iMin = i + 1;
                }
                else if (i != 0 && j != n && A[i-1] > B[j]) { // i 需要减小
                    iMax = i - 1;
                }
                else { // 达到要求，并且将边界条件列出来单独考虑
                    int maxLeft = 0;
                    if (i == 0) { maxLeft = B[j-1]; }
                    else if (j == 0) { maxLeft = A[i-1]; }
                    else { maxLeft = Math.max(A[i-1], B[j-1]); }
                    if ( (m + n) % 2 == 1 ) { return maxLeft; } // 奇数的话不需要考虑右半部分

                    int minRight = 0;
                    if (i == m) { minRight = B[j]; }
                    else if (j == n) { minRight = A[i]; }
                    else { minRight = Math.min(B[j], A[i]); }

                    return (maxLeft + minRight) / 2.0; //如果是偶数的话返回结果
                }
            }
            return 0.0;
        }




}
