package com.romanceramic.mrp;

import com.romanceramic.mrp.view.LoginView;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

public class Main {
    public static void main(String[] args) {
        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch (ClassNotFoundException | InstantiationException |
                IllegalAccessException | UnsupportedLookAndFeelException ex) {
            System.err.println("Error setting Look and Feel: " + ex.getMessage());
        }
        
        SwingUtilities.invokeLater(() -> {
            new LoginView().setVisible(true);
        });
    }
}
