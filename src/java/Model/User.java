/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Model;



/**
 *
 * @author admin
 */
public class User {
    private String username;
    private String password;
    private String hoTen;
    private String email;
    private String role;
    public User(String a, String b, String c, String d, String f){
        this.username = a;
        this.password = b;
        this.hoTen = c;
        this.email = d;
        this.role = f;
    }

    public User() {
    }
    

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getHoTen() {
        return hoTen;
    }

    public void setHoTen(String hoTen) {
        this.hoTen = hoTen;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }
    @Override
    public String toString(){
        return "Username: " + username + "\nHo ten: " + hoTen + "\nEmail: " + email+ "\nPassword: " + password+ "\nRole: " + role ;
    }
}
