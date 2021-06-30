package org.yju.myapplication.data;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.time.LocalDateTime;

@Data
public class Rsvt {
    private String rsvt_dtt; // 예약신청일시
    private String chg_id; // 충전기코드
    private String stat_id; // 충전소 코드
    private String rsvt_start; // 예약시작시간
    private String rsvt_end; // 예약종료시간
    private String rsvt_cancel; // 예약취소시간
    private String u_id; // 아이디

    @Override
    public String toString() {
        return "Rsvt{" +
                "rsvt_dtt='" + rsvt_dtt + '\'' +
                ", chg_id='" + chg_id + '\'' +
                ", stat_id='" + stat_id + '\'' +
                ", rsvt_start='" + rsvt_start + '\'' +
                ", rsvt_end='" + rsvt_end + '\'' +
                ", rsvt_cancel='" + rsvt_cancel + '\'' +
                ", u_id='" + u_id + '\'' +
                '}';
    }

    public String getRsvt_dtt() {
        return rsvt_dtt;
    }

    public void setRsvt_dtt(String rsvt_dtt) {
        this.rsvt_dtt = rsvt_dtt;
    }

    public String getChg_id() {
        return chg_id;
    }

    public void setChg_id(String chg_id) {
        this.chg_id = chg_id;
    }

    public String getStat_id() {
        return stat_id;
    }

    public void setStat_id(String stat_id) {
        this.stat_id = stat_id;
    }

    public String getRsvt_start() {
        return rsvt_start;
    }

    public void setRsvt_start(String rsvt_start) {
        this.rsvt_start = rsvt_start;
    }

    public String getRsvt_end() {
        return rsvt_end;
    }

    public void setRsvt_end(String rsvt_end) {
        this.rsvt_end = rsvt_end;
    }

    public String getRsvt_cancel() {
        return rsvt_cancel;
    }

    public void setRsvt_cancel(String rsvt_cancel) {
        this.rsvt_cancel = rsvt_cancel;
    }

    public String getU_id() {
        return u_id;
    }

    public void setU_id(String u_id) {
        this.u_id = u_id;
    }
}