package com.kelompok5.kelompok5app;

import com.kelompok5.kelompok5app.view.DasboardAdmin;

public class main {
    public static void main(String[] args) {
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new DasboardAdmin().setVisible(true);
            }
        });
    }
}
