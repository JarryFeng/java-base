package com.jarry.jvm;

import java.io.File;
import java.net.URL;
import java.net.URLClassLoader;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by jarry on 2018/6/13.
 * <p>
 * 1.8元数据空间内存溢出
 * <p>
 * -XX:MetaspaceSize=3M -XX:MaxMetaspaceSize=3M
 */
public class MetaspaceOOM {
    public static void main(String[] args) {
        URL url = null;
        List<ClassLoader> classLoaderList = new ArrayList<ClassLoader>();
        try

        {
            url = new File("/tmp").toURI().toURL();
            URL[] urls = {url};
            while (true) {
                ClassLoader loader = new URLClassLoader(urls);
                classLoaderList.add(loader);
                loader.loadClass("com.jarry.jvm.MetaspaceTest");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}


class MetaspaceTest {

}