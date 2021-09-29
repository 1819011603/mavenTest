package leetcode;

import org.junit.Test;

/**
 * @author 18190
 * @Date: 2021/6/18  14:35
 * @VERSION 1.0
 */
public class Candy {
    private int i;
    private String s;


    @Override
    public String toString() {
        return "Candy{" +
                "i=" + i +
                ", s='" + s + '\'' +
                '}';
    }

    public int getI() {
        return i;
    }

    public String getS() {
        return s;
    }

    public void setI(int i) {
        this.i = i;
    }

    public void setS(String s) {
        this.s = s;
    }

    public Candy(){
        i = 0;
        s = "zzl";
        System.out.println("Candy init");
    }


    public void candy(){
        System.out.println("candy");

    }

    public String apple(){
        System.out.println("apple");
        return "apple";

    }

    public int eggs(){
        System.out.println("eggs");
        return 4;
    }
}
