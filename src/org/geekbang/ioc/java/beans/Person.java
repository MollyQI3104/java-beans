package org.geekbang.ioc.java.beans;

/*
* 描述人的 POJO 类
*
* 普通的类，没有复杂业务逻辑
*
* Setter / Getter方法
* java beans中则是
* 可写方法（Writable）/ 可读方法（Readable）
*
* */
public class Person {

    String name; // java beans里叫做 property
    Integer age;

    public String getName() {
        return name;
    }

    public Integer getAge() {
        return age;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setAge(Integer age) {
        this.age = age;
    }
}
