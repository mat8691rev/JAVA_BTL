/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package pkg5.chuongtrinh.lib;

import java.util.ArrayList;
import java.util.List;
import pkg5.chuongtrinh.BienSoan;
import pkg5.chuongtrinh.GiangVien;
import pkg5.chuongtrinh.GiaoTrinh;
import pkg5.chuongtrinh.GiaoTrinh_BienSoan;
import pkg5.chuongtrinh.Khoa;
import pkg5.chuongtrinh.MonHoc;

/**
 *
 * @author tranq
 */
public class LoadUtil {
    
    public static ArrayList<GiangVien> loadGiangVien(String fileName){
        List<String> fileData = FileUtil.readFile(fileName);
        ArrayList<GiangVien> listGiangVien = MapToReadUtil.mapToGiangVienList(fileData);
        return listGiangVien;
    }
    
    public static ArrayList<Khoa> loadKhoa(String fileName){
        List<String> fileData = FileUtil.readFile(fileName);
        ArrayList<Khoa> listKhoa = MapToReadUtil.mapToKhoaList(fileData);
        return listKhoa;
    }
    
    public static ArrayList<BienSoan> loadBienSoan(String fileName){
        List<String> fileData = FileUtil.readFile(fileName);
        ArrayList<BienSoan> listBienSoan = MapToReadUtil.mapToBienSoanList(fileData);
        return listBienSoan;   
    }
    
    public static ArrayList<MonHoc> loadMonHoc(String fileName){
        List<String> fileData = FileUtil.readFile(fileName);
        ArrayList<MonHoc> listMonHoc = MapToReadUtil.mapToMonHocList(fileData);
        return listMonHoc;   
    }
    
    public static ArrayList<GiaoTrinh_BienSoan> loadGiaoTrinhBienSoan(String fileName){
        List<String> fileData = FileUtil.readFile(fileName);
        ArrayList<GiaoTrinh_BienSoan> listGiaoTrinhBienSoan = 
                                        MapToReadUtil.mapToGiaoTrinhBienSoan(fileData);
        return listGiaoTrinhBienSoan;   
    }
    
    public static ArrayList<GiaoTrinh> loadGiaoTrinh(String fileName){
        List<String> fileData = FileUtil.readFile(fileName);
        ArrayList<GiaoTrinh> listGiaoTrinh = 
                                        MapToReadUtil.mapToGiaoTrinh(fileData);
        return listGiaoTrinh;   
    }
}
