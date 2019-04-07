package com.jarry.introspection;


import java.sql.Date;

public class IntrospectionObject {

    private int age;

    private String name;

    private boolean sex;

    private Date brithday = new Date(1);


    public Date getBrithday() {
        return brithday;
    }

    public void setBrithday(Date brithday) {
        this.brithday = brithday;
    }

    public IntrospectionObject(int age, String name) {
        this.age = age;
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

     public boolean isSex() {
         return sex;
     }

     public void setSex(boolean sex) {
         this.sex = sex;
     }
 }
