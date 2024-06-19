/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package pkg5.chuongtrinh.lib;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import pkg5.chuongtrinh.BienSoan;
import static pkg5.chuongtrinh.Constant.pathBienSoan;
import static pkg5.chuongtrinh.Constant.pathGiangVien;
import static pkg5.chuongtrinh.Constant.pathMonHoc;
import pkg5.chuongtrinh.Date;
import pkg5.chuongtrinh.GiangVien;
import pkg5.chuongtrinh.GiaoTrinh;
import pkg5.chuongtrinh.GiaoTrinh_BienSoan;
import pkg5.chuongtrinh.Khoa;
import pkg5.chuongtrinh.MonHoc;

/**
 *
 * @author tranq
 */
public class MapToReadUtil {
    
    public static Date mapToDate(String dateData){
        String[] dateElement = dateData.split("/");
        return new Date(Integer.parseInt(dateElement[0]), 
                            Integer.parseInt(dateElement[1]),
                                Integer.parseInt(dateElement[2]));
    }
    
    // dụng map chuyen doi tư maGV -> GV object
    public static Map<String, GiangVien> buildMapGiangVien(){
        // lay thong tin giang vien tu file lên
        ArrayList<GiangVien> listGV = LoadUtil.loadGiangVien(pathGiangVien);
        // dung hashmap de chuyen doi
        Map<String, GiangVien> mapGV = new HashMap<>();
        for (GiangVien gv: listGV){
            mapGV.put(gv.getMaGV(), gv);
        }
        return mapGV;
    }
    
    public static Map<String, BienSoan> buildMapBienSoan(){
        ArrayList<BienSoan> listBS = LoadUtil.loadBienSoan(pathBienSoan);
        Map<String, BienSoan> mapBS = new HashMap<>();
        for (BienSoan bs: listBS){
            mapBS.put(bs.getMaBs(), bs);
        }
        return mapBS;
    }
    
    public static Map<String, MonHoc> buildMapMonHoc(){
        ArrayList<MonHoc> listMH = LoadUtil.loadMonHoc(pathMonHoc);
        Map<String, MonHoc> mapMH = new HashMap<>();
        for (MonHoc mh: listMH){
            mapMH.put(mh.getMaMon(), mh);
        }
        return mapMH;
    }
    
    // ham loai bo ngoac vuong + cat dau phay
    public static String[] extractArrayData(String arrayData){
        return arrayData.substring(1, arrayData.length()-1).split(",");
    }
    
    // chuyen doi tư maGV -> giang vien object
    public static GiangVien getGiangVienByMaGV(String maGV, 
                                           Map<String, GiangVien> mapGiangVien){
        GiangVien gv = mapGiangVien.get(maGV);
        if (gv != null) return gv;
        return null;
    }
    
    public static BienSoan getBienSoanByMaBS(String maBS, 
                                           Map<String, BienSoan> mapBienSoan){
        BienSoan bs = mapBienSoan.get(maBS);
        if (bs != null) return bs;
        return null;
    }
    
    public static MonHoc getMonHocByMaMH(String maMH,
                                           Map<String, MonHoc> mapMonHoc){
        MonHoc mh = mapMonHoc.get(maMH);
        if (mh != null) return mh;
        return null;
    }
    
    // ham bien doi tu danh sach maGV sang danh sach object giang vien
    public static ArrayList<GiangVien> getListGiangVienByListMaGV
                              (String[] listMaGV, Map<String, GiangVien> mapGV){
        ArrayList<GiangVien> listGiangVien = new ArrayList<>();
        
        if (0 == listMaGV.length) return listGiangVien;
        
        for (String maGV: listMaGV){
            listGiangVien.add(mapGV.get(maGV));
        }
        return listGiangVien; 
    }
                              
    public static ArrayList<BienSoan> getListBienSoanByListMaBS
                               (String[] listMaBS, Map<String, BienSoan> mapBS){
        ArrayList<BienSoan> listBienSoan = new ArrayList<>();
        
        if (0 == listMaBS.length) return listBienSoan;
        
        for (String maBS: listMaBS){
            listBienSoan.add(mapBS.get(maBS));
        }
        return listBienSoan;
    }
    
    // map bien soan object vơi du lieu trong file                           
    public static ArrayList<BienSoan> mapToBienSoanList(List<String> data){
        ArrayList<BienSoan> listResult = new ArrayList();
        
        if (data.isEmpty()) return listResult;
        
        for (String line: data){
            String[] element = line.split(";");
            BienSoan object = new BienSoan(element[0], mapToDate(element[1]),
                        mapToDate(element[2]), element[3], element[4]);
            listResult.add(object);
        }
        return listResult;
    }
    
    // map giang vien object vơi du lieu trong file
    public static ArrayList<GiangVien> mapToGiangVienList(List<String> data){
        ArrayList<GiangVien> listResult = new ArrayList();
        
        if (data.isEmpty()) return listResult;
        
        for (String line: data){
            String[] element = line.split(";");
            GiangVien giangVien = new GiangVien(element[0], element[1],
                    element[2], element[3], element[4], element[5], element[6],
                    Integer.parseInt(element[7]), element[8], element[9]);
            listResult.add(giangVien);
        }
        return listResult;
    }
    
    public static ArrayList<Khoa> mapToKhoaList(List<String> data){
        ArrayList<Khoa> listResult = new ArrayList();
        
        if (data.isEmpty()) return listResult;
        
        for (String line: data){
            String[] element = line.split(";");
            Khoa khoa = new Khoa(element[0], element[1]);     
            listResult.add(khoa);
        }
        return listResult;
    }
    
    public static ArrayList<MonHoc> mapToMonHocList(List<String> data){
        ArrayList<MonHoc> listResult = new ArrayList();
        
        if (data.isEmpty()) return listResult;
        
        for (String line: data){
            String[] element = line.split(";");
            MonHoc monHoc = new MonHoc(element[0], element[1], 
                    Integer.parseInt(element[2]), Double.parseDouble(element[3]), 
                    element[4], element[5]);
            listResult.add(monHoc);
        }
        return listResult;
    }
    
    public static ArrayList<GiaoTrinh_BienSoan> 
                                      mapToGiaoTrinhBienSoan(List<String> data){
        ArrayList<GiaoTrinh_BienSoan> listResult = new ArrayList();
        
        if (data.isEmpty()) return listResult;
        
        for (String line: data){
            String[] element = line.split(";");
            GiaoTrinh_BienSoan giaoTrinhBienSoan = 
                    new GiaoTrinh_BienSoan(element[0], element[1]);
            listResult.add(giaoTrinhBienSoan);
        }
        return listResult;
    }
    
    // map giang vien
    public static ArrayList<GiaoTrinh> mapToGiaoTrinh(List<String> data){
        Map<String, MonHoc> mapMonHoc = buildMapMonHoc();
        Map<String, GiangVien> mapGiangVien = buildMapGiangVien();
        Map<String, BienSoan> mapBienSoan = buildMapBienSoan();
        
        ArrayList<GiaoTrinh> listResult = new ArrayList();
        for (String line: data){
            String[] element = line.split(";");
            MonHoc monHoc = mapMonHoc.get(element[2]);
            GiangVien chuBien = mapGiangVien.get(element[3]);
            ArrayList<GiangVien> dongTacGia = 
                    getListGiangVienByListMaGV( 
                                extractArrayData(element[4]), mapGiangVien);
            Khoa khoa = new Khoa(element[5], element[6]);
            Date ngayBd = mapToDate(element[7]);
            Date ngayKT = mapToDate(element[8]);
            ArrayList<BienSoan> listBienSoan = 
                    getListBienSoanByListMaBS(
                                extractArrayData(element[9]), mapBienSoan);
            boolean xuatBan = Boolean.parseBoolean(element[10]);
            boolean dangKy = Boolean.parseBoolean(element[11]);
            GiaoTrinh giaoTrinh =
                    new GiaoTrinh(element[0], element[1], monHoc, chuBien,
                    dongTacGia, khoa, ngayBd, ngayKT, listBienSoan, xuatBan, dangKy);
            listResult.add(giaoTrinh);     
        }
        return listResult;
    }
    
}
