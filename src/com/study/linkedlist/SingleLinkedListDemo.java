package com.study.linkedlist;

public class SingleLinkedListDemo {
    public static void  main (String[] args){
        //进行测试
        //先创建节点
        HeroNode hero1=new HeroNode(1,"大娃","大力娃");
        HeroNode hero2=new HeroNode(2,"二娃","耳眼娃");
        HeroNode hero3=new HeroNode(3,"三娃","铁头娃");
        HeroNode hero4=new HeroNode(4,"四娃","喷火娃");
        HeroNode hero5=new HeroNode(5,"五娃","喷水娃");
        HeroNode hero6=new HeroNode(6,"六娃","隐身娃");
        //创建链表
        SingleLinkedList singleLinkedList= new SingleLinkedList();
        //加入
        singleLinkedList.add(hero1);
        singleLinkedList.add(hero3);
        singleLinkedList.add(hero5);
        singleLinkedList.list();
        //按编号
        singleLinkedList.addByOrder(hero2);
        singleLinkedList.addByOrder(hero4);
        singleLinkedList.addByOrder(hero6);
        //显示一把
        singleLinkedList.list();

        //测试修改节点de代码
        HeroNode newHeroNode=new HeroNode(6,"七娃","葫芦娃");
        singleLinkedList.update(newHeroNode);
        System.out.println("修改后。。。");
        singleLinkedList.list();

        //删除
        singleLinkedList.del(2);
        singleLinkedList.del(4);
        System.out.println("删除后。。。");
        singleLinkedList.list();


    }
}
//定义SingleLinkedList管理我们的英雄
class SingleLinkedList{
    //先初始化一个头节点，头节点不要动，不存放具体数据
    private HeroNode head=new HeroNode(0,"","");

    //添加节点到单项列表
    //思路：不考虑编号顺序
    //1.找到当前链表的最后节点
    //2.将最后节点的next指向新的节点
    public void add (HeroNode heroNode){
        //因为head节点不能动，因此我们需要一个辅助遍历temp
        HeroNode temp=head;
        //便利链表，找到最后
        while(true) {
            //找到链表的最后
            if (temp.next == null) {
                break;
            }
            //如果没有找到最后i，将temp后移
            temp = temp.next;
        }
            //当退出while循环时，temp就指向了链表的最后
            //将最后这个节点的next指向新的节点
            temp.next=heroNode;

    }
    //第二种方式在添加英雄时，根据排名将英雄插入到指定位置
    //（如果有这个排名，则添加失败，并给出提示）
    public void addByOrder(HeroNode heroNode){
        //头节点不能动，因此我们仍然通过一个辅助指针来帮我们找到指定位置
        //因为单链表，因为我们找到的temp是位于添加位置的前一个节点，否则插入不了
        HeroNode temp=head;
        boolean flag=false;
        while(true){
            if(temp.next==null){
                //temp已经在链表的最后
                break;
            }
            if(temp.next.no>heroNode.no){
                //位置找到，就在temp后面插入
                break;
            }else if(temp.next.no==heroNode.no){
                //说明希望添加的编号已经存在
                flag=true;
                break;
            }
            temp=temp.next;
        }
        if(flag){
            System.out.printf("准备插入的娃娃%d已经存在，不能加入\n",heroNode.no);
        }else {
            //插入链表中，temp的后面
            heroNode.next=temp.next;
            temp.next=heroNode;
        }
    }
    //修改节点的信息。根据 no编号来修改即可，即no编号不能改
    //说明
    //1.根据newHeroNode的no来修改即可
    public void update(HeroNode newHeroNode){
        //判断是否为空
        if(head.next==null){
            System.out.println("链表为空");
            return;
        }
        //找到需要修改的节点，根据no编号
        //定义 一个辅助变量
        HeroNode temp=head.next;
        boolean flag=false;//表示是否找到该节点
        while (true){
            if(temp==null){
                break;//已经遍历完链表
            }
            if(temp.no==newHeroNode.no){
                //找到
                flag=true;
                break;
            }
            temp=temp.next;
        }
        //根据flag判断是否找到要修改节点
        if (flag){
            temp.name=newHeroNode.name;
            temp.nickname=newHeroNode.nickname;
        }else{
            System.out.printf("没找到编号%d的节点，不能修改\n",newHeroNode.no);
        }
    }
    //删除节点
    //1.head不能动，需要temp辅助节点找到待删除节点的前一个节点
    //2.说明我们在比较时。是temp.next.no和需要删除的节点的no之间进行比较
    public void del(int no){
        HeroNode temp=head;
        boolean flag=false;
        while (true){
            //链表已经到最后
            if(temp.next==null){
                break;
            }
            if(temp.next.no==no){
                //找到的待删除节点的前一个节点temp
                flag=true;
                break;

            }
            temp=temp.next;//temp后移遍历
        }
        //判断flag
        if(flag){
            //找到，可以删除
            temp.next=temp.next.next;
        }else {
            System.out.printf("要删除的节点%d节点不存在i\n",no);
        }
    }
    //显示链表【遍历】
    public void list(){
        //判断链表是否为空
        if(head.next==null){
            System.out.println("链表为空");
            return;
        }
        //头节点不能动，需要辅助变量来便历
        HeroNode temp=head.next;
        while (true){
            //判断是否到最后
            if(temp==null){
                break;
            }
            //输出节点的信息
            System.out.println(temp);
            temp=temp.next;
        }
    }
}
class HeroNode{
    public int no;
    public String name;
    public String nickname;
    public HeroNode next;//指向下一个节点
    //构造器
    public  HeroNode(int no,String name,String nickname){
        this.no=no;
        this.name=name;
        this.nickname=nickname;
    }
    //为了显示方便，重写toString；
    @Override
    public String toString() {
        return "HeroNode{" +
                "no=" + no +
                ", name='" + name + '\'' +
                ", nickname='" + nickname + '\'' +
                ", next=" + next +
                '}';
    }
}