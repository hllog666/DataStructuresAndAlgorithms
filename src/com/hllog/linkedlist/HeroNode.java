package com.hllog.linkedlist;

/**
 * @author hllog
 * @create 2022-08-13 18:05
 */
public class HeroNode {
    int number;
    String name;
    String nickname;
    HeroNode next;

    public HeroNode() {
    }

    public HeroNode(int number, String name, String nickname) {
        this.number = number;
        this.name = name;
        this.nickname = nickname;
    }

    public HeroNode(int number, String name, String nickname, HeroNode next) {
        this.number = number;
        this.name = name;
        this.nickname = nickname;
        this.next = next;
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

    @Override
    public String toString() {
        return "HeroNode{" +
                "number=" + number +
                ", name='" + name + '\'' +
                ", nickname='" + nickname + '\'' +
                '}';
    }
}
