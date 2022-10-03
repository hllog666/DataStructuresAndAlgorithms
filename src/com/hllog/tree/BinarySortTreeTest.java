package com.hllog.tree;

/**
 * @author hllog
 * @create 2022-08-17 23:19
 */
public class BinarySortTreeTest {
    public static void main(String[] args) {
        int[] arr = {7, 3, 10, 12, 5, 1, 9};
        BinarySortTree binarySortTree = new BinarySortTree();
        for (int i = 0; i < arr.length; i++) {
            binarySortTree.add(new SortNode(arr[i]));
        }
        binarySortTree.infixOrder();
        System.out.println("-----------");
        binarySortTree.delNode(7);
        binarySortTree.infixOrder();
    }
}

class BinarySortTree {
    private SortNode root;

    public SortNode search(int value) {
        if (root == null) {
            return null;
        }
        return root.search(value);
    }

    public SortNode searchParent(int value) {
        if (root == null) {
            return null;
        }
        return root.searchParent(value);
    }

    public void delNode(int value) {
        if (root == null) {
            return;
        }
        // 没有这个值
        SortNode targetNode = search(value);
        if (targetNode == null) {
            return;
        }
        // 只有一个节点
        if (root.left == null && root.right == null) {
            root = null;
            return;
        }
        // 叶子节点
        SortNode parent = searchParent(value);
        if (targetNode.left == null && targetNode.right == null) {
            if (parent.left != null && parent.left.value == value) {
                parent.left = null;
                return;
            } else if (parent.right != null && parent.right.value == value) {
                parent.right = null;
                return;
            }
        }

        if (targetNode.left != null && targetNode.right != null) {
            targetNode.value = delRightTreeMin(targetNode.right);
            return;
        }

        // 只有一颗子树
        if (targetNode.left != null) {
            if (parent != null) {
                if (parent.left.value == value) {
                    parent.left = targetNode.left;
                } else {
                    parent.right = targetNode.left;
                }
            } else {
                root = targetNode;
            }
        } else {
            if (parent != null) {
                parent.right = targetNode.right;
            } else {
                root = targetNode.right;
            }
        }
    }

    public int delRightTreeMin(SortNode node) {
        SortNode target = node;
        while (target.left != null) {
            target = target.left;
        }
        delNode(target.value);
        return target.value;
    }

    public void add(SortNode node) {
        if (root == null) {
            root = node;
        } else {
            root.add(node);
        }
    }

    public void infixOrder() {
        if (root != null) {
            root.infixOrder();
        } else {
            System.out.println("空的");
        }
    }
}

class SortNode {
    int value;
    SortNode left;
    SortNode right;

    public SortNode(int value) {
        this.value = value;
    }

    @Override
    public String toString() {
        return "SortNode{" +
                "value=" + value +
                '}';
    }

    public void add(SortNode node) {
        if (node == null) {
            return;
        }
        if (node.value < this.value) {
            if (this.left == null) {
                this.left = node;
            } else {
                this.left.add(node);
            }
        } else {
            if (this.right == null) {
                this.right = node;
            } else {
                this.right.add(node);
            }
        }
    }

    public void infixOrder() {
        if (this.left != null) {
            this.left.infixOrder();
        }
        System.out.println(this);
        if (this.right != null) {
            this.right.infixOrder();
        }
    }

    public SortNode search(int value) {
        if (value == this.value) {
            return this;
        }
        if (value < this.value) {
            if (this.left == null) {
                return null;
            }
            return this.left.search(value);
        }
        if (this.right == null) {
            return null;
        }
        return this.right.search(value);
    }

    public SortNode searchParent(int value) {
        boolean flag = (this.left != null && this.left.value == value) ||
                (this.right != null && this.right.value == value);
        if (flag) {
            return this;
        }
        if (value < this.value && this.left != null) {
            return this.left.searchParent(value);
        }
        if (value > this.value && this.right != null) {
            return this.right.searchParent(value);
        }
        return null;
    }
}