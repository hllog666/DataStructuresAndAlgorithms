package com.hllog.search;

import java.util.ArrayList;
import java.util.List;

/**
 * @author hllog
 * @create 2022-08-15 20:52
 */
public class BinarySearch {
    public static void main(String[] args) {
        int[] arr = {1, 8, 10, 89, 1000, 1234};
        int index = binarySearch(arr, 0, arr.length - 1, 88);
        if (index == -1) {
            System.out.println("没有找到");
        } else {
            System.out.println("下标为" + index);
        }

        int[] arr2 = {1, 2, 2, 3, 3, 4, 4, 4, 5};
        List<Integer> list = binarySearch2(arr2, 0, arr.length - 1, 4);
        list.forEach(System.out::println);
    }

    public static int binarySearch(int[] arr, int left, int right, int value) {
        int mid = (left + right) / 2;
        int midValue = arr[mid];
        if (left > right) {
            return -1;
        }
        if (value > midValue) {
            return binarySearch(arr, mid + 1, right, value);
        } else if (value < midValue) {
            return binarySearch(arr, left, mid - 1, value);
        } else {
            return mid;
        }
    }

    /**
     * 找到所有值为value的数的下标
     *
     * @param arr   要查找的数组
     * @param left  查找左边界
     * @param right 查找右边界
     * @param value 要查找的值
     * @return 所有下标
     */
    public static List<Integer> binarySearch2(int[] arr, int left, int right, int value) {
        List<Integer> resIndexList;

        int mid = (left + right) / 2;
        int midValue = arr[mid];
        if (left > right) {
            return new ArrayList<>();
        }
        if (value > midValue) {
            return binarySearch2(arr, mid + 1, right, value);
        } else if (value < midValue) {
            return binarySearch2(arr, left, mid - 1, value);
        } else {
            resIndexList = new ArrayList<>();
            int temp = mid - 1;
            while (true) {
                if (temp < 0 || arr[temp] != value) {
                    break;
                }
                resIndexList.add(temp);
                temp--;
            }
            resIndexList.add(mid);
            temp = mid + 1;
            while (true) {
                if (temp > arr.length - 1 || arr[temp] != value) {
                    break;
                }
                resIndexList.add(temp);
                temp++;
            }
        }
        return resIndexList;
    }
}
