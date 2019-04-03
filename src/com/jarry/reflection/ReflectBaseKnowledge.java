package com.jarry.reflection;

/**
 * @author fengzheng
 * @date 2019/4/3
 * @describe 反射基础知识
 */
public class ReflectBaseKnowledge {

    public static void main(String[] args) throws ClassNotFoundException {
        //jvm中的字节码只有一份
        String str = "str";
        Class cls1 = str.getClass();
        Class cls2 = String.class;
        Class cls3 = Class.forName("java.lang.String");
        System.out.println(cls1 == cls2);//true
        System.out.println(cls1 == cls3);//true

        //9种基本类型字节码文件
        Class<Byte> byteClass = byte.class;
        Class<Short> shortClass = short.class;
        Class<Integer> integerClass = int.class;
        Class<Long> longClass = long.class;

        Class<Float> floatClass = float.class;
        Class<Double> doubleClass = double.class;

        Class<Character> characterClass = char.class;

        Class<Boolean> booleanClass = boolean.class;

        //Class类方法介绍
        //1.是否基本类型
        System.out.println(cls1.isPrimitive());//false,String是引用类型

        //2.是否数组
        System.out.println(int[].class.isArray());//true

        //3.是否枚举
        System.out.println(EnumTest.class.isEnum());//true,EnumTest的父类是Enum
        System.out.println(Enum.class.isEnum());//false,为什么是false呢， 因为isEnum里面判断this.getSuperclass() == java.lang.Enum.class

    }

    static enum EnumTest{//内部枚举类，默认修饰符 static
        MON,SUN;
    }
}
