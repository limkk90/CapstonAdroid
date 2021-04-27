package org.yju.myapplication.data;

import java.time.LocalDateTime;

import lombok.Data;

@Data
public class Board {
    private char cat_cd;
    private LocalDateTime b_dtt;
    private String b_title;
    private String b_content;
    private int b_visite;
    private String u_id;

    @Override
    public String toString() {
        return "Board{" +
                "cat_cd=" + cat_cd +
                ", b_dtt=" + b_dtt +
                ", b_title='" + b_title + '\'' +
                ", b_content='" + b_content + '\'' +
                ", b_visite=" + b_visite +
                ", u_id='" + u_id + '\'' +
                '}';
    }

    public char getCat_cd() {
        return cat_cd;
    }

    public void setCat_cd(char cat_cd) {
        this.cat_cd = cat_cd;
    }

    public LocalDateTime getB_dtt() {
        return b_dtt;
    }

    public void setB_dtt(LocalDateTime b_dtt) {
        this.b_dtt = b_dtt;
    }

    public String getB_title() {
        return b_title;
    }

    public void setB_title(String b_title) {
        this.b_title = b_title;
    }

    public String getB_content() {
        return b_content;
    }

    public void setB_content(String b_content) {
        this.b_content = b_content;
    }

    public int getB_visite() {
        return b_visite;
    }

    public void setB_visite(int b_visite) {
        this.b_visite = b_visite;
    }

    public String getU_id() {
        return u_id;
    }

    public void setU_id(String u_id) {
        this.u_id = u_id;
    }
}
