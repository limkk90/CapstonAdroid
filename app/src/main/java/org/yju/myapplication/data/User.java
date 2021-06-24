package org.yju.myapplication.data;

import java.util.Date;

import lombok.Data;

@Data
public class User {
    private String u_id; // 아이디 (아이디 15 + 권한 1)
    private String u_email; // 이메일
    private String u_pwd; // 비밀번호
    private String u_car;
    private String u_s_car;
    private int u_point;
    private String u_reg_dt; // 가입일

    @Override
    public String toString() {
        return "User{" +
                "u_id='" + u_id + '\'' +
                ", u_email='" + u_email + '\'' +
                ", u_pwd='" + u_pwd + '\'' +
                ", u_car='" + u_car + '\'' +
                ", u_s_car='" + u_s_car + '\'' +
                ", u_point=" + u_point +
                ", u_reg_dt='" + u_reg_dt + '\'' +
                '}';
    }

    public String getU_id() {
        return u_id;
    }

    public void setU_id(String u_id) {
        this.u_id = u_id;
    }

    public String getU_email() {
        return u_email;
    }

    public void setU_email(String u_email) {
        this.u_email = u_email;
    }

    public String getU_pwd() {
        return u_pwd;
    }

    public void setU_pwd(String u_pwd) {
        this.u_pwd = u_pwd;
    }

    public String getU_car() {
        return u_car;
    }

    public void setU_car(String u_car) {
        this.u_car = u_car;
    }

    public String getU_s_car() {
        return u_s_car;
    }

    public void setU_s_car(String u_s_car) {
        this.u_s_car = u_s_car;
    }

    public int getU_point() {
        return u_point;
    }

    public void setU_point(int u_point) {
        this.u_point = u_point;
    }

    public String getU_reg_dt() {
        return u_reg_dt;
    }

    public void setU_reg_dt(String u_reg_dt) {
        this.u_reg_dt = u_reg_dt;
    }
}
