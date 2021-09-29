package reflect;

import leetcode.Candy;

/**
 * @author 18190
 * @Date: 2021/8/3  14:54
 * @VERSION 1.0
 */
public class Reflect {

    private String name;
    private Integer age;
    private Candy candy = new Candy();

    public Reflect(){

    }

    private Reflect(String name){
        this.name = name;
    }
    public Reflect(String name,int age){
        this.name = name;
        this.age = age;
    }

    public Reflect(Candy candy) {
        this.candy = candy;
    }


    @Override
    public String toString() {
        return "Reflect{" +
                "name='" + name + '\'' +
                ", age=" + age +
                ", candy=" + candy +
                '}';
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public Candy getCandy() {
        return candy;
    }

    public void setCandy(Candy candy) {
        this.candy = candy;
    }

    public void hhh(String h,Integer f,String t){
        System.out.println(h + " " + f + " "+ t);

    }
}
