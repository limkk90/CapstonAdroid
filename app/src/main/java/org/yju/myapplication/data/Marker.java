package org.yju.myapplication.data;

import lombok.Data;

@Data
public class Marker {
    private String stat_id;
    private String stat_lng;
    private String stat_lat;
    private String stat_nm;

    public String getStat_lng() {
        return stat_lng;
    }

    public void setStat_lng(String stat_lng) {
        this.stat_lng = stat_lng;
    }

    public String getStat_lat() {
        return stat_lat;
    }

    public void setStat_lat(String stat_lat) {
        this.stat_lat = stat_lat;
    }

    public String getStat_nm() {
        return stat_nm;
    }

    public void setStat_nm(String stat_nm) {
        this.stat_nm = stat_nm;
    }

    public String getStat_id() {
        return stat_id;
    }

    public void setStat_id(String stat_id) {
        this.stat_id = stat_id;
    }

    @Override
    public String toString() {
        return "Marker{" +
                "stat_id='" + stat_id + '\'' +
                ", stat_lng='" + stat_lng + '\'' +
                ", stat_lat='" + stat_lat + '\'' +
                ", stat_nm='" + stat_nm + '\'' +
                '}';
    }
}
