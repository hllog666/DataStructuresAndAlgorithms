package com.hllog.tree;

/**
 * @author hllog
 * @create 2022-08-16 21:11
 */
public class ThreadedBinaryTreeTest {
    public static void main(String[] args) {
        HeroNode2 root = new HeroNode2(1, "tom");
        HeroNode2 node2 = new HeroNode2(2, "jack");
        HeroNode2 node3 = new HeroNode2(3, "smith");
        HeroNode2 node4 = new HeroNode2(4, "mary");
        HeroNode2 node5 = new HeroNode2(5, "king");
        HeroNode2 node6 = new HeroNode2(6, "dim");

        root.setLeft(node2);
        root.setRight(node3);
        node2.setLeft(node4);
        node2.setRight(node5);
        node3.setLeft(node6);

        ThreadedBinaryTree threadedBinaryTree = new ThreadedBinaryTree();
        threadedBinaryTree.setRoot(root);
        threadedBinaryTree.threadedNodes();

        System.out.println(node5.getLeft());
        System.out.println(node5.getRight());
        System.out.println(node4.getRight());
        System.out.println(node6.getRight());
        System.out.println("中序遍历");
        threadedBinaryTree.threadedList();
    }
}

class ThreadedBinaryTree {
    private HeroNode2 root;

    private HeroNode2 pre = null;

    public void setRoot(HeroNode2 root) {
        this.root = root;
    }

    public void preOrder() {
        if (this.root != null) {
            this.root.preOrder();
        } else {
            System.out.println("二叉树为空");
        }
    }

    public void infixOrder() {
        if (this.root != null) {
            this.root.infixOrder();
        } else {
            System.out.println("二叉树为空");
        }
    }

    public void postOrder() {
        if (this.root != null) {
            this.root.postOrder();
        } else {
            System.out.println("二叉树为空");
        }
    }

    public HeroNode2 preOrderSearch(int number) {
        if (this.root != null) {
            return root.preOrderSearch(number);
        } else {
            return null;
        }
    }

    public HeroNode2 infixOrderSearch(int number) {
        if (this.root != null) {
            return root.infixOrderSearch(number);
        } else {
            return null;
        }
    }

    public HeroNode2 postOrderSearch(int number) {
        if (this.root != null) {
            return root.postOrderSearch(number);
        } else {
            return null;
        }
    }

    public void delNode(int number) {
        if (root != null) {
            if (root.getNumber() == number) {
                root = null;
            } else {
                root.delNode(number);
            }
        } else {
            System.out.println("二叉树为空");
        }
    }

    public void threadedNodes() {
        this.threadedNodes(root);
    }

    public void threadedNodes(HeroNode2 node) {
        if (node == null) {
            return;
        }
        threadedNodes(node.getLeft());
        if (node.getLeft() == null) {
            node.setLeft(pre);
            node.setLeftType(1);
        }
        if (pre != null && pre.getRight() == null) {
            pre.setRight(node);
            pre.setRightType(1);
        }
        pre = node;
        threadedNodes(node.getRight());
    }

    public void threadedList() {
        HeroNode2 node = root;
        while (node != null) {
            while (node.getLeftType() == 0) {
                node = node.getLeft();
            }
            System.out.println(node);
            while (node.getRightType() == 1) {
                node = node.getRight();
                System.out.println(node);
            }
            node = node.getRight();
        }
    }
}

class HeroNode2 {
    private int number;
    private String name;
    private HeroNode2 left;
    private HeroNode2 right;
    /**
     * 0：左子树，1：前驱
     */
    private int leftType;
    /**
     * 0：右子树，1：后继
     */
    private int rightType;

    public int getLeftType() {
        return leftType;
    }

    public void setLeftType(int leftType) {
        this.leftType = leftType;
    }

    public int getRightType() {
        return rightType;
    }

    public void setRightType(int rightType) {
        this.rightType = rightType;
    }

    public HeroNode2(int number, String name) {
        this.number = number;
        this.name = name;
    }

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public HeroNode2 getLeft() {
        return left;
    }

    public void setLeft(HeroNode2 left) {
        this.left = left;
    }

    public HeroNode2 getRight() {
        return right;
    }

    public void setRight(HeroNode2 right) {
        this.right = right;
    }

    @Override
    public String toString() {
        return "HeroNode2{" +
                "number=" + number +
                ", name='" + name + '\'' +
                '}';
    }

    public void preOrder() {
        System.out.println(this);
        if (this.left != null) {
            this.left.preOrder();
        }
        if (this.right != null) {
            this.right.preOrder();
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

    public void postOrder() {
        if (this.left != null) {
            this.left.postOrder();
        }
        if (this.right != null) {
            this.right.postOrder();
        }
        System.out.println(this);
    }

    public HeroNode2 preOrderSearch(int number) {
        if (this.number == number) {
            return this;
        }
        HeroNode2 resNode = null;
        if (this.left != null) {
            resNode = this.left.preOrderSearch(number);
        }
        if (resNode != null) {
            return resNode;
        }
        if (this.right != null) {
            resNode = this.right.preOrderSearch(number);
        }
        return resNode;
    }

    public HeroNode2 infixOrderSearch(int number) {
        HeroNode2 resNode = null;
        if (this.left != null) {
            resNode = this.left.preOrderSearch(number);
        }
        if (resNode != null) {
            return resNode;
        }
        if (this.number == number) {
            return this;
        }
        if (this.right != null) {
            resNode = this.right.preOrderSearch(number);
        }
        return resNode;
    }

    public HeroNode2 postOrderSearch(int number) {
        HeroNode2 resNode = null;
        if (this.left != null) {
            resNode = this.left.preOrderSearch(number);
        }
        if (resNode != null) {
            return resNode;
        }
        if (this.right != null) {
            resNode = this.right.preOrderSearch(number);
        }
        if (resNode != null) {
            return resNode;
        }
        return this.number == number ? this : null;
    }

    public void delNode(int number) {
        if (this.left != null && this.left.number == number) {
            this.left = null;
            return;
        }
        if (this.right != null && this.right.number == number) {
            this.right = null;
            return;
        }
        if (this.left != null) {
            this.left.delNode(number);
        }
        if (this.right != null) {
            this.right.delNode(number);
        }
    }
}