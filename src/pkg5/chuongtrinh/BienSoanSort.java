/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package pkg5.chuongtrinh;

import pkg5.chuongtrinh.BienSoan;
import java.util.Comparator;

/**
 *
 * @author Admin
 */
public class BienSoanSort implements Comparator<BienSoan>{

    @Override
    public int compare(BienSoan o1, BienSoan o2) {
        if (o1.getBatDau().getY() > o2.getBatDau().getY()) {
            return 1;
        } else if (o1.getBatDau().getY() < o2.getBatDau().getY()) {
            return -1;
        } else {
            if (o1.getBatDau().getM() > o2.getBatDau().getM()) {
                return 1;
            } else if (o1.getBatDau().getM() < o2.getBatDau().getM()) {
                return -1;
            } else {
                if (o1.getBatDau().getD() > o2.getBatDau().getD()) {
                    return 1;
                } else if (o1.getBatDau().getD() < o2.getBatDau().getD()) {
                    return -1;
                } else {
                    return 0;
                }
            }
        }
    }
    
}
