package com.jarry.io;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

/**
 * @author fengzheng
 * @date 2019/4/3
 * @describe 获取文件所在路径
 */
public class IOPathTest {
    public static void main(String[] args) throws IOException {

        FileInputStream is = new FileInputStream("config.properties2");//默认查找项目文件根目录

        Properties properties = new Properties();
        properties.load(is);
        is.close();

        Object username = properties.get("username2");
        System.out.println(username);


        InputStream is2 = IOPathTest.class.getClassLoader().getResourceAsStream("com/jarry/config.properties");//classpath路径
        Properties properties2 = new Properties();
        properties2.load(is2);
        is2.close();

        Object username2 = properties2.get("username");
        System.out.println(username2);
    }
}
