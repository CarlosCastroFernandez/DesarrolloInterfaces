package ventanas;

import javax.swing.*;

public class ventana extends JFrame {
    public ventana(){
        this.setTitle("Prueba");
        this.setContentPane(new Prueba(this));
        this.setSize(300,400);
        this.setLocationRelativeTo(null);
        this.setVisible(true);
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
    }
    public  void cambioVentana(Class<?>clases){
        this.getContentPane().setVisible(false);
        if(clases.equals(Prueba.class)){
            this.setContentPane(new Prueba(this));
        }
        this.getContentPane().setVisible(true);
    }
}
