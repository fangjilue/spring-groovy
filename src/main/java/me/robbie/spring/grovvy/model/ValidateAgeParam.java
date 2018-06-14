package me.robbie.spring.grovvy.model;

import java.io.Serializable;

/**
 * <一句话功能简述>
 * <功能详细描述>
 *
 * @auth:闻西
 * @see: [相关类/方法]
 * @date 2018/6/13 下午6:12
 * @since [产品/模块版本]
 */
public class ValidateAgeParam implements Serializable {
    private static final long serialVersionUID = -288965000127823346L;

    private int age;

    private String name;

    public ValidateAgeParam() {
    }

    public ValidateAgeParam(int age, String name) {
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
}
