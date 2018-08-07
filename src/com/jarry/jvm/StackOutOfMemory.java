package com.jarry.jvm;

import java.util.ArrayList;
import java.util.List;

/**
 * https://blog.csdn.net/renfufei/article/details/53432995
 * Created by jarry on 2018/6/7.
 * 栈帧溢出 设置Xss108K
 * 栈内存溢出无法测试：只有栈帧无法创建的时候，没有足够内存再分配更多栈帧的时候才会内存溢出
 */
public class StackOutOfMemory {

    static int dept = 0 ;

    //栈帧溢出
    private void stackMethod(){
        dept ++;

        stackMethod();
    }

    public static void main(String[] args) {
        StackOutOfMemory stackOutOfMemory = new StackOutOfMemory();
        try {
            stackOutOfMemory.stackMethod();
        }finally {
            System.out.println("栈深:" + dept);
        }

        //栈内存溢出
        //stackOutOfMemory.oomMethod();
    }

    public void oomMethod(){
        while(true){
            new Thread(new Runnable() {
                @Override
                public void run() {
                    loopMethod();
                }
            }).start();;
        }
    }

    private void loopMethod(){
        while(true){

        }
    }
}

