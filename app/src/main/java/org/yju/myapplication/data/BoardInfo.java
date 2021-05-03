package org.yju.myapplication.data;

import lombok.Data;

@Data
public class BoardInfo {
    private Criteria criteria;
    private String b_dtt;

    @Override
    public String toString() {
        return "BoardInfo{" +
                "criteria=" + criteria +
                ", b_dtt='" + b_dtt + '\'' +
                '}';
    }

    public Criteria getCriteria() {
        return criteria;
    }

    public void setCriteria(Criteria criteria) {
        this.criteria = criteria;
    }

    public String getB_dtt() {
        return b_dtt;
    }

    public void setB_dtt(String b_dtt) {
        this.b_dtt = b_dtt;
    }
}
