package utu;

import utu.vista.Login;

public class App {
    public static void main(String[] args) {
        System.out.println("Bienvenido al sistema del hospital");
        
        javax.swing.SwingUtilities.invokeLater(() -> {
            new Login().setVisible(true);
        });
    }

}
