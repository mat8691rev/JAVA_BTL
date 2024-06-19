/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package pkg5.chuongtrinh.lib;

import java.util.List;
import static pkg5.chuongtrinh.Constant.pathGiaoTrinh;
import pkg5.chuongtrinh.GiaoTrinh;

/**
 *
 * @author tranq
 */
public class WriteDataUtil {
    
    public static void writeGiaoTrinhData(List<GiaoTrinh> data){
        List<String> line = MapToWriteUtil.MapToWriteGiaoTrinh(data);
        FileUtil.writeFile(pathGiaoTrinh, line);
    }
    
}
