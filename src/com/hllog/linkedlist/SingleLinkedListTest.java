package com.hllog.linkedlist;

import java.util.Stack;

/**
 * @author hllog
 * @create 2022-08-13 18:19
 */
public class SingleLinkedListTest {
    public static void main(String[] args) {
        HeroNode hero1 = new HeroNode(1, "宋江", "及时雨");
        HeroNode hero2 = new HeroNode(2, "卢俊义", "玉麒麟");
        HeroNode hero3 = new HeroNode(3, "吴用", "智多星");
        HeroNode hero4 = new HeroNode(4, "林冲", "豹子头");

        SingleLinkedList singleLinkedList = new SingleLinkedList();
        singleLinkedList.add(hero1);
        singleLinkedList.add(hero2);
        singleLinkedList.add(hero3);
        singleLinkedList.add(hero4);
        singleLinkedList.list();

        System.out.println("-----------------");

        SingleLinkedList singleLinkedList2 = new SingleLinkedList();
        singleLinkedList2.addByOrder(hero4);
        singleLinkedList2.addByOrder(hero1);
        singleLinkedList2.addByOrder(hero3);
        singleLinkedList2.addByOrder(hero2);
        singleLinkedList2.addByOrder(hero3);
        singleLinkedList2.list();

        HeroNode newHeroNode = new HeroNode(2, "卢俊义~", "玉麒麟~");
        singleLinkedList.update(newHeroNode);
        singleLinkedList.list();

        singleLinkedList.delete(4);
        singleLinkedList.list();
        System.out.println(getLength(singleLinkedList.getHead()));
        System.out.println(findLastIndexNode(singleLinkedList.getHead(), 2));
        HeroNode reverseNode = reverseLinkedList(singleLinkedList.getHead());
        System.out.println("---------------");
        singleLinkedList.list();
        reversePrint(singleLinkedList.getHead());

        System.out.println("----------------------------");

        SingleLinkedList singleLinkedList3 = new SingleLinkedList();
        singleLinkedList3.addByOrder(hero1);
        singleLinkedList3.addByOrder(hero3);
        SingleLinkedList singleLinkedList4 = new SingleLinkedList();
        singleLinkedList4.addByOrder(hero2);
        singleLinkedList4.addByOrder(hero4);
        singleLinkedList3.list();
        singleLinkedList4.list();
        HeroNode newHead = unionTwoLinkedList(singleLinkedList3.getHead(), singleLinkedList4.getHead());
        reversePrint(newHead);
    }

    /**
     * 求链表节点个数
     *
     * @param head 链表的头节点
     * @return 链表节点个数
     */
    public static int getLength(HeroNode head) {
        if (head.next == null) {
            return 0;
        }
        int length = 0;
        HeroNode cur = head.next;
        while (cur != null) {
            length++;
            cur = cur.next;
        }
        return length;
    }

    public static HeroNode findLastIndexNode(HeroNode head, int index) {
        if (head.next == null) {
            return null;
        }
        int size = getLength(head);
        if (index <= 0 || index > size) {
            return null;
        }
        HeroNode cur = head.next;
        for (int i = 0; i < size - index; i++) {
            cur = cur.next;
        }
        return cur;
    }

    public static HeroNode reverseLinkedList(HeroNode head) {
        if (head.next == null || head.next.next == null) {
            return head;
        }
        HeroNode cur = head.next;
        HeroNode next = null;
        HeroNode reverseHead = new HeroNode();
        while (cur != null) {
            next = cur.next;
            cur.next = reverseHead.next;
            reverseHead.next = cur;
            cur = next;
        }
        head.next = reverseHead.next;
        return reverseHead.next;
    }

    public static void reversePrint(HeroNode head) {
        if (head.next == null) {
            return;
        }
        Stack<HeroNode> stack = new Stack<>();
        HeroNode cur = head.next;
        while (cur != null) {
            stack.push(cur);
            cur = cur.next;
        }
        while (stack.size() > 0) {
            System.out.println(stack.pop());
        }
    }

    public static HeroNode unionTwoLinkedList(HeroNode head1, HeroNode head2) {
        if (head1.next == null) {
            return head2;
        }
        if (head2.next == null) {
            return head1;
        }
        HeroNode cur1 = head1.next;
        HeroNode cur2 = head2.next;
        HeroNode newHead = new HeroNode();
        HeroNode temp = newHead;
        while (cur1 != null && cur2 != null) {
            if (cur1.number > cur2.number) {
                temp.next = cur2;
                cur2 = cur2.next;
            } else {
                temp.next = cur1;
                cur1 = cur1.next;
            }
            temp = temp.next;
        }
        if (cur1 != null) {
            temp.next = cur1;
        }
        if (cur2 != null) {
            temp.next = cur2;
        }
        return newHead;
    }
}
