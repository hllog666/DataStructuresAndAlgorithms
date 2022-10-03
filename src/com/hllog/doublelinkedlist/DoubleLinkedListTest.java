package com.hllog.doublelinkedlist;

/**
 * @author hllog
 * @create 2022-08-14 1:09
 */
public class DoubleLinkedListTest {
    public static void main(String[] args) {
        HeroNode hero1 = new HeroNode(1, "宋江", "及时雨");
        HeroNode hero2 = new HeroNode(2, "卢俊义", "玉麒麟");
        HeroNode hero3 = new HeroNode(3, "吴用", "智多星");
        HeroNode hero4 = new HeroNode(4, "林冲", "豹子头");

        DoubleLinkedList doubleLinkedList = new DoubleLinkedList();
        doubleLinkedList.add(hero1);
        doubleLinkedList.add(hero2);
        doubleLinkedList.add(hero3);
        doubleLinkedList.add(hero4);
        doubleLinkedList.list();

        System.out.println("------------");
        HeroNode hero5 = new HeroNode(4, "公孙胜", "入云龙");
        doubleLinkedList.update(hero5);
        doubleLinkedList.list();

        System.out.println("---------------");
        doubleLinkedList.delete(3);
        doubleLinkedList.list();
    }
}
