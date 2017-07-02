package com.xdShop.rest.service;

public class Main{
    static class TreeNode{
        TreeNode left;
        TreeNode right;
    }
    //测试数据说明都木有搞个锤子
    public static void main(String[] args){
//        Scanner sc=new Scanner(System.in);

    }

    public static TreeNode getParentOfTwoNode(TreeNode first,TreeNode second,TreeNode root){
        if(root==null)
            return null;

        boolean leftContain,leftContain2;
        leftContain=contains(root.left,first);
        leftContain2=contains(root.left,second);
        //左孩子包含两个节点 递归
        if(leftContain&&leftContain2)
            return getParentOfTwoNode(first,second,root.left);
        //左孩子不包含两个节点
        if(!leftContain&&!leftContain2)
            return getParentOfTwoNode(first,second,root.right);
        //左孩子包含first 判断右孩子是否包含second
        if(leftContain&&contains(root.right,second))
            return root;
        //左孩子包含second 判断右孩子是否包含first
        if(leftContain2&&contains(root.right,first))
            return root;
        return null;
    }

    public static boolean contains(TreeNode root,TreeNode node ){
        if(root==null)
            return false;
        if(root==node)
            return true;
        return contains(root.left,node)||contains(root.right,node);
    }
}
