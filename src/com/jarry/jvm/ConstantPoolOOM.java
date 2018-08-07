package com.jarry.jvm;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by jarry on 2018/6/13.
 *
 * 1.8以后已经无永久代Perm,且常量放到了heap堆中，无法通过设置 -XX:PermSize=10m -XX:MaxPermSize=10M 来测试方法区溢出
 * 所以这测试案例只会抛出java.lang.OutOfMemoryError: Java heap space
 *
 */
public class ConstantPoolOOM {

    static String base = "string";

    public static void main(String[] args) {
        List<String> list = new ArrayList<String>();
        for (int i = 0; i < Integer.MAX_VALUE; i++) {
            String str = base + base;
            base = str;
            list.add(str.intern());
        }
    }

}
