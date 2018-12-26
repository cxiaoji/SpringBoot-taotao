package com.itxj.bean;

/*
 *  @项目名：  TestFreeMarker
 *  @包名：    com.itxj.bean
 *  @文件名:   User
 *  @创建者:   小吉
 *  @创建时间:  2018/12/4 19:44
 *  @描述：    TODO
 */
public class User {

    private Long id;
    private String username;
    private String adress;

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", username='" + username + '\'' +
                ", adress='" + adress + '\'' +
                '}';
    }

    public User() {
    }

    public User(Long id, String username, String adress) {
        this.id = id;
        this.username = username;
        this.adress = adress;
    }

    public Long getId() {
        return id;
    }

    public String getUsername() {
        return username;
    }

    public String getAdress() {
        return adress;
    }
}
