/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Controller;

import java.util.regex.Pattern;

/**
 *
 * @author admin
 */
public class ValidateController {

    public static boolean isValidUsername(String username) {
        String USERNAME_PATTERN = "^[a-z]{3,15}$";
        Pattern pattern = Pattern.compile(USERNAME_PATTERN);
        return pattern.matcher(username).matches();
    }

    public static boolean isValidPassword(String password) {
        String PASSWORD_PATTERN = "^(?=.*\\d)(?=.*[a-zA-Z\\S])[0-9a-zA-Z\\S]{3,15}$";
        Pattern pattern = Pattern.compile(PASSWORD_PATTERN);
        return pattern.matcher(password).matches();
    }

    public static void main(String[] args) {
        if (ValidateController.isValidUsername("avc") && ValidateController.isValidPassword("Passw0rd@")) {
            System.out.println("Controller.ValidateController.main()");
        } else {
            System.out.println("c)");

        }
    }

}
