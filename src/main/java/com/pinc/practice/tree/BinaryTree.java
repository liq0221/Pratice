package com.pinc.practice.tree;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;
import java.util.concurrent.LinkedBlockingQueue;

public class BinaryTree {

    /**
     * 生成二叉树
     * @param linkedList
     * @return
     */
    public TreeNode createTree(LinkedList<Integer> linkedList) {
        if (linkedList == null || linkedList.isEmpty()) {
            return null;
        }
        Integer data = linkedList.removeFirst();
        TreeNode treeNode = null;
        if (data != null) {
            treeNode = new TreeNode(data);
            treeNode.left = createTree(linkedList);
            treeNode.right = createTree(linkedList);
        }
        return treeNode;
    }

    /**
     * 前序遍历（递归实现）
     * @param treeNode
     */
    public void preOrderTravel_recursion(TreeNode treeNode) {

        if (treeNode == null) {
            return;
        }
        System.out.println(treeNode.data);
        preOrderTravel_recursion(treeNode.left);
        preOrderTravel_recursion(treeNode.right);
    }

    /**
     * 前序遍历(栈实现)
     * @param treeNode
     */
    public void preOrderTravel_stack(TreeNode treeNode) {
        Stack<TreeNode> stack = new Stack<>();
        TreeNode root = treeNode;
        while (root != null || !stack.isEmpty()) {
             while (root != null) {
                 System.out.println(root.data);
                 stack.push(root);
                 root = root.left;
             }

            if (!stack.isEmpty()) {
                root = stack.pop();
                root = root.right;
            }
        }
    }

    /**
     * 中序遍历（递归实现）
     * @param treeNode
     */
    public void midOrderTravel_recursion(TreeNode treeNode) {

        if (treeNode == null) {
            return;
        }
        midOrderTravel_recursion(treeNode.left);
        System.out.println(treeNode.data);
        midOrderTravel_recursion(treeNode.right);
    }

    /**
     * 中序遍历(栈实现)
     * @param treeNode
     */
    public void midOrderTravel_stack(TreeNode treeNode) {
        Stack<TreeNode> stack = new Stack<>();
        TreeNode root = treeNode;
        while (root != null || !stack.isEmpty()) {
            while (root != null) {
                stack.push(root);
                root = root.left;
            }
            if (!stack.isEmpty()) {
                root = stack.pop();
                System.out.println(root.data);
                root = root.right;
            }
        }

    }

    /**
     * 后序遍历（递归实现）
     * @param treeNode
     */
    public void postOrderTravel_recursion(TreeNode treeNode) {

        if (treeNode == null) {
            return;
        }
        postOrderTravel_recursion(treeNode.left);
        postOrderTravel_recursion(treeNode.right);
        System.out.println(treeNode.data);
    }

    /**
     * 后序遍历(栈实现)
     * @param treeNode
     */
    public void postOrderTravel_stack(TreeNode treeNode) {
        Stack<TreeNode> stack1 = new Stack<>();
        Stack<TreeNode> stack2 = new Stack<>();
        TreeNode root = treeNode;
        while (root != null || !stack1.isEmpty()) {
            while (root != null) {
                stack1.push(root);
                stack2.push(root);
                root = root.right;
            }
            if (!stack1.isEmpty()) {
                root = stack1.pop();
                root = root.left;
            }
        }
        while (!stack2.isEmpty()) {
            root = stack2.pop();
            System.out.println(root.data);
        }
    }

    /**
     * 层序遍历(队列实现)
     * @param treeNode
     */
    public void levelOrderTravel_queue(TreeNode treeNode) {
        Queue<TreeNode> queue = new LinkedBlockingQueue<>();
        queue.offer(treeNode);
        while (!queue.isEmpty()) {
            TreeNode node = queue.poll();
            System.out.println(node.data);
            if (node.left != null) {
                queue.offer(node.left);
            }
            if (node.right != null) {
                queue.offer(node.right);
            }
        }

    }

    public static class TreeNode {

        private int data;

        private TreeNode left;

        private TreeNode right;

        public TreeNode(int data) {
            this.data = data;
        }


    }
}
