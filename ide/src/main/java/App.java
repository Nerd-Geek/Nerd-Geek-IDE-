import com.formdev.flatlaf.FlatDarkLaf;
import com.formdev.flatlaf.FlatLightLaf;

import javax.swing.*;

public class App {
    public static void main(String[] args) {
        //Crear un hilo independiente para la vista
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                FlatDarkLaf.setup();
                new Vista().setVisible(true);
            }
        });
    }
}
