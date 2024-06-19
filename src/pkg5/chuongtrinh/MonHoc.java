/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package pkg5.chuongtrinh;

/**
 *
 * @author Admin
 */
public class MonHoc{
    private String maMon;
    private String tenMon;
    private int soTC;
    private double hocPhi;
    private Khoa khoa;

    public MonHoc() {
    }

    public MonHoc(String maMon, String tenMon, int soTC, double hocPhi, Khoa khoa) {
        this.maMon = maMon;
        this.tenMon = tenMon;
        this.soTC = soTC;
        this.hocPhi = hocPhi;
        this.khoa = khoa;
    }
    
    public MonHoc(String maMon, String tenMon, int soTC, double hocPhi, String maKhoa, String tenKhoa) {
        this.maMon = maMon;
        this.tenMon = tenMon;
        this.soTC = soTC;
        this.hocPhi = hocPhi;
        this.khoa = new Khoa(maKhoa, tenKhoa);
    }

    public String getMaMon() {
        return maMon;
    }

    public void setMaMon(String maMon) {
        this.maMon = maMon;
    }

    public String getTenMon() {
        return tenMon;
    }

    public void setTenMon(String tenMon) {
        this.tenMon = tenMon;
    }

    public int getSoTC() {
        return soTC;
    }

    public void setSoTC(int soTC) {
        this.soTC = soTC;
    }

    public double getHocPhi() {
        return hocPhi;
    }

    public void setHocPhi(double hocPhi) {
        this.hocPhi = hocPhi;
    }

    public Khoa getKhoa() {
        return khoa;
    }

    public void setKhoa(Khoa khoa) {
        this.khoa = khoa;
    }

    @Override
    public String toString() {
        return "MonHoc{" + "maMon=" + maMon + ", tenMon=" + tenMon + ", soTC=" + soTC + ", hocPhi=" + hocPhi + ", khoa=" + khoa + '}';
    }


}