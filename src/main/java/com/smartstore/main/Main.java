package com.smartstore.main;

import com.smartstore.ui.LoginFrame;
import javax.swing.SwingUtilities;

public class Main {
    public static void main(String[] args) {
        // GUI ko threat-safe chalane k liye safe tarika
        SwingUtilities.invokeLater(() -> {
            new LoginFrame().setVisible(true);
        });
    }
}