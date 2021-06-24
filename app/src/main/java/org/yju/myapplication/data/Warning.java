package org.yju.myapplication.data;

import lombok.Data;

import java.util.Date;

@Data
public class Warning {
    private String w_dt; // 경고일
    private String u_id; // 아이디 (아이디 15Byte + 권한 1Byte)
    private char w_cat; // 경고분류 (0: 예약, 1: 게시판, 2: 댓글)
    private char w_reason; // 경고사유 (0 : 욕설, 1 : 도배, 2 : 광고, 3 : 부적절, 9: 기타)
    private String w_content; // 경고내용

    @Override
    public String toString() {
        return "Warning{" +
                "w_dt='" + w_dt + '\'' +
                ", u_id='" + u_id + '\'' +
                ", w_cat=" + w_cat +
                ", w_reason=" + w_reason +
                ", w_content='" + w_content + '\'' +
                '}';
    }

    public String getW_dt() {
        return w_dt;
    }

    public void setW_dt(String w_dt) {
        this.w_dt = w_dt;
    }

    public String getU_id() {
        return u_id;
    }

    public void setU_id(String u_id) {
        this.u_id = u_id;
    }

    public char getW_cat() {
        return w_cat;
    }

    public void setW_cat(char w_cat) {
        this.w_cat = w_cat;
    }

    public char getW_reason() {
        return w_reason;
    }

    public void setW_reason(char w_reason) {
        this.w_reason = w_reason;
    }

    public String getW_content() {
        return w_content;
    }

    public void setW_content(String w_content) {
        this.w_content = w_content;
    }
}