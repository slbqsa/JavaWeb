package com.onlineshoppingmall.service.model;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

/**
 * id int auto_increment,
 * 	name varchar(64) not null,
 * 	gender tinyint default 0 not null,
 * 	age int not null,
 * 	phonenum varchar(36) not null,
 * 	means varchar(256) not null,
 * 	id_three varchar(64) not null,
 */
public class UserModel {
    private Integer id;
    @NotBlank(message = "用户名不能为空")
    private String name;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Byte getGender() {
        return gender;
    }

    public void setGender(Byte gender) {
        this.gender = gender;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public String getPhonenum() {
        return phonenum;
    }

    public void setPhonenum(String phonenum) {
        this.phonenum = phonenum;
    }

    public String getMeans() {
        return means;
    }

    public void setMeans(String means) {
        this.means = means;
    }

    public String getId_three() {
        return id_three;
    }

    public void setId_three(String id_three) {
        this.id_three = id_three;
    }
@NotNull(message = "性别不能为空")
    private Byte gender;
    @NotNull(message = "年龄不能为空")
    @Min(value = 0,message = "年龄输入有误")
    @Max(value = 200,message = "年龄输入错误")
    private Integer age;
    @NotBlank(message = "手机号不能为空")
    private String phonenum;
    private String means;
    private String id_three;
    @NotBlank(message = "密码不能为空")
    private String encrptPasswd;

    public String getEncrptPasswd() {
        return encrptPasswd;
    }

    public void setEncrptPasswd(String encrptPasswd) {
        this.encrptPasswd = encrptPasswd;
    }
}
