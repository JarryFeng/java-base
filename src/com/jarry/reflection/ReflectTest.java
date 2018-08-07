package com.jarry.reflection;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

/**
 * Created by jarry on 2018/7/25.
 */
public class ReflectTest {

    public static void main(String[] args) throws ClassNotFoundException {
        /*
            获取字节码的三种方式：
            1.对象.getClass();
            2.类.class
            3.Class.forName("类路径")
        */
        ReflectTest reflectTest = new ReflectTest();
        Class<? extends ReflectTest> aClass = reflectTest.getClass();

        Class<ReflectTest> reflectTestClass = ReflectTest.class;

        Class<?> forName = Class.forName("com.jarry.reflection.ReflectTest");

        //相等证明字节码只有一份
        System.out.println(aClass == reflectTestClass);
        System.out.println(forName == reflectTestClass);

        doMethod();

    }

    public static void doMethod(){
        Class<User> userClass = User.class;

        System.out.println("===============getDeclaredConstructors获取类中所有的构造方法==============");
        //获取类中所有的构造方法(不管是否私有还是public)
        Constructor<?>[] declaredConstructors = userClass.getDeclaredConstructors();

        for(Constructor constructor : declaredConstructors){
            System.out.println(constructor);
        }
        System.out.println("===============getConstructors获取类中所有public的构造方法==============");
        //获取类中所有public的构造方法
        Constructor<?>[] constructors = userClass.getConstructors();
        for(Constructor constructor : constructors){
            System.out.println(constructor);
        }

        System.out.println("===============getConstructor获取类中指定的public的构造方法==============");
        try {
            Constructor<User> constructor = userClass.getConstructor(String.class, String.class, int.class);
            User user = constructor.newInstance("张三","清华大学",101);
            System.out.println(user.toString());
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }

        System.out.println("===============getDeclaredConstructor获取类中指定的构造方法==============");
        try {
            Constructor<User> constructor = userClass.getDeclaredConstructor();
            //调用private构造函数必须加这一行，否则报错
            constructor.setAccessible(true);
            User user = constructor.newInstance();
            System.out.println(user.toString());
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }

        System.out.println("===============getDeclaredMethods获取当前类中所有的方法==============");
        Method[] declaredMethods = userClass.getDeclaredMethods();
        for(Method method : declaredMethods){
            System.out.println(method);
        }

        System.out.println("===============getMethods获取类中所有public的方法,包括父类的==============");
        Method[] methods = userClass.getMethods();
        for(Method method : methods){
            System.out.println(method);
        }



    }

}
