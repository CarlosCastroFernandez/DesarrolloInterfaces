package ventanas;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Prueba extends JPanel {

    private JPanel panel1;
    private ventana v;
    private JLabel labelUsuario;
    private JTextField textField1;
    private JTextField passwordField1;
    private JButton button1;

    public Prueba(ventana v) {

        labelUsuario=new JLabel("Usuario");
        textField1=new JTextField();
        textField1.setColumns(40);
        JLabel contraseña=new JLabel("Password");
        passwordField1=new JTextField();
        passwordField1.setColumns(40);
        button1=new JButton("haz click");
        JTextField resultado=new JTextField();
        resultado.setColumns(40);
        resultado.setEditable(false);
        this.v=v;
        button1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                Integer numero1=Integer.parseInt(textField1.getText());
                Integer numero2=Integer.parseInt(passwordField1.getText());
                resultado.setEditable(true);
                resultado.setText((numero1+numero2)+"");
                resultado.setEditable(false);
                JOptionPane.showMessageDialog(v,"Esto es una prueba","Guay",
                        JOptionPane.INFORMATION_MESSAGE);
            }
        });
        this.add(labelUsuario);
        this.add(textField1);
        this.add(contraseña);
        this.add(passwordField1);
        this.add(button1);
        this.add(resultado);
    }

}
