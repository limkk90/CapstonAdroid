package org.yju.myapplication.data;

import java.time.LocalDateTime;

import lombok.Data;

@Data
public class Charger {
    private String chg_id; // 충전기
    private char chg_type; // 충전기타입 (01:DC차데모, 02:AC완속, 03:DC차데모+AC3상, 04: DC콤보, 05:DC차데모+DC콤보, 06: DCckepah+AC3상+DC콤보, 07:AC3상)
    private String chg_method;
    private char chg_st;
    private String chg_st_dt;
    private String chg_rsvt;
    private String stat_id;

    @Override
    public String toString() {
        return "Charger{" +
                "chg_id='" + chg_id + '\'' +
                ", chg_type=" + chg_type +
                ", chg_method='" + chg_method + '\'' +
                ", chg_st=" + chg_st +
                ", chg_st_dt=" + chg_st_dt +
                ", chg_rsvt=" + chg_rsvt +
                ", stat_id='" + stat_id + '\'' +
                '}';
    }

    public String getChg_id() {
        return chg_id;
    }

    public void setChg_id(String chg_id) {
        this.chg_id = chg_id;
    }

    public char getChg_type() {
        return chg_type;
    }

    public void setChg_type(char chg_type) {
        this.chg_type = chg_type;
    }

    public String getChg_method() {
        return chg_method;
    }

    public void setChg_method(String chg_method) {
        this.chg_method = chg_method;
    }

    public char getChg_st() {
        return chg_st;
    }

    public void setChg_st(char chg_st) {
        this.chg_st = chg_st;
    }

    public String getChg_st_dt() {
        return chg_st_dt;
    }

    public void setChg_st_dt(String chg_st_dt) {
        this.chg_st_dt = chg_st_dt;
    }

    public String getChg_rsvt() {
        return chg_rsvt;
    }

    public void setChg_rsvt(String chg_rsvt) {
        this.chg_rsvt = chg_rsvt;
    }

    public String getStat_id() {
        return stat_id;
    }

    public void setStat_id(String stat_id) {
        this.stat_id = stat_id;
    }
}
