package com.jarry.reflection;

/**
 * Created by jarry on 2018/7/25.
 */
public class User extends Parent {
    private String username;

    private String schoolName;

    private int classNo;

    public User(String username, String schoolName, int classNo) {
        this.username = username;
        this.schoolName = schoolName;
        this.classNo = classNo;
    }

    protected User(String username, String schoolName) {
        this(username, schoolName, 1);
    }

    User(String username) {
        this(username, "清华大学", 1);
    }

    private User() {
        this("张三", "清华大学", 1);
    }


    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getSchoolName() {
        return schoolName;
    }

    public void setSchoolName(String schoolName) {
        this.schoolName = schoolName;
    }

    public int getClassNo() {
        return classNo;
    }

    public void setClassNo(int classNo) {
        this.classNo = classNo;
    }

    @Override
    public String toString() {
        return "User{" +
                "username='" + username + '\'' +
                ", schoolName='" + schoolName + '\'' +
                ", classNo=" + classNo +
                '}';
    }
}
