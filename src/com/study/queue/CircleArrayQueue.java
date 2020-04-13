package com.study.queue;

import java.util.Scanner;

public class CircleArrayQueue {
    //环形队列
    public static void main(String[] args){
        //测试
        //创建一个队列
        CircleArray queue=new CircleArray(4);
        char key =' ';//接收用户输入
        Scanner scanner=new Scanner(System.in);
        boolean loop=true;
        //输出一个菜单
        while(loop){
            System.out.println("s(show):显示队列");
            System.out.println("e(exit):调出程序");
            System.out.println("a(add):添加数据到队列");
            System.out.println("g(get):从队列取出数据");
            System.out.println("h(head):查看队列头的数据");
            key=scanner.next().charAt(0);//接收第一个字符
            switch (key){
                case 's':
                    queue.showQueue();
                    break;
                case 'a':
                    System.out.println("输出一个数");
                    int value=scanner.nextInt();
                    queue.addQueue(value);
                    break;
                case 'g':
                    try {
                        int res=queue.getQueue();
                        System.out.printf("取出的数据是%d\n",res);
                    }catch (Exception e){

                    }
                    break;
                case 'h':
                    try {
                        int res=queue.headQueue();
                        System.out.printf("队列头是%d\n",res);
                    }catch (Exception e){

                    }
                    break;
                case 'e':
                    scanner.close();
                    loop=false;
                    break;
                default:
                    break;
            }
        }
        System.out.println("程序退出");

    }
}
//使用数组模拟队列
class CircleArray{
    //环形队列判断综述（占用一个位置）
    //队列满：(rear+1)%maxSize == front
    //队列空： rear==front;
    //队列 有效数据元素个数：rear+maxSize-front)%maxSize
    private int maxSize;//表示数组最大容量

    //front变量的含义：指向队列的第一个元素，也就是说arr【front】
    //front初始值=0
    private int front;//队列头

    //rear变量的含义：指向队列最后一个元素的后一个位置，因为希望空出 一个位置
    //rear初始值=0
    private int rear;//队列尾
    private int[] arr;//该数组用于存储数据，模拟队列

    //创建队列的构造器
    public CircleArray(int arrMaxSize){
        maxSize=arrMaxSize;
        arr=new int[maxSize];
    }
    //判断队列是否满
    public boolean isFull(){
        return (rear+1)%maxSize == front;
    }
    //判断队列是否为空
    public boolean isEmpty(){
        return rear==front;
    }
    //添加数据到队列
    public void addQueue(int n){
        //判断是否满
        if(isFull()){
            System.out.println("队列满");
        }
        arr[rear]=n;
        //将rear后移，这里考虑取模
        rear=(rear+1)%maxSize;
    }
    //获取队列数据，出队列
    public int getQueue(){
        //判断是否为空
        if(isEmpty()){
            throw new RuntimeException("队列空，不能取数据");
        }
        //这里需要分析出front是指向队列的第一个元素
        //1.先把front对应的值保留到一个临时变量
        //2.将front后移，考虑取模
        //3.将临时保存的变量返回
        int value=arr[front];
        front=(front+1)%maxSize;
        return value;
    }
    //显示队列所有数据
    public void showQueue(){
        //遍历
        if(isEmpty()){
            System.out.println("队列空，没有数据");
            return;
        }
        //从front开始遍历
        for (int i = front; i <front+size() ; i++) {
            System.out.printf("arr[%d]=%d\n ",i%maxSize,arr[i%maxSize]);
        }
    }

    public int headQueue(){
        if (isEmpty()){
            throw  new  RuntimeException("队列空没有数据");
        }
        return arr[front];
    }
    //求出当前队列的有效数据元素个数
    public int size(){
        return (rear+maxSize-front)%maxSize;
    }

}