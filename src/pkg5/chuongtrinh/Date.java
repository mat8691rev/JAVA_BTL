/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package pkg5.chuongtrinh;
/**
 *
 * @author Admin
 */
public class Date{
    
    private int D;
    private int M;
    private int Y;

    public Date() {
    }

    public Date(int D, int M, int Y) {
        this.D = D;
        this.M = M;
        this.Y = Y;
    }
    
    public Date(Date date) {
        this.D = date.D;
        this.M = date.M;
        this.Y = date.Y;
    }

    public int getD() {
        return D;
    }

    public void setD(int D) {
        this.D = D;
    }

    public int getM() {
        return M;
    }

    public void setM(int M) {
        this.M = M;
    }

    public int getY() {
        return Y;
    }

    public void setY(int Y) {
        this.Y = Y;
    }
    
    @Override
    public String toString() {
        return D + "/" + M + "/" + Y;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Date other = (Date) obj;
        if (this.D != other.D) {
            return false;
        }
        if (this.M != other.M) {
            return false;
        }
        return this.Y == other.Y;
    }
}
