package com.dmt.sannguyen.lgwfb;

public class TramXa {
    private int IDTramXa;
    private String Ten;
    private String DiaChi;
    private String Street;
    private String Ward;
    private String District;
    private String City;
    private long Xvalue;
    private long Yvalue;

    public TramXa(int IDTramXa, String ten, String diaChi, String street, String ward, String district, String city, long xvalue, long yvalue) {
        this.IDTramXa = IDTramXa;
        Ten = ten;
        DiaChi = diaChi;
        Street = street;
        Ward = ward;
        District = district;
        City = city;
        Xvalue = xvalue;
        Yvalue = yvalue;
    }

    public int getIDTramXa() {
        return IDTramXa;
    }

    public void setIDTramXa(int IDTramXa) {
        this.IDTramXa = IDTramXa;
    }

    public String getTen() {
        return Ten;
    }

    public void setTen(String ten) {
        Ten = ten;
    }

    public String getDiaChi() {
        return DiaChi;
    }

    public void setDiaChi(String diaChi) {
        DiaChi = diaChi;
    }

    public String getStreet() {
        return Street;
    }

    public void setStreet(String street) {
        Street = street;
    }

    public String getWard() {
        return Ward;
    }

    public void setWard(String ward) {
        Ward = ward;
    }

    public String getDistrict() {
        return District;
    }

    public void setDistrict(String district) {
        District = district;
    }

    public String getCity() {
        return City;
    }

    public void setCity(String city) {
        City = city;
    }

    public long getXvalue() {
        return Xvalue;
    }

    public void setXvalue(long xvalue) {
        Xvalue = xvalue;
    }

    public long getYvalue() {
        return Yvalue;
    }

    public void setYvalue(long yvalue) {
        Yvalue = yvalue;
    }
}
