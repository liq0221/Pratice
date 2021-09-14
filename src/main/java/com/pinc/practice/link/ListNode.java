package com.pinc.practice.link;

public class ListNode {

    private Node head;

    private Node last;

    private int size;

    public void insert(int data, int index) {
        if (index < 0 || index > size) {
            throw new RuntimeException("位置超出范围");
        }
        Node insertNode = new Node(data);
        if (size == 0) {
            head =  insertNode;
            last = insertNode;
        } else if (index == size) {
            last.next = insertNode;
            last = insertNode;
        } else {
            Node preNode = get(index - 1);
            Node nextNode = preNode.next;
            preNode.next = insertNode;
            insertNode.next = nextNode;
        }
        size++;
    }
    public Node remove(int index) {

        Node temp = head;
        if (index == 0) {
            head = head.next;
        } else if (size == index) {
            Node newLastNode = get(size - 1);
            newLastNode.next = null;
            temp = last;
        } else {
            Node preNode = get(index - 1);
            Node deleteNode = preNode.next;
            Node nextNode = deleteNode.next;
            preNode.next = nextNode;
            temp = deleteNode;
        }
        size--;
        return temp;

    }

    private Node get(int index) {
        int i = 0;
        Node temp = head;
        while (i < index) {
            temp = temp.next;
            i++;
        }
        return temp;
    }

    public void output() {
        Node temp = head;
        while (temp != null) {
            System.out.println(temp.data);
            temp = temp.next;
        }
        System.out.println(size);
    }

    private static class Node {

        private int data;

        private Node next;

        public Node(int data) {
            this.data = data;
        }




    }
}
