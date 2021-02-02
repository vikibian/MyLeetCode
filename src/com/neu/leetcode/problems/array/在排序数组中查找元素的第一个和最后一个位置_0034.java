package com.neu.leetcode.problems.array;

public class 在排序数组中查找元素的第一个和最后一个位置_0034 {

    public static void main(String[] args) {
        int[] nums = new int[]{1,4};
        System.out.println(searchRange(nums,4)[0]);
        System.out.println(((1+2) >>> 1));
    }

    public static int[] searchRange(int[] nums, int target) {
        int len = nums.length;
        if (len == 0){
            return new int[] {-1,-1};
        }

        if (len == 1 ){
            if (target==nums[0]){
                return new int[] {0,0};
            } else {
                return new int[] {-1,-1};
            }

        }
        int left =0,right = len-1;
        int index = -1;
        while (left<=right){
            int mid = (left + right) / 2;
            if (nums[mid] == target){
                index = mid;
                break;
            } else if (target>=nums[left] && target<nums[mid]){
                right = mid -1;
            } else if (target<=nums[right] && target>nums[mid]){
                left = mid + 1;
            }else {
                left++;
                right--;
            }
        }

        if (index == -1){
            return new int[] {-1,-1};
        } else {
            int indexleft = index,indexright=index;
            System.out.println(indexleft);
            while (indexleft >= 0 && nums[indexleft] == target ){
                indexleft--;
            }
            while (indexright<len && nums[indexright] == target){
                indexright++;
            }

            if (indexleft <len-1){
                indexleft++;
            }

            if (indexright>=1){
                indexright--;
            }


            return new int[] {indexleft,indexright};
        }

    }

    //二分查找 官方文字题解
    public int[] searchRange1(int[] nums, int target) {
        int leftIdx = binarySearch(nums,target,true);
        int rightIdx = binarySearch(nums,target,false) - 1;
        if (leftIdx <= rightIdx && rightIdx<nums.length&&nums[leftIdx] == target&&nums[rightIdx] == target){
            return new int[] {leftIdx,rightIdx};
        }
        
        return new int[] {-1,-1};
    }

    private int binarySearch(int[] nums, int target, boolean lower) {
        int left = 0,right = nums.length - 1,ans = nums.length;
        while (left <= right){
            int mid = (left + right) /2;
            if (nums[mid]> target || (lower && nums[mid]>= target)){
                right = mid -1;
                ans = mid;
            } else {
                left = mid+1;
            }
        }
        return ans;
    }

    //二分查找 官方视频题解
    public int[] searchRange2(int[] nums,int target){
        int len = nums.length;
        if (len == 0){
            return new int[]{-1,-1};
        }
        int firstPosition = findFirstPosition(nums,target);
        if (firstPosition == -1){
            return new int[] {-1,-1};
        }
        int lastPosition = findLastPosition(nums,target);
        return new int[] {firstPosition,lastPosition};
    }

    private int findLastPosition(int[] nums, int target) {
        int left = 0;
        int right = nums.length - 1;
        while (left < right){
            int mid = (left + right) >>> 1;
            if (nums[mid] <target){
                left = mid + 1;
            } else if (nums[mid] == target){
                left = mid;
            } else {
                right = mid - 1;
            }
        }
        if (nums[left] == target){
            return left;
        }
        return -1;
    }

    private int findFirstPosition(int[] nums, int target) {
        int left = 0;
        int right = nums.length - 1;
        while (left <right){
            int mid = (left + right) >>> 1;
            if (nums[mid] < target){
                //下一轮搜索区间是 [mid + 1,right]
                left = mid +1;
            } else if (nums[mid] == target){
                //下一轮搜索区间是 [left,mid]
                right = mid;
            } else {
                // nums[mid] > target
                // 下一轮的搜索区间[left,mid-1]
                right = mid - 1;
            }
        }
        if (nums[left] == target){
            return left;
        }
        return -1;
    }
}
