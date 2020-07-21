package com.study.sort;

/**
 * @author Lesleey
 * @date 2020/6/2-20:46
 * @function 插入排序
 *  数组的插入排序是将分组分为两个部分，其中一部分已经有序，且最初只包含数组中第一个元素
 *  第二部分包含其余元素，算法是每次从无序中删除第一个元素，并将它插入到有序部分的合适位置
 *  在有序部分从后往前遍历，找到相应位置并插入
 *  时间复杂度（平均） 时间复杂度（最坏） 时间复杂度（最好） 空间复杂度 稳定性 复杂性
 *      O(n^2)      	O(n^2)	        O(n)	         O(1)	  稳定	简单
 *      最好情况是数组已经有序，最坏情况是数组逆序排序
 */
public class InsertSort {
    public void sort(int[] arr, int start, int end){
        for (int i = start + 1; i <= end; i++) {
            int rightPlace = i - 1;
            int curVal = arr[i];
            for (; rightPlace >= start && arr[rightPlace] > curVal; rightPlace --) {
                arr[rightPlace + 1] = arr[rightPlace];
            }
            arr[rightPlace + 1] = curVal;
        }
    }

    public static void main(String[] args) {
        int[] arr={0,8,1,2,4,6,3,0,4};
        new InsertSort().sort(arr, 0, arr.length - 1);
        for (int i = 0; i < arr.length; i ++) {
            System.out.print(arr[i]+" ");
        }
    }
}
