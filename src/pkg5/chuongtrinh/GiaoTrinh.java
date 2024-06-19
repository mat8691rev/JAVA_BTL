/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package pkg5.chuongtrinh;

import java.util.ArrayList;

/**
 *
 * @author Admin
 */
public class GiaoTrinh{
    private String maGT;
    private String tenGT;
    private MonHoc tenMon = new MonHoc();
    private GiangVien chuBien = new GiangVien();
    private ArrayList<GiangVien> cacTacGia = new ArrayList<>();
    private Khoa khoa;
    private Date tgbd;
    private Date tgkt;
    private ArrayList<BienSoan> bienSoan;
    private boolean xuatBan;
    private boolean dangKy;

    public GiaoTrinh() {
    }

    public GiaoTrinh(String maGT, String tenGT, MonHoc tenMon, GiangVien chuBien, ArrayList<GiangVien> cacTacGia, Khoa khoa, Date tgbd, Date tgkt) {
        this.maGT = maGT;
        this.tenGT = tenGT;
        this.tenMon = tenMon;
        this.chuBien = chuBien;
        this.cacTacGia = cacTacGia;
        this.khoa = khoa;
        this.tgbd = tgbd;
        this.tgkt = tgkt;
        this.bienSoan = new ArrayList<>();
        this.xuatBan = false;
        this.dangKy = false;
    }
    
    public GiaoTrinh(String maGT, String tenGT, 
                MonHoc tenMon, GiangVien chuBien, 
                    ArrayList<GiangVien> cacTacGia, Khoa khoa, 
                    Date tgbd, Date tgkt, 
                    ArrayList<BienSoan> listBienSoan,
                    boolean xuatBan, boolean dangKy) {
        this.maGT = maGT;
        this.tenGT = tenGT;
        this.tenMon = tenMon;
        this.chuBien = chuBien;
        this.cacTacGia = cacTacGia;
        this.khoa = khoa;
        this.tgbd = tgbd;
        this.tgkt = tgkt;
        this.bienSoan = listBienSoan;
        this.xuatBan = xuatBan;
        this.dangKy = dangKy;
    }
    

    public String getMaGT() {
        return maGT;
    }

    public void setMaGT(String maGT) {
        this.maGT = maGT;
    }

    public String getTenGT() {
        return tenGT;
    }

    public void setTenGT(String tenGT) {
        this.tenGT = tenGT;
    }

    public MonHoc getTenMon() {
        return tenMon;
    }

    public void setTenMon(MonHoc tenMon) {
        this.tenMon = tenMon;
    }

    public GiangVien getChuBien() {
        return chuBien;
    }

    public void setChuBien(GiangVien chuBien) {
        this.chuBien = chuBien;
    }

    public ArrayList<GiangVien> getCacTacGia() {
        return cacTacGia;
    }

    public void setCacTacGia(ArrayList<GiangVien> cacTacGia) {
        this.cacTacGia = cacTacGia;
    }

    public Khoa getKhoa() {
        return khoa;
    }

    public void setKhoa(Khoa khoa) {
        this.khoa = khoa;
    }

    public Date getTgbd() {
        return tgbd;
    }

    public void setTgbd(Date tgbd) {
        this.tgbd = tgbd;
    }

    public Date getTgkt() {
        return tgkt;
    }

    public void setTgkt(Date tgkt) {
        this.tgkt = tgkt;
    }

    public ArrayList<BienSoan> getBienSoan() {
        return bienSoan;
    }

    public void setBienSoan(ArrayList<BienSoan> bienSoan) {
        this.bienSoan = bienSoan;
    }

    public boolean isXuatBan() {
        return xuatBan;
    }

    public void setXuatBan(boolean xuatBan) {
        this.xuatBan = xuatBan;
    }

    public boolean isDangKy() {
        return dangKy;
    }

    public void setDangKy(boolean dangKy) {
        this.dangKy = dangKy;
    }
    
    public String getdsTG() {
        String cacTacGia = "";
        
        for (GiangVien x : this.cacTacGia) {
            cacTacGia += x.getTenGV() + " ";
        }
        
        return cacTacGia.trim();
    }
    
    
}
