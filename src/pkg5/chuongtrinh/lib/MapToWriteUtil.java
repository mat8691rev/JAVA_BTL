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
import static pkg5.chuongtrinh.Constant.pathMonHoc;
import pkg5.chuongtrinh.GiangVien;
import pkg5.chuongtrinh.GiaoTrinh;
import pkg5.chuongtrinh.MonHoc;

/**
 *
 * @author tranq
 */
public class MapToWriteUtil {
    
    public static String buildListGVData(List<GiangVien> listGiangVien){
        StringBuilder result = new StringBuilder();
        result.append("[");
        if (!listGiangVien.isEmpty()){
            for (GiangVien gv: listGiangVien){
                result.append(gv.getMaGV()).append(",");
            }
            result.deleteCharAt(result.length() -1);
        }
        result.append("]");
        return result.toString();
    }
    
    public static String buildListBSData(List<BienSoan> listBienSoan){
        StringBuilder result = new StringBuilder();
        result.append("[");
        if (!listBienSoan.isEmpty()){
            for (BienSoan bs: listBienSoan){
                result.append(bs.getMaBs()).append(",");
            }
            result.deleteCharAt(result.length() -1);
        }
        result.append("]");
        return result.toString();
    }
    
    public static Map<String, MonHoc> buildMapTenMonHoc(){
        ArrayList<MonHoc> listMH = LoadUtil.loadMonHoc(pathMonHoc);
        Map<String, MonHoc> mapMH = new HashMap<>();
        for (MonHoc mh: listMH){
            mapMH.put(mh.getTenMon(), mh);
        }
        return mapMH;
    }
    
    public static List<String> MapToWriteGiaoTrinh(List<GiaoTrinh> listData){
       Map<String, MonHoc> mapMonHoc = buildMapTenMonHoc();
       List<String> resultData = new ArrayList<>();
       for (GiaoTrinh gt: listData){
           StringBuilder data = new StringBuilder();
           data.append(gt.getMaGT()).append(";");
           data.append(gt.getTenGT()).append(";");
           MonHoc monhoc = mapMonHoc.get(gt.getTenMon().getTenMon());
           data.append(monhoc.getMaMon()).append(";");
           data.append(gt.getChuBien().getMaGV()).append(";");
           data.append(buildListGVData(gt.getCacTacGia())).append(";");
           data.append(gt.getKhoa().getMaKhoa()).append(";");
           data.append(gt.getKhoa().getTenKhoa()).append(";");
           data.append(gt.getTgbd().toString()).append(";");
           data.append(gt.getTgkt().toString()).append(";");
           data.append(buildListBSData(gt.getBienSoan())).append(";");
           data.append(String.valueOf(gt.isXuatBan())).append(";");
           data.append(String.valueOf(gt.isDangKy()));
           resultData.add(data.toString());
       }
       return resultData;
    }
    
}
