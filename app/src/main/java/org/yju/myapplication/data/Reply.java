package org.yju.myapplication.data;


import com.fasterxml.jackson.databind.ObjectMapper;

import java.util.List;
import java.util.Map;

import lombok.Data;


@Data
public class Reply {
    private String r_dtt; // 댓글번호
    private String r_content; // 내용
    private String r_writer; // 작성자
    private int b_no; // 글번호

    @Override
    public String toString() {
        return "Reply{" +
                "r_dtt='" + r_dtt + '\'' +
                ", r_content='" + r_content + '\'' +
                ", r_writer='" + r_writer + '\'' +
                ", b_no=" + b_no +
                '}';
    }

    public String getR_dtt() {
        return r_dtt;
    }

    public void setR_dtt(String r_dtt) {
        this.r_dtt = r_dtt;
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

    public int getB_no() {
        return b_no;
    }

    public void setB_no(int b_no) {
        this.b_no = b_no;
    }

    // body내부의 board 받아서 Board 도메인 리턴
    public Reply ObjToReplyList(Object o) {
        List<Reply> Replylist = null;

        ObjectMapper mapper = new ObjectMapper();
        Map result = mapper.convertValue(o, Map.class);

//        return replylist;
        return null;
    }
}
