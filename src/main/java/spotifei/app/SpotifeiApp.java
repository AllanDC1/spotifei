/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package spotifei.app;

import com.formdev.flatlaf.FlatDarkLaf;
import spotifei.view.LoginFrame;

/**
 * Classe principal para execução do sistema.
 * 
 *
 *
 */
public class SpotifeiApp {
    public static void main(String[] args) {
        FlatDarkLaf.setup();
        
        LoginFrame loginFrame = new LoginFrame();
        loginFrame.setVisible(true);     
    }
}