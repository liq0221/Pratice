package com.pinc.practice.sort;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

public class TriesTree {

    private Node root;

    public static class Node {

        private int pass;

        private int end;

        private Map<Integer, Node> next;

        public Node() {
            this.pass = 0;
            this.end = 0;
            this.next = new HashMap<>();
        }

    }

    public void TriesTree() {
        root = new Node();
    }

    public void insert(String value) {
        if (Objects.isNull(value) || "".equals(value)) {
            return;
        }
        char[] chr = value.toCharArray();
        Node node = root;
        node.pass++;
        int index = 0;
        for (int i = 0; i < chr.length; i++) {
            index = (int) chr[i];
            if (!node.next.containsKey(index)) {
                node.next.put(index, new Node());
            }
            node = node.next.get(index);
            node.pass++;
        }
        node.end++;
    }

    // 寻找某个字符串出现了几次
    public int search(String str) {
        if (Objects.isNull(str) || "".equals(str)) {
            return 0;
        }
        char[] chr = str.toCharArray();
        Node node = root;
        int index = 0;
        for (int i = 0; i < chr.length; i++) {
            index = (int) chr[i];
            if (!node.next.containsKey(index)) {
                return 0;
            }
            node = node.next.get(index);
        }
        return node.end;
    }

    // 寻找以str为前缀的共有多少
    public int preSearch(String str) {
        if (Objects.isNull(str) || "".equals(str)) {
            return 0;
        }
        char[] chr = str.toCharArray();
        Node node = root;
        int index = 0;
        for (int i = 0; i < chr.length; i++) {
            index = (int) chr[i];
            if (!node.next.containsKey(index)) {
                return 0;
            }
            node = node.next.get(index);
        }
        return node.pass;
    }

    public void delete(String str) {
        if (search(str)  != 0) {
            char[] chr = str.toCharArray();
            Node node = root;
            int index = 0;
            node.pass--;
            for (int i = 0; i < chr.length; i++) {
                index = (int) chr[i];
                // 如果路径上的节点pass为0  则该节点下的所有节点断开 不需要再次遍历删除
                if (--node.next.get(index).pass == 0) {
                    node.next.put(index, null);
                    return;
                }
                node = node.next.get(index);
            }
            node.end--;
        }
    }


}
