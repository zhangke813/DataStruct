package com.study.array;

public class DynamicArray<E> {
    //动态数组
    //实现基本思想(定长数组实现动态数组的)：
    //1、实现过程：首先先申请一个定长的数组空间Original，定义一个int型的数据len，记录当前已使用的空间大小，
    //   也就是动态数组的长度。然后在每次涉及到增加元素的操作中对当前动态数组的长度和已开辟的空间大小进行比
    //   较。如果len>=length，那么我们就新开辟一个大小为Original.length()*2的新定长数组，先把原来数组的信
    //   息赋值到这个新的数组中，再增加新的数据。如果len<length,我们可以直接在原来的定长数组中添加新数据。
    /* 2.  动态数组需要实现哪些功能
            1.动态添加add方法；
            2.获取任意位置的数据get方法；
            3.返回当前动态数组的长度；
            4.在任意位置插入数据的insert方法；
            5.删除任意位置数据的delete方法；
            6.合并其他数组的unite方法；
    这里的话，前三个方法是必须要有的，后三个可有可无，根据具体的题目来决定。如果还需要其他方法可以自行增加。*/
    //申请一个数组，初始定长是100
    Object[] Original;
    private int startLength=100;
    int len=0; //当前长度
    //不给数组初始长度，使用默认初始长度
    public DynamicArray(){
        Original=new Object[startLength];
    }
    //给定初始数组长度
    public DynamicArray(int startLength){
        this.startLength=startLength;
    }
    //添加数据的方法
    public void  add(E o){
        //当前长度和数组可用长度相等
        if(len==Original.length) {
            //将数组长度扩展一倍
            Object[] newArray = new Object[Original.length * 2];
            //保存原来的值
            for (int i = 0; i < len; i++) {
                newArray[i] = Original[i];
            }
            newArray[len] = o;
            //把原来数组指向改变后的数组位置
            Original = newArray;
        }
        else Original[len]=o;
        //当前元素个数加1
        len++;
    }
    //返回数组的长度
    public int size() {
        return len;
    }
    //获取数据的方法
    public Object get(int index) {
        return Original[index];
    }
    //插入数据的方法
    public void insert(E o,int position){
        len++;
        if(len==Original.length){
            Object[] newArray=new Object[Original.length*2];
            for(int i=0;i<position;i++)
                newArray[i]=Original[i];
            for(int i = position+1; i <len ; i++)
                newArray[i]=Original[i-1];
            newArray[position]=0;
            //把原来数组指向改变后的数组位置
            Original=newArray;
        }else {
            for (int i = position+1; i <len ; i++)
                Original[i]=Original[i-1];
            Original[position]=0;
        }
    }
    //删除数据的方法
    public Object delete(int index) {
        //长度减少1
        len--;
        //保存要删除的元素
        Object element=Original[index];
        //从index开始就需要从后往前替换
        for(int i=index;i<len;i++)
            Original[i]=Original[i+1];
        return element;
    }
    //合并的另一个数组
    public void unite(DynamicArray dArray){
        //判断两个数组长度之和是否比当前定长数组长度大
        if (dArray.size()+len >Original.length) {
            Object[] newArray=new Object[(dArray.size()+len)*2];
            for (int i = 0; i <len ; i++)
                newArray[i]=Original[i];
            for(int i=len;i<(len+dArray.size());i++)
                newArray[i]=dArray.get(i);
            Original=newArray;
        }else {
            for (int i = len; i <(len+dArray.size()) ; i++)
                Original[i]=dArray.get(i);
        }
    }
    //定义一个测试类
    public static class Test {

        public int data;

        public Test(int data) {
            this.data=data;
        }

        public static void main(String[] args) {
            Test t1=new Test(1);
            Test t2=new Test(2);
            Test t3=new Test(3);

            DynamicArray dynamicArray=new DynamicArray();
            dynamicArray.add(t1);
            dynamicArray.add(t2);
            dynamicArray.add(3);
            dynamicArray.insert(t3, 1);
            dynamicArray.delete(0);

            DynamicArray<Test>dynamicArray2=new DynamicArray();
            dynamicArray2.add(t1);
            //dynamicArray2.add(3);//这个方法会报错，因为指定了泛型的具体数据类型

            dynamicArray.unite(dynamicArray2);

            int len=dynamicArray.size();
            System.out.println("length="+len);
            for(int i=0;i<dynamicArray.size();i++) {
                //这里需要强制转型
                Object x=dynamicArray.get(i);
                Test changex=(Test)x;
                System.out.println(changex.data);
            }


        }
    }
}

