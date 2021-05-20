package org.yju.myapplication.data;

import lombok.Data;

@Data
public class RequestReply {
    private char cat_cd;
    private String b_dtt;
    private String r_content;
    private String r_writer;

    @Override
    public String toString() {
        return "RequestReply{" +
                "cat_cd=" + cat_cd +
                ", b_dtt='" + b_dtt + '\'' +
                ", r_content='" + r_content + '\'' +
                ", r_writer='" + r_writer + '\'' +
                '}';
    }

    public char getCat_cd() {
        return cat_cd;
    }

    public void setCat_cd(char cat_cd) {
        this.cat_cd = cat_cd;
    }

    public String getB_dtt() {
        return b_dtt;
    }

    public void setB_dtt(String b_dtt) {
        this.b_dtt = b_dtt;
    }

    public String getR_content() {
        return r_content;
    }

    public void setR_content(String r_content) {
        this.r_content = r_content;
    }

    public String getR_writer() {
        return r_writer;
    }

    public void setR_writer(String r_writer) {
        this.r_writer = r_writer;
    }
}
