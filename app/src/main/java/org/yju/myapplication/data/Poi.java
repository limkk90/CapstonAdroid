package org.yju.myapplication.data;

public class Poi {
    public String getPoiName() {
        return poiName;
    }

    public void setPoiName(String poiName) {
        this.poiName = poiName;
    }



    public String getPoiAddress() {
        return poiAddress;
    }

    public void setPoiAddress(String poiAddress) {
        this.poiAddress = poiAddress;
    }

    @Override
    public String toString() {
        return "Poi{" +
                "poiName='" + poiName + '\'' +
                ", poiAddress='" + poiAddress + '\'' +
                '}';
    }

    private String poiName;
    private String poiAddress;

}
