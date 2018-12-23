package com.dmt.sannguyen.lgwfb;

public class MuiTiem {
    private int ID_MuiTiem;
    private int ID_Baby;
    private int ID_Vaccine;
    private String ThoiGianDuKien;
    private String ThoiGianTiem;
    private String ThoiGianHenGio;
    private String GhiChu;
    private String BabyName;
    private String MuiTiemName;
    private String StatusMuiTiem;

    public MuiTiem(int ID_MuiTiem, int ID_Baby, int ID_Vaccine, String thoiGianDuKien, String thoiGianTiem, String thoiGianHenGio, String ghiChu, String babyName, String muiTiemName, String statusMuiTiem) {
        this.ID_MuiTiem = ID_MuiTiem;
        this.ID_Baby = ID_Baby;
        this.ID_Vaccine = ID_Vaccine;
        ThoiGianDuKien = thoiGianDuKien;
        ThoiGianTiem = thoiGianTiem;
        ThoiGianHenGio = thoiGianHenGio;
        GhiChu = ghiChu;
        BabyName = babyName;
        MuiTiemName = muiTiemName;
        StatusMuiTiem = statusMuiTiem;
    }

    public int getID_MuiTiem() {
        return ID_MuiTiem;
    }

    public void setID_MuiTiem(int ID_MuiTiem) {
        this.ID_MuiTiem = ID_MuiTiem;
    }

    public int getID_Baby() {
        return ID_Baby;
    }

    public void setID_Baby(int ID_Baby) {
        this.ID_Baby = ID_Baby;
    }

    public int getID_Vaccine() {
        return ID_Vaccine;
    }

    public void setID_Vaccine(int ID_Vaccine) {
        this.ID_Vaccine = ID_Vaccine;
    }

    public String getThoiGianDuKien() {
        return ThoiGianDuKien;
    }

    public void setThoiGianDuKien(String thoiGianDuKien) {
        ThoiGianDuKien = thoiGianDuKien;
    }

    public String getThoiGianTiem() {
        return ThoiGianTiem;
    }

    public void setThoiGianTiem(String thoiGianTiem) {
        ThoiGianTiem = thoiGianTiem;
    }

    public String getThoiGianHenGio() {
        return ThoiGianHenGio;
    }

    public void setThoiGianHenGio(String thoiGianHenGio) {
        ThoiGianHenGio = thoiGianHenGio;
    }

    public String getGhiChu() {
        return GhiChu;
    }

    public void setGhiChu(String ghiChu) {
        GhiChu = ghiChu;
    }

    public String getBabyName() {
        return BabyName;
    }

    public void setBabyName(String babyName) {
        BabyName = babyName;
    }

    public String getMuiTiemName() {
        return MuiTiemName;
    }

    public void setMuiTiemName(String muiTiemName) {
        MuiTiemName = muiTiemName;
    }

    public String getStatusMuiTiem() {
        return StatusMuiTiem;
    }

    public void setStatusMuiTiem(String statusMuiTiem) {
        StatusMuiTiem = statusMuiTiem;
    }
}
