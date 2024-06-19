/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package pkg5.chuongtrinh;
/**
 *
 * @author Admin
 */
public class GiangVien{
    private String maGV;
    private String tenGV;
    private String gioiTinh;
    private String hocVi;
    private String chucVu;
    private String sdt;
    private String email;
    private int namVaoLam;
    private Khoa khoa = new Khoa();

    public GiangVien() {
    }

    public GiangVien(String maGV, String tenGV, String gioiTinh, String hocVi, String chucVu, String sdt, String email, int namVaoLam, Khoa khoa) {
        this.maGV = maGV;
        this.tenGV = tenGV;
        this.gioiTinh = gioiTinh;
        this.hocVi = hocVi;
        this.chucVu = chucVu;
        this.sdt = sdt;
        this.email = email;
        this.namVaoLam = namVaoLam;
        this.khoa = khoa;
    }
    public GiangVien(String maGV, String tenGV, String gioiTinh, String hocVi, 
            String chucVu, String sdt, String email, int namVaoLam, String maKhoa, String tenKhoa) {
        this.maGV = maGV;
        this.tenGV = tenGV;
        this.gioiTinh = gioiTinh;
        this.hocVi = hocVi;
        this.chucVu = chucVu;
        this.sdt = sdt;
        this.email = email;
        this.namVaoLam = namVaoLam;
        Khoa khoa = new Khoa(maKhoa, tenKhoa);
        this.khoa = khoa;
    }

    public String getMaGV() {
        return maGV;
    }

    public void setMaGV(String maGV) {
        this.maGV = maGV;
    }

    public String getTenGV() {
        return tenGV;
    }

    public void setTenGV(String tenGV) {
        this.tenGV = tenGV;
    }

    public String getGioiTinh() {
        return gioiTinh;
    }

    public void setGioiTinh(String gioiTinh) {
        this.gioiTinh = gioiTinh;
    }

    public String getHocVi() {
        return hocVi;
    }

    public void setHocVi(String hocVi) {
        this.hocVi = hocVi;
    }

    public String getChucVu() {
        return chucVu;
    }

    public void setChucVu(String chucVu) {
        this.chucVu = chucVu;
    }

    public String getSdt() {
        return sdt;
    }

    public void setSdt(String sdt) {
        this.sdt = sdt;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public int getNamVaoLam() {
        return namVaoLam;
    }

    public void setNamVaoLam(int namVaoLam) {
        this.namVaoLam = namVaoLam;
    }

    public Khoa getKhoa() {
        return khoa;
    }

    public void setKhoa(Khoa khoa) {
        this.khoa = khoa;
    }

    @Override
    public String toString() {
        return tenGV;
    }
}
