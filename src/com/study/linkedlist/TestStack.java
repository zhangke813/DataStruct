package com.study.linkedlist;

import java.util.Stack;

public class TestStack {
   //利用栈反向打印链表
    // 演示栈的基本使用
        public static void main(String[] args){
            Stack<String> stack=new Stack<>();
            //入栈
            stack.add("jack");
            stack.add("tom");
            stack.add("smith");
            //出栈
            while (stack.size()>0){
                System.out.println(stack.pop());
            }

        }
        public static void reversePrint(HeroNode head){
            if(head.next==null){
                return;
            }
            Stack<HeroNode> stack=new Stack<HeroNode>();
            HeroNode cur=head.next;
            while (cur!=null){
                stack.push(cur);
                cur=cur.next;
            }
            while (stack.size()>0){
                System.out.println(stack.pop());
            }
        }
}
