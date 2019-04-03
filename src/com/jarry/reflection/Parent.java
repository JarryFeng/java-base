package com.jarry.reflection;

import java.util.Objects;

/**
 * Created by jarry on 2018/7/25.
 */
public class Parent {

    private String name;
    private int age;

    public Parent() {
        System.out.println("父类被初始化");
    }

    private String getName() {
        return name;
    }

    private void setName(String name) {
        this.name = name;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Parent)) return false;
        Parent parent = (Parent) o;
        return age == parent.age &&
                Objects.equals(name, parent.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, age);
    }
}
