package org.yju.myapplication.data;

import lombok.Data;

import java.util.Date;

@Data
public class Answer {
    private String q_cd;
    private String ans_content;
    private String ans_dt;
    private String ans_writer;

    @Override
    public String toString() {
        return "Answer{" +
                "q_cd='" + q_cd + '\'' +
                ", ans_content='" + ans_content + '\'' +
                ", ans_dt='" + ans_dt + '\'' +
                ", ans_writer='" + ans_writer + '\'' +
                '}';
    }

    public String getQ_cd() {
        return q_cd;
    }

    public void setQ_cd(String q_cd) {
        this.q_cd = q_cd;
    }

    public String getAns_content() {
        return ans_content;
    }

    public void setAns_content(String ans_content) {
        this.ans_content = ans_content;
    }

    public String getAns_dt() {
        return ans_dt;
    }

    public void setAns_dt(String ans_dt) {
        this.ans_dt = ans_dt;
    }

    public String getAns_writer() {
        return ans_writer;
    }

    public void setAns_writer(String ans_writer) {
        this.ans_writer = ans_writer;
    }
}
