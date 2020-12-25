package com.demo.thread;

/**
 * @Author Zhangnana
 * @DATE 2020/12/23 17:58
 * @Version 1.0
 */
public class Postgraduate {
    private Long num;

    private String name;

    private String sex;

    public Long getNum() {
        return num;
    }

    public void setNum(Long num) {
        this.num = num;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }



    public Postgraduate(Long num, String name, String sex) {
        this.num = num;
        this.name = name;
        this.sex = sex;
    }

    public  void printInfo(){
        System.out.println("学号为"+ this.num + "的学生名为"+ this.name + ","+this.sex+"生");
    }

    public static void main(String[] args) {
        Postgraduate postgraduate = new Postgraduate(517021233L,"黄珊","女");
        postgraduate.printInfo();
    }
}
