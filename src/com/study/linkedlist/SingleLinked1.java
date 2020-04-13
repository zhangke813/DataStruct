package com.study.linkedlist;

public class SingleLinked1 {
    //获取单链表的节点个数
    public static int getLength(HeroNode head){
        if(head.next==null){
            return 0;
        }
        int length=0;
        HeroNode cur =head.next;
        while (cur!=null){
            length++;
            cur=cur.next;
        }
        return  length;
    }

    //查找单链表中的倒数第K个节点
    public  static HeroNode fondLastIndexNode(HeroNode head,int index){
        if(head.next==null){
            return  null;
        }
        int size=getLength(head);
        if(index>size||index<=0){
            return null;
        }
        HeroNode cur=head.next;
        for (int i = 0; i <size-index ; i++) {
            cur=cur.next;
        }
        return cur;
    }
    //单链表反转
    public static void reversetList(HeroNode head){
        if(head.next==null||head.next.next==null){
            return;
        }
        HeroNode cur=head.next;
        HeroNode next=null;
        HeroNode reverseHead=new HeroNode(0,"","");
        while (cur!=null){
            next=cur.next;
            cur.next=reverseHead;
            reverseHead.next=cur;
            cur=next;
        }
        head.next=reverseHead.next;
    }


}
