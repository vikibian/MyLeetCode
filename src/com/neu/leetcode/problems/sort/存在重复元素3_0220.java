package com.neu.leetcode.problems.sort;

import java.util.HashMap;
import java.util.Map;
import java.util.TreeSet;

public class 存在重复元素3_0220 {

    public boolean containsNearbyAlmostDuplicate(int[] nums, int k, int t) {
        int len = nums.length;
        int i =0,j=1;
        while (j<len){
//            int num = Math.abs(nums[i]-nums[j]);
            long longi = (long)nums[i];
            long longj = (long)nums[j];
            long num = Math.abs(longi-longj);
            System.out.println(num);
//            System.out.println(Long.valueOf(nums[j]-nums[i]));
            int index = Math.abs(j-i);
            if (num<=t && index<=k ){
                return true;
            } else {
                if ( index > k){
                    i++;
                    j = i;
                }

                if (num>t){
                    i++;
                    j = i;
                }
            }
            j++;
        }
        return false;
    }

    //官方题解 一 滑动窗口  有序合集
    public boolean containsNearbyAlmostDuplicate1(int[] nums, int k, int t) {
        int n = nums.length;
        TreeSet<Long> set = new TreeSet<>();
        for (int i=0;i<n;i++){
            Long ceiling = set.ceiling((long)nums[i]-(long)t);
            if (ceiling!= null && ceiling <= (long)nums[i]+(long)t){
                return true;
            }
            set.add((long)nums[i]);
            if (i>=k){
                set.remove((long)nums[i-k]);
            }
        }
        return false;
    }

    //官方题解 二 桶排序
    public boolean containsNearbyAlmostDuplicate2(int[] nums,int k,int t){
        int n = nums.length;
        Map<Long,Long> map = new HashMap<>();
        Long w = (long)t+1;
        for (int i=0;i<n;i++){
            long id = getId(nums[i],w);
            if (map.containsKey(id)){
                return true;
            }
            if (map.containsKey(id-1) && Math.abs(nums[i]-map.get(id-1))<w){
                return true;
            }
            if (map.containsKey(id+1) && Math.abs(nums[i]-map.get(id+1))<w){
                return true;
            }
            map.put(id,(long)nums[i]);
            if (i>=k){
                map.remove(getId(nums[i-k],w));
            }

        }
        return false;
    }

    private long getId(int num, Long w) {
        if (num>=0){
            return num/w;
        }
        return (num+1)/(w-1);
    }

    //宫水三叶  滑动窗口
    public boolean containsNearbyAlmostDuplicate3(int[] nums, int k, int t) {
        int n = nums.length;
        TreeSet<Long> ts = new TreeSet<>();
        for (int i = 0; i < n; i++) {
            Long u = nums[i] * 1L;
            // 从 ts 中找到小于等于 u 的最大值（小于等于 u 的最接近 u 的数）
            Long l = ts.floor(u);
            // 从 ts 中找到大于等于 u 的最小值（大于等于 u 的最接近 u 的数）
            Long r = ts.ceiling(u);
            if(l != null && u - l <= t) return true;
            if(r != null && r - u <= t) return true;
            // 将当前数加到 ts 中，并移除下标范围不在 [max(0, i - k), i) 的数（维持滑动窗口大小为 k）
            ts.add(u);
            if (i >= k) ts.remove(nums[i - k] * 1L);
        }
        return false;
    }


    //桶排序
    long size;
    public boolean containsNearbyAlmostDuplicate4(int[] nums, int k, int t) {
        int n = nums.length;
        Map<Long, Long> map = new HashMap<>();
        size = t + 1L;
        for (int i = 0; i < n; i++) {
            long u = nums[i] * 1L;
            long idx = getIdx(u);
            // 目标桶已存在（桶不为空），说明前面已有 [u - t, u + t] 范围的数字
            if (map.containsKey(idx)) return true;
            // 检查相邻的桶
            long l = idx - 1, r = idx + 1;
            if (map.containsKey(l) && u - map.get(l) <= t) return true;
            if (map.containsKey(r) && map.get(r) - u <= t) return true;
            // 建立目标桶
            map.put(idx, u);
            // 移除下标范围不在 [max(0, i - k), i) 内的桶
            if (i >= k) map.remove(getIdx(nums[i - k] * 1L));
        }
        return false;
    }
    long getIdx(long u) {
        return u >= 0 ? u / size : (u + 1) / size - 1;
    }

}
