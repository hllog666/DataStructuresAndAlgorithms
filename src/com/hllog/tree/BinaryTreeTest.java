package com.hllog.tree;

/**
 * @author hllog
 * @create 2022-08-16 18:57
 */
public class BinaryTreeTest {
    public static void main(String[] args) {
        BinaryTree binaryTree = new BinaryTree();
        HeroNode node1 = new HeroNode(1, "宋江");
        HeroNode node2 = new HeroNode(2, "吴用");
        HeroNode node3 = new HeroNode(3, "卢俊义");
        HeroNode node4 = new HeroNode(4, "林冲");
        HeroNode node5 = new HeroNode(5, "鲁智深");

        node1.setLeft(node2);
        node1.setRight(node3);
        node3.setRight(node4);
        node3.setLeft(node5);
        binaryTree.setRoot(node1);

        System.out.println("前序遍历");
        binaryTree.preOrder();
        System.out.println("中序遍历");
        binaryTree.infixOrder();
        System.out.println("后序遍历");
        binaryTree.postOrder();

        System.out.println("前序查找");
        System.out.println(binaryTree.preOrderSearch(3));
        System.out.println("中序查找");
        System.out.println(binaryTree.infixOrderSearch(3));
        System.out.println("后序查找");
        System.out.println(binaryTree.postOrderSearch(3));

        System.out.println("删除");
        binaryTree.delNode(3);
        binaryTree.preOrder();
    }
}

class BinaryTree {
    private HeroNode root;

    public void setRoot(HeroNode root) {
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

    public HeroNode preOrderSearch(int number) {
        if (this.root != null) {
            return root.preOrderSearch(number);
        } else {
            return null;
        }
    }

    public HeroNode infixOrderSearch(int number) {
        if (this.root != null) {
            return root.infixOrderSearch(number);
        } else {
            return null;
        }
    }

    public HeroNode postOrderSearch(int number) {
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
}

class HeroNode {
    private int number;
    private String name;
    private HeroNode left;
    private HeroNode right;

    public HeroNode(int number, String name) {
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

    public HeroNode getLeft() {
        return left;
    }

    public void setLeft(HeroNode left) {
        this.left = left;
    }

    public HeroNode getRight() {
        return right;
    }

    public void setRight(HeroNode right) {
        this.right = right;
    }

    @Override
    public String toString() {
        return "HeroNode{" +
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

    public HeroNode preOrderSearch(int number) {
        if (this.number == number) {
            return this;
        }
        HeroNode resNode = null;
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

    public HeroNode infixOrderSearch(int number) {
        HeroNode resNode = null;
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

    public HeroNode postOrderSearch(int number) {
        HeroNode resNode = null;
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
