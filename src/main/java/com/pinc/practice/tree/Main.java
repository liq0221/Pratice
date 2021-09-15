package com.pinc.practice.tree;

import java.util.Arrays;
import java.util.LinkedList;

public class Main {

    public static void main(String[] args) {
        // new Integer[]{3,2,9,null,null,10,null,null,8,null,4,}
        LinkedList<Integer> inputList = new LinkedList<Integer>(
                Arrays.asList(new Integer[]{3,2,9,null,null,10,null,null,8,null,4,}));
        BinaryTree binaryTree = new BinaryTree();
        BinaryTree.TreeNode treeNode = binaryTree.createTree(inputList);
        System.out.println("=======前序遍历(递归实现)=======");
        binaryTree.preOrderTravel_recursion(treeNode);
        System.out.println("=======中序遍历(递归实现)=======");
        binaryTree.midOrderTravel_recursion(treeNode);
        System.out.println("=======后序遍历(递归实现)=======");
        binaryTree.postOrderTravel_recursion(treeNode);
        System.out.println("=======前序遍历(栈实现)=======");
        binaryTree.preOrderTravel_stack(treeNode);
        System.out.println("=======中序遍历(栈实现)=======");
        binaryTree.midOrderTravel_stack(treeNode);
        System.out.println("=======后序遍历(栈实现)=======");
        binaryTree.postOrderTravel_stack(treeNode);
    }
}
