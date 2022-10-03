package com.hllog.tree;

/**
 * @author hllog
 * @create 2022-08-18 23:36
 */
public class AvlTreeTest {
    public static void main(String[] args) {
        int[] arr = {4, 3, 6, 5, 7, 8};
        AvlTree avlTree = new AvlTree();
        for (int i = 0; i < arr.length; i++) {
            avlTree.add(new AvlNode(arr[i]));
        }
        avlTree.infixOrder();

        System.out.println(avlTree.getRoot().height());
    }
}

class AvlTree {
    private AvlNode root;

    public AvlNode getRoot() {
        return root;
    }

    public AvlNode search(int value) {
        if (root == null) {
            return null;
        }
        return root.search(value);
    }

    public AvlNode searchParent(int value) {
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
        AvlNode targetNode = search(value);
        if (targetNode == null) {
            return;
        }
        // 只有一个节点
        if (root.left == null && root.right == null) {
            root = null;
            return;
        }
        // 叶子节点
        AvlNode parent = searchParent(value);
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

    public int delRightTreeMin(AvlNode node) {
        AvlNode target = node;
        while (target.left != null) {
            target = target.left;
        }
        delNode(target.value);
        return target.value;
    }

    public void add(AvlNode node) {
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

class AvlNode {
    int value;
    AvlNode left;
    AvlNode right;

    public AvlNode(int value) {
        this.value = value;
    }

    private void leftRotate() {
        AvlNode newNode = new AvlNode(value);
        newNode.left = left;
        newNode.right = right.left;
        value = right.value;
        right = right.right;
        left = newNode;
    }

    private void rightRotate() {
        AvlNode newNode = new AvlNode(value);
        newNode.right = right;
        newNode.left = left.right;
        value = left.value;
        left = left.left;
        right = newNode;
    }

    public int leftHeight() {
        if (left == null) {
            return 0;
        }
        return left.height();
    }

    public int rightHeight() {
        if (right == null) {
            return 0;
        }
        return right.height();
    }

    public int height() {
        return Math.max(left == null ? 0 : left.height(), right == null ? 0 : right.height()) + 1;
    }

    @Override
    public String toString() {
        return "AvlNode{" +
                "value=" + value +
                '}';
    }

    public void add(AvlNode node) {
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

        if (rightHeight() - leftHeight() > 1) {
            if (right != null && right.leftHeight() > right.rightHeight()) {
                right.rightRotate();
                leftRotate();
            } else {
                leftRotate();
            }
            return;
        }
        if (leftHeight() - rightHeight() > 1) {
            if (left != null && left.rightHeight() > left.leftHeight()) {
                left.leftRotate();
                rightRotate();
            } else {
                rightRotate();
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

    public AvlNode search(int value) {
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

    public AvlNode searchParent(int value) {
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