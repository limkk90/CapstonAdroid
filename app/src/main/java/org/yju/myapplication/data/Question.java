package org.yju.myapplication.data;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.databind.ObjectMapper;

import lombok.Data;

import java.time.LocalDateTime;
import java.util.Map;

@Data
public class Question {
    private String q_dtt;
    private char q_cat;
    private String q_title;
    private String q_content;
    private String u_id;
    private int answerCnt;

    @Override
    public String toString() {
        return "Question{" +
                "q_dtt='" + q_dtt + '\'' +
                ", q_cat=" + q_cat +
                ", q_title='" + q_title + '\'' +
                ", q_content='" + q_content + '\'' +
                ", u_id='" + u_id + '\'' +
                ", answerCnt=" + answerCnt +
                '}';
    }

    public String getQ_dtt() {
        return q_dtt;
    }

    public void setQ_dtt(String q_dtt) {
        this.q_dtt = q_dtt;
    }

    public char getQ_cat() {
        return q_cat;
    }

    public void setQ_cat(char q_cat) {
        this.q_cat = q_cat;
    }

    public String getQ_title() {
        return q_title;
    }

    public void setQ_title(String q_title) {
        this.q_title = q_title;
    }

    public String getQ_content() {
        return q_content;
    }

    public void setQ_content(String q_content) {
        this.q_content = q_content;
    }

    public String getU_id() {
        return u_id;
    }

    public void setU_id(String u_id) {
        this.u_id = u_id;
    }

    public int getAnswerCnt() {
        return answerCnt;
    }

    public void setAnswerCnt(int answerCnt) {
        this.answerCnt = answerCnt;
    }

    public Question ObjToQuestion(Object o) {
        ObjectMapper objectMapper = new ObjectMapper();
        Map result = objectMapper.convertValue(o, Map.class);

        Question question = new Question();

        question.setQ_dtt(result.get("q_dtt").toString());
        question.setQ_title(result.get("q_title").toString());
        question.setQ_content(result.get("q_content").toString());
        question.setQ_cat(result.get("q_cat").toString().charAt(0));
        question.setU_id(result.get("u_id").toString());
        return question;
    }
}