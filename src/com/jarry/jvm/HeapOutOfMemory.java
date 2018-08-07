package com.jarry.jvm;

import java.util.ArrayList;
import java.util.List;

/**
 * https://blog.csdn.net/renfufei/article/details/53432995
 * Created by jarry on 2018/6/7.
 * 堆溢出
 * 前提条件：设置堆内存大小，设置成一样，避免自动扩展
 * -Xms10M
 * -Xmx10M
 *
 */
public class HeapOutOfMemory {
    public static void main(String[] args) {

        /*List<T> list = new ArrayList<T>();
        while(true){
            list.add(new T());
        }*/

        List<byte[]> list = new ArrayList<>();
        int i=0;
        while(true){
            list.add(new byte[2*1024*1024]);
            System.out.println("分配次数："+(++i));
        }

    }
}

class T {

}