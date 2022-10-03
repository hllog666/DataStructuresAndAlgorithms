package com.hllog.doublelinkedlist;

/**
 * @author hllog
 * @create 2022-08-14 0:55
 */
public class HeroNode {
    int number;
    String name;
    String nickname;
    HeroNode next;
    HeroNode prev;

    public HeroNode() {
    }

    public HeroNode(int number, String name, String nickname) {
        this.number = number;
        this.name = name;
        this.nickname = nickname;
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

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public HeroNode getNext() {
        return next;
    }

    public void setNext(HeroNode next) {
        this.next = next;
    }

    public HeroNode getPrev() {
        return prev;
    }

    public void setPrev(HeroNode prev) {
        this.prev = prev;
    }

    @Override
    public String toString() {
        return "HeroNode{" +
                "number=" + number +
                ", name='" + name + '\'' +
                ", nickname='" + nickname + '\'' +
                '}';
    }
}
