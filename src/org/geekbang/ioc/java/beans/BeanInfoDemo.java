package org.geekbang.ioc.java.beans;

import java.beans.BeanInfo;
import java.beans.IntrospectionException;
import java.beans.Introspector;
import java.beans.PropertyEditorSupport;
import java.util.stream.Stream;

/*
* {@link java.beans.BeanInfo}通过示例了解基本使用
*
*
* */
public class BeanInfoDemo {
    public static void main(String[] args) throws IntrospectionException {

        BeanInfo beanInfo = Introspector.getBeanInfo(Person.class, Object.class);

//        Stream.of(beanInfo.getPropertyDescriptors())
//                .forEach(propertyDescriptor -> {
//                    System.out.println(propertyDescriptor);
//                });

/*
*
* BeanInfo beanInfo = Introspector.getBeanInfo(Person.class);
java.beans.PropertyDescriptor[name=age; propertyType=class java.lang.Integer; readMethod=public java.lang.Integer org.geekbang.ioc.java.beans.Person.getAge(); writeMethod=public void org.geekbang.ioc.java.beans.Person.setAge(java.lang.Integer)]
java.beans.PropertyDescriptor[name=class; propertyType=class java.lang.Class; readMethod=public final native java.lang.Class java.lang.Object.getClass()]
java.beans.PropertyDescriptor[name=name; propertyType=class java.lang.String; readMethod=public java.lang.String org.geekbang.ioc.java.beans.Person.getName(); writeMethod=public void org.geekbang.ioc.java.beans.Person.setName(java.lang.String)]

*不输出class作为属性。为了只输出当前层次的类的属性，可以第二个参数设置为父类，此处person父类是object
* BeanInfo beanInfo = Introspector.getBeanInfo(Person.class, Object.class);
java.beans.PropertyDescriptor[name=age; propertyType=class java.lang.Integer; readMethod=public java.lang.Integer org.geekbang.ioc.java.beans.Person.getAge(); writeMethod=public void org.geekbang.ioc.java.beans.Person.setAge(java.lang.Integer)]
java.beans.PropertyDescriptor[name=name; propertyType=class java.lang.String; readMethod=public java.lang.String org.geekbang.ioc.java.beans.Person.getName(); writeMethod=public void org.geekbang.ioc.java.beans.Person.setName(java.lang.String)]

*
* */

        Stream.of(beanInfo.getPropertyDescriptors())
                .forEach(propertyDescriptor -> {

                    //PropertyDescriptor 允许添加属性编辑器 - PropertyEditor
                    //GUI -> text(String) ->PropertyType
                    //name -> String
                    //age -> Integer 类型转换
                    //面向元信息
                    Class<?> propertyType = propertyDescriptor.getPropertyType();
                    String propertyName = propertyDescriptor.getName();
                    if("age".equals(propertyName)){// 为"age"字段/属性增加PropertyEditor
                        //String -> Integer
//                        Integer.valueOf("")
                        propertyDescriptor.setPropertyEditorClass(StringToIntegerPropertyEditor.class);
                        //propertyDescriptor.createPropertyEditor()放入bean
                    }
                });
    }

    static class StringToIntegerPropertyEditor extends PropertyEditorSupport{
        //重写（拦截）PropertyEditorSupport重的setAsText
        public void setAsText(String text) throws java.lang.IllegalArgumentException {
            Integer value = Integer.valueOf(text);
            setValue(value);
        }
    }

}
