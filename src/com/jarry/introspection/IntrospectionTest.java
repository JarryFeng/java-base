package com.jarry.introspection;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.beanutils.MethodUtils;
import org.apache.commons.beanutils.PropertyUtils;

import java.beans.BeanInfo;
import java.beans.IntrospectionException;
import java.beans.Introspector;
import java.beans.PropertyDescriptor;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Map;

/**
 * @author fengzheng
 * @date 2019/4/3
 * @describe JDK中提供了对JavaBean进行操作的一些API，这套API就称为内省
 * 该API是对反射进行的一层封装，简便操作，内省主要用于框架，框架是面向抽象编程的，最简单的例子就是 apache的BeanUtils类，进行对象属性的拷贝。
 */
public class IntrospectionTest {
    public static void main(String[] args) throws IntrospectionException, InvocationTargetException, IllegalAccessException, NoSuchMethodException {
        IntrospectionObject obj = new IntrospectionObject(10, "张三");


        String propertyName = "age";

        //获取属性描述信息
        PropertyDescriptor propertyDescriptor = new PropertyDescriptor(propertyName, obj.getClass());
        //得到对应的get方法
        Method readMethod = propertyDescriptor.getReadMethod();
        //通过反射获得get方法返回的值
        Object returnValue = readMethod.invoke(obj);
        System.out.println("修改前的值:" + returnValue);

        //获得对应的set方法
        Method writeMethod = propertyDescriptor.getWriteMethod();
        //通过反射，将age的值设置为8
        writeMethod.invoke(obj, 8);
        //查看是否更改成功
        System.out.println("修改后的值:" + obj.getAge());



        //稍微复杂的内省操作,BeanInfo包含了对象的MethodDescriptor、PropertyDescriptor、BeanDescriptor
        BeanInfo beanInfo = Introspector.getBeanInfo(obj.getClass());
        //获取对象的所有属性描述
        PropertyDescriptor[] propertyDescriptors = beanInfo.getPropertyDescriptors();
        for (PropertyDescriptor p: propertyDescriptors) {
            //获取属性名 p.getName()
            if(p.getName().equals(propertyName)){
                Method writeMethod1 = p.getWriteMethod();
                writeMethod1.invoke(obj, 20);
                System.out.println("最新修改后的值:" + obj.getAge());
                break;
            }
        }


        //使用apache的BeanUtils获取对象的属性/设置对象的属性
        //TODO 这边有个坑，就是BeanUtils只能获取public修饰的类
        String name = BeanUtils.getProperty(obj, "sex");//获取boolean
        System.out.println(name);

        //设置
        BeanUtils.setProperty(obj, "brithday", "2019-04-04");
        System.out.println(obj.getBrithday());

        //可以设置对象中的对象的属性值， 例如 obj对象下的Date对象下的time属性
        BeanUtils.setProperty(obj, "brithday.time", "9999999999999999999999");
        System.out.println(obj.getBrithday());

        Object sex = PropertyUtils.getProperty(obj, "sex");
        System.out.println(sex);

    }
}


