package org.yju.myapplication.data;

import lombok.Data;

@Data
public class Criteria {
    private String bnoTime; //게시글 번호 2020/10/10 18:11:10
    private int page; //선택한 페이지
    private int perPageNum; //선택한 페이지에 보여줄 갯수
    private int rowStart; //DB에 검색할 start rownum
    private int rowEnd; //DB에 검색할 end rownum
    private char ser;
    private String keyWord;
    private char cat_cd;

    @Override
    public String toString() {
        return "Criteria{" +
                "bnoTime='" + bnoTime + '\'' +
                ", page=" + page +
                ", perPageNum=" + perPageNum +
                ", rowStart=" + rowStart +
                ", rowEnd=" + rowEnd +
                ", ser=" + ser +
                ", keyWord='" + keyWord + '\'' +
                ", cat_cd=" + cat_cd +
                '}';
    }

    public char getSer() {
        return ser;
    }

    public void setSer(char ser) {
        this.ser = ser;
    }

    public String getKeyWord() {
        return keyWord;
    }

    public void setKeyWord(String keyWord) {
        this.keyWord = keyWord;
    }

    public char getCat_cd() {
        return cat_cd;
    }

    public void setCat_cd(char cat_cd) {
        this.cat_cd = cat_cd;
    }

    public Criteria(){
        this.page = 1;
        this.perPageNum = 10;
    }

    public void setPage(int page){
        if(page <= 0){
            this.page = 1;
            return;
        }
        this.page = page;
    }

    public void setPerPageNum(int perPageNum){
        if(perPageNum <= 0 || perPageNum > 100){
            this.perPageNum = 10;
            return;
        }
        this.perPageNum = perPageNum;
    }

    public int getPage(){
        return page;
    }

    public int getPageStart(){
        return (this.page - 1) * perPageNum;
    }

    public int getPerPageNum(){
        return this.perPageNum;
    }

    public int getRowStart(){
        rowStart = ((page-1) * perPageNum) + 1;
        return rowStart;
    }

    public int getRowEnd(){
        rowEnd = rowStart + perPageNum -1;
        return rowEnd;
    }
}
