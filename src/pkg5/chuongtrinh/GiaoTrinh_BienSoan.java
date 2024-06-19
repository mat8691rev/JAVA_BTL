/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package pkg5.chuongtrinh;
/**
 *
 * @author Admin
 */
public class GiaoTrinh_BienSoan {
    private String maGT;
    private String maBS;

    public GiaoTrinh_BienSoan() {
    }

    public GiaoTrinh_BienSoan(String maGT, String maBS) {
        this.maGT = maGT;
        this.maBS = maBS;
    }

    public String getMaGT() {
        return maGT;
    }

    public void setMaGT(String maGT) {
        this.maGT = maGT;
    }

    public String getMaBS() {
        return maBS;
    }

    public void setMaBS(String maBS) {
        this.maBS = maBS;
    }   
}
