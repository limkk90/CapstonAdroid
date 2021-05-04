package org.yju.myapplication.data;

import lombok.Data;

@Data
public class Marker {
    private double stat_lng;
    private double stat_lat;

    @Override
    public String toString() {
        return "Marker{" +
                "stat_lng=" + stat_lng +
                ", stat_lat=" + stat_lat +
                '}';
    }

    public double getStat_lng() {
        return stat_lng;
    }

    public void setStat_lng(double stat_lng) {
        this.stat_lng = stat_lng;
    }

    public double getStat_lat() {
        return stat_lat;
    }

    public void setStat_lat(double stat_lat) {
        this.stat_lat = stat_lat;
    }
}
