package com.hllog.tree;

/**
 * @author hllog
 * @create 2022-08-16 20:53
 */
public class ArrayBinaryTreeTest {
    public static void main(String[] args) {
        int[] array = {1, 2, 3, 4, 5, 6, 7};
        ArrayBinaryTree arrayBinaryTree = new ArrayBinaryTree(array);
        arrayBinaryTree.preOrder();
    }
}

class ArrayBinaryTree {
    private int[] array;

    public ArrayBinaryTree(int[] array) {
        this.array = array;
    }

    public void preOrder() {
        this.preOrder(0);
    }

    public void preOrder(int index) {
        if (array == null || array.length == 0) {
            System.out.println("数组为空");
        }
        System.out.println(array[index]);
        if ((index * 2 + 1) < array.length) {
            preOrder(2 * index + 1);
        }
        if ((index * 2 + 2) < array.length) {
            preOrder(2 * index + 2);
        }
    }
}