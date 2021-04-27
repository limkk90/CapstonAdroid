package org.yju.myapplication.cmData;

public class CommunityData {
    String title;
    String content;
    String writer;
    String regDate;

    public CommunityData(String title, String content, String writer, String regDate) {
        this.title = title;
        this.content = content;
        this.writer = writer;
        this.regDate = regDate;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getWriter() {
        return writer;
    }

    public void setWriter(String writer) {
        this.writer = writer;
    }

    public String getRegDate() {
        return regDate;
    }

    public void setRegDate(String regDate) {
        this.regDate = regDate;
    }
}
