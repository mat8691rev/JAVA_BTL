/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package pkg5.chuongtrinh;

import java.io.Serializable;

/**
 *
 * @author Admin
 */
public class BienSoan implements Serializable{
    private String maBS;
    private Date batDau;
    private Date ketThuc;
    private String noiDung;
    private String ghiChu;

    public BienSoan() {
    }

    public BienSoan(Date batDau, Date ketThuc, String noiDung, String ghiChu) {
        this.batDau = batDau;
        this.ketThuc = ketThuc;
        this.noiDung = noiDung;
        this.ghiChu = ghiChu;
    }
    
    public BienSoan(String maBs, Date batDau, Date ketThuc, String noiDung, String ghiChu){
        this.maBS = maBs;
        this.batDau = batDau;
        this.ketThuc = ketThuc;
        this.noiDung = noiDung;
        this.ghiChu = ghiChu;
    }

    public Date getBatDau() {
        return batDau;
    }

    public void setBatDau(Date batDau) {
        this.batDau = batDau;
    }

    public Date getKetThuc() {
        return ketThuc;
    }

    public void setKetThuc(Date ketThuc) {
        this.ketThuc = ketThuc;
    }

    public String getNoiDung() {
        return noiDung;
    }

    public void setNoiDung(String noiDung) {
        this.noiDung = noiDung;
    }

    public String getGhiChu() {
        return ghiChu;
    }

    public void setGhiChu(String ghiChu) {
        this.ghiChu = ghiChu;
    }
   
    public String getMaBs(){
        return this.maBS;
    }
    
    public void setMaBs(String maBs){
        this.maBS = maBs;
    }
}
