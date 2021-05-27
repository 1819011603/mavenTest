package Jdbc.utils;


import java.sql.Date;
import java.time.LocalDateTime;

public class Actor {// javaBean
    private Integer id;
    private String sex;

    // mysql 中我傻逼了 用了dataTime  actor1用的date
    private LocalDateTime borndate;
    private String phone;
    private String NAME;
    public Actor(){

    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNAME() {
        return NAME;
    }

    public void setNAME(String NAME) {
        this.NAME = NAME;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public LocalDateTime getBorndate() {
        return borndate;
    }

    public void setBorndate(LocalDateTime borndate) {
        this.borndate = borndate;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    @Override
    public String toString() {
        return "Actor{" +
                "id=" + id +
                ", NAME='" + NAME + '\'' +
                ", sex='" + sex + '\'' +
                ", borndate=" + borndate +
                ", phone='" + phone + '\'' +
                '}';
    }
}
