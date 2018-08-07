package com.jarry.reflection;

/**
 * Created by jarry on 2018/7/25.
 */
public class Parent {

    private String name;

    public Parent() {
        System.out.println("父类被初始化");
    }

    private String getName() {
        return name;
    }

    private void setName(String name) {
        this.name = name;
    }
}
