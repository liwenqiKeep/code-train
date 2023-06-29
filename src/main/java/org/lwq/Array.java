package org.lwq;

import java.util.Arrays;

/**
 * @author Liwq
 */
public class Array {
    public static void main(String[] args) {

        Array array = new Array();
        int[] nums = {-7, -3, 2, 3, 11};

        // 移除元素并返回新数组长度
        System.out.println(array.removeElement(nums, 1));
        // 二分查找
        System.out.println(array.search(nums, 4));
        // 平方排序
        System.out.println(Arrays.toString(array.sortedSquares(nums)));
        //209. 长度最小的子数组
        System.out.println(array.minSubArrayLen(1, nums));
        //59. 螺旋矩阵 II
        System.out.println(Arrays.toString(array.generateMatrix(5)));

    }

    /**
     * 给你一个正整数 n ，生成一个包含 1 到 n2 所有元素，且元素按顺时针顺序螺旋排列的 n x n 正方形矩阵 matrix 。
     */
    public int[][] generateMatrix(int n) {
        int[][] res = new int[n][n];
        int minX = 0;
        int maxX = n;
        int minY = 0;
        int maxY = n;
        int index = 0;
        int a = 1;
        // 依次插入数据，
        // minX,minY -> maxX,minY，minY插入完成，minY ++;
        // maxX,minY -> maxX,maxY, maxX插入完成，maxX --;
        // maxX,maxY -> minX,maxY, maxY插入完成，maxY --;
        // minX,maxy —> minx,minY, minX插入完成，minX --;
        while (index < n) {
            for (int i = minX; i < maxX; i++) {
                res[minY][i] = a++;
            }
            minY++;
            for (int i = minY; i < maxY; i++) {
                res[i][maxX - 1] = a++;
            }
            maxX--;
            for (int i = maxX - 1; i >= minX; i--) {
                res[maxY - 1][i] = a++;
            }
            maxY--;

            for (int i = maxY - 1; i >= minY; i--) {
                res[i][minX] = a++;
            }
            minX++;

            index++;
        }


        return res;
    }

    /**
     * 209. 长度最小的子数组
     * 给定一个含有 n 个正整数的数组和一个正整数 target 。
     * <p>
     * 找出该数组中满足其和 ≥ target 的长度最小的 连续子数组 [nums1, nums a+1, ..., nums a-1, nums e] ，并返回其长度。如果不存在符合条件的子数组，返回 0 。
     * 示例 1：
     * <p>
     * 输入：target = 7, nums = [2,3,1,2,4,3]
     * 输出：2
     * 解释：子数组 [4,3] 是该条件下的长度最小的子数组。
     * 示例 2：
     * <p>
     * 输入：target = 4, nums = [1,4,4]
     * 输出：1
     * 示例 3：
     * <p>
     * 输入：target = 11, nums = [1,1,1,1,1,1,1,1]
     * 输出：0
     */
    public int minSubArrayLen(int target, int[] nums) {
        // 滑动窗口。 窗口长度由 target决定，最后取出最新的窗口长度
        int i = 0, j = 0;
        int len = nums.length;

        int res = Integer.MAX_VALUE;
        int count = 0;
        while (i < len) {
            count += nums[i];
            while (count >= target) {
                res = Math.min(res, i - j + 1);
                count -= nums[j];
                j++;
            }

            i++;
        }


        return res == Integer.MAX_VALUE ? 0 : res;
    }

    /**
     * 给你一个按 非递减顺序 排序的整数数组 nums，返回 每个数字的平方 组成的新数组，要求也按 非递减顺序 排序。
     * <p>
     * <p>
     * 示例 1：
     * <p>
     * 输入：nums = [-4,-1,0,3,10]
     * 输出：[0,1,9,16,100]
     * 解释：平方后，数组变为 [16,1,0,9,100]
     * 排序后，数组变为 [0,1,9,16,100]
     * 示例 2：
     * <p>
     * 输入：nums = [-7,-3,2,3,11]
     * 输出：[4,9,9,49,121]
     */
    public int[] sortedSquares(int[] nums) {
        // 双指针，需要注意的是负数的平方值可能更大，但数组本身是有序的，只需要双边判断是否移动即可
        int len = nums.length;
        int[] res = new int[len];
        int i = 0, j = len - 1;
        int index = j;
        while (i < j) {
            // 代表当前的最小值的平方大于最大值的平方，则结果的最大值插入
            if (nums[i] * nums[i] > nums[j] * nums[j]) {
                res[index--] = nums[i] * nums[i];
                i++;
            } else {
                res[index--] = nums[j] * nums[j];
                j--;
            }
        }


        return res;
    }

    /**
     * 27. 移除元素
     * 给你一个数组 nums 和一个值 val，你需要 原地 移除所有数值等于 val 的元素，并返回移除后数组的新长度。
     * <p>
     * 不要使用额外的数组空间，你必须仅使用 O(1) 额外空间并 原地 修改输入数组。
     * <p>
     * 元素的顺序可以改变。你不需要考虑数组中超出新长度后面的元素。
     * <p>
     * <p>
     * <p>
     * 说明:
     * <p>
     * 为什么返回数值是整数，但输出的答案是数组呢?
     * <p>
     * 请注意，输入数组是以「引用」方式传递的，这意味着在函数里修改输入数组对于调用者是可见的。
     * <p>
     * 你可以想象内部操作如下:
     * <p>
     * // nums 是以“引用”方式传递的。也就是说，不对实参作任何拷贝
     * int len = removeElement(nums, val);
     * <p>
     * // 在函数里修改输入数组对于调用者是可见的。
     * // 根据你的函数返回的长度, 它会打印出数组中 该长度范围内 的所有元素。
     * for (int i = 0; i < len; i++) {
     * print(nums[i]);
     * }
     * <p>
     * <p>
     * 示例 1：
     * <p>
     * 输入：nums = [3,2,2,3], val = 3
     * 输出：2, nums = [2,2]
     * 解释：函数应该返回新的长度 2, 并且 nums 中的前两个元素均为 2。你不需要考虑数组中超出新长度后面的元素。例如，函数返回的新长度为 2 ，而 nums = [2,2,3,3] 或 nums = [2,2,0,0]，也会被视作正确答案。
     * 示例 2：
     * <p>
     * 输入：nums = [0,1,2,2,3,0,4,2], val = 2
     * 输出：5, nums = [0,1,4,0,3]
     * 解释：函数应该返回新的长度 5, 并且 nums 中的前五个元素为 0, 1, 3, 0, 4。注意这五个元素可为任意顺序。你不需要考虑数组中超出新长度后面的元素。
     */
    public int removeElement(int[] nums, int val) {
        int i = 0;
        for (int j = 0; j < nums.length; j++) {
            // 将不等于 目标值的 依次替换nums中的元素，并移动有效下标，代表元素个数
            if (nums[j] != val) {
                nums[i] = nums[j];
                i++;
            }

        }
        return i;
    }

    /**
     * 给定一个 n 个元素有序的（升序）整型数组 nums 和一个目标值 target  ，写一个函数搜索 nums 中的 target，如果目标值存在返回下标，否则返回 -1。
     * <p>
     * 示例 1:
     * <p>
     * 输入: nums = [-1,0,3,5,9,12], target = 9
     * 输出: 4
     * 解释: 9 出现在 nums 中并且下标为 4
     * 示例 2:
     * <p>
     * 输入: nums = [-1,0,3,5,9,12], target = 2
     * 输出: -1
     * 解释: 2 不存在 nums 中因此返回 -1
     * 提示：
     * <p>
     * 你可以假设 nums 中的所有元素是不重复的。
     * n 将在 [1, 10000]之间。
     * nums 的每个元素都将在 [-9999, 9999]之间。
     * #思路
     */
    public int search(int[] nums, int target) {

        // 注意左右边界问题
        int len = nums.length;
        int left = 0, right = len - 1;
        while (left <= right) {
            int s = (right + left) >> 1;
            if (nums[s] == target) {
                return s;
            } else if (nums[s] > target) {
                right = s - 1;
            } else {
                left = s + 1;
            }
        }
        return -1;
    }
}
