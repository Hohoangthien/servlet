/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Model;

/**
 *
 * @author admin
 */
public class Sach {

    private int maSach;
    private String tenSach;
    private TacGia tg;
    private int giatien;

    public Sach() {

    }

    public Sach(int maSach, String tenSach, TacGia tg, int giatien) {
        this.maSach = maSach;
        this.tenSach = tenSach;
        this.tg = tg;
        this.giatien = giatien;
    }

    public Sach(int ma_sach, String ten_sach, TacGia tac_gia) {
        this.maSach = ma_sach;
        this.tenSach = ten_sach;
        this.tg = new TacGia(tac_gia);
    }

    public Sach(Sach s) {
        this.maSach = s.getMaSach();
        this.tenSach = s.getTenSach();
        this.tg = new TacGia(s.getTg());
    }

    public int getMaSach() {
        return maSach;
    }

    public void setMaSach(int maSach) {
        this.maSach = maSach;
    }

    public String getTenSach() {
        return tenSach;
    }

    public void setTenSach(String tenSach) {
        this.tenSach = tenSach;
    }

    public TacGia getTg() {
        return tg;
    }

    public void setTg(TacGia tg) {
        this.tg = tg;
    }

    public int getGiatien() {
        return giatien;
    }

    public void setGiatien(int giatien) {
        this.giatien = giatien;
    }

    @Override
    public String toString() {
        return maSach + " " + tenSach;
    }

}
