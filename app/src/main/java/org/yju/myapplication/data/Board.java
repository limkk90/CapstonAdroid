package org.yju.myapplication.data;

import android.os.Build;
import android.util.Log;

import androidx.annotation.RequiresApi;

import com.fasterxml.jackson.databind.ObjectMapper;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Date;
import java.util.Map;

import lombok.AllArgsConstructor;
import lombok.Data;

@RequiresApi(api = Build.VERSION_CODES.O)
@Data
public class Board {
    private String b_no;
    private char cat_cd;
    private String b_title;
    private String b_content;
    private int b_visite;
    private String b_dtt;
    private String u_id;

    @Override
    public String toString() {
        return "Board{" +
                "b_no=" + b_no +
                ", cat_cd=" + cat_cd +
                ", b_title='" + b_title + '\'' +
                ", b_content='" + b_content + '\'' +
                ", b_visite=" + b_visite +
                ", b_dtt=" + b_dtt +
                ", u_id='" + u_id + '\'' +
                '}';
    }

    public String getB_no() {
        return b_no;
    }

    public void setB_no(String b_no) {
        this.b_no = b_no;
    }

    public char getCat_cd() {
        return cat_cd;
    }

    public void setCat_cd(char cat_cd) {
        this.cat_cd = cat_cd;
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

    public String getB_dtt() {
        return b_dtt;
    }

    public void setB_dtt(String b_dtt) {
        this.b_dtt = b_dtt;
    }

    public String getU_id() {
        return u_id;
    }

    public void setU_id(String u_id) {
        this.u_id = u_id;
    }

    // body내부의 board 받아서 Board 도메인 리턴
    public Board ObjToBoard(Object o) {
        ObjectMapper mapper = new ObjectMapper();
        Map result = mapper.convertValue(o, Map.class);

        Board board = new Board();

        board.setB_title(result.get("b_title").toString());
        board.setB_no(result.get("b_no").toString());
        board.setB_dtt(result.get("b_dtt").toString());
        board.setB_content(result.get("b_content").toString());
        board.setCat_cd(result.get("cat_cd").toString().charAt(0));
        board.setU_id(result.get("u_id").toString());

        return board;
    }
}
