/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Model;

/**
 *
 * @author admin
 */
public class TacGia {
    private int maTacGia;
    private String tenTacGia;

    public TacGia(){
        
    }

    public TacGia(int maTG, String tenTG){
        this.maTacGia = maTG;
        this.tenTacGia = tenTG;
    }
    public TacGia(TacGia tacgia){
        this.maTacGia = tacgia.getMaTacGia();
        this.tenTacGia = tacgia.getTenTacGia();
    }
    public int getMaTacGia() {
        return maTacGia;
    }

    public void setMaTacGia(int maTacGia) {
        this.maTacGia = maTacGia;
    }

    public String getTenTacGia() {
        return tenTacGia;
    }

    public void setTenTacGia(String tenTacGia) {
        this.tenTacGia = tenTacGia;
    }
    
}
