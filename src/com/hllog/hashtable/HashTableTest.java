package com.hllog.hashtable;

import java.util.Scanner;

/**
 * @author hllog
 * @create 2022-08-15 23:00
 */
public class HashTableTest {
    public static void main(String[] args) {
        MyHashTable myHashTable = new MyHashTable(7);

        String key = "";
        Scanner scanner = new Scanner(System.in);
        while (true) {
            System.out.println("add:添加雇员");
            System.out.println("list:显示雇员");
            System.out.println("find:查找雇员");
            System.out.println("exit:退出系统");
            key = scanner.next();
            switch (key) {
                case "add":
                    System.out.println("输入id");
                    int id = scanner.nextInt();
                    System.out.println("输入名字");
                    String name = scanner.next();
                    Employee employee = new Employee(id, name);
                    myHashTable.add(employee);
                    break;
                case "list":
                    myHashTable.list();
                    break;
                case "find":
                    System.out.println("请输入要查找的id");
                    id = scanner.nextInt();
                    myHashTable.findEmployeeById(id);
                    break;
                case "exit":
                    scanner.close();
                    System.exit(0);
                default:
                    break;
            }
        }
    }
}

class MyHashTable {
    private EmployeeLinkedList[] employeeLinkedLists;
    private int size;

    public MyHashTable(int size) {
        this.size = size;
        employeeLinkedLists = new EmployeeLinkedList[size];
        for (int i = 0; i < size; i++) {
            employeeLinkedLists[i] = new EmployeeLinkedList();
        }
    }

    public void add(Employee employee) {
        int employeeLinkedListNumber = hashFunction(employee.id);
        employeeLinkedLists[employeeLinkedListNumber].add(employee);
    }

    public void list() {
        for (int i = 0; i < size; i++) {
            employeeLinkedLists[i].list(i);
        }
    }

    public void findEmployeeById(int id) {
        int employeeLinkedListNumber = hashFunction(id);
        Employee employee = employeeLinkedLists[employeeLinkedListNumber].findEmployeeById(id);
        if (employee != null) {
            System.out.printf("在第%d条链表中找到该雇员\n", employeeLinkedListNumber + 1);
        } else {
            System.out.println("没有该雇员");
        }
    }

    public int hashFunction(int id) {
        return id % size;
    }
}

class Employee {
    public int id;
    public String name;
    public Employee next;

    public Employee(int id, String name) {
        this.id = id;
        this.name = name;
    }
}

class EmployeeLinkedList {
    private Employee head;

    public void add(Employee employee) {
        if (head == null) {
            head = employee;
            return;
        }
        Employee curEmployee = head;
        while (true) {
            if (curEmployee.next == null) {
                break;
            }
            curEmployee = curEmployee.next;
        }
        curEmployee.next = employee;
    }

    public void list(int n) {
        if (head == null) {
            System.out.println("第" + n + "条链表为空");
            return;
        }
        System.out.println("第" + n + "条链表的信息为");
        Employee curEmployee = head;
        while (true) {
            System.out.printf("=>id = %d name = %s\t", curEmployee.id, curEmployee.name);
            if (curEmployee.next == null) {
                break;
            }
            curEmployee = curEmployee.next;
        }
        System.out.println();
    }

    public Employee findEmployeeById(int id) {
        if (head == null) {
            System.out.println("链表为空");
            return null;
        }
        Employee curEmployee = head;
        while (true) {
            if (curEmployee.id == id) {
                break;
            }
            if (curEmployee.next == null) {
                curEmployee = null;
            }
            curEmployee = curEmployee.next;
        }
        return curEmployee;
    }
}