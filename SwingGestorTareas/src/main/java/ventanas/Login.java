package ventanas;

import org.example.Sesion;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Login extends JFrame {
    private JPanel panel;
    private JTextField textField1;
    private JButton entrarButton;
    private JLabel labelCOntra;
    private JPasswordField passwordField1;
    private JPanel panelFondo;
    private JLabel error;

    public Login(){
        this.setContentPane(panel);
        this.setTitle("Gestor Tareas");
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.setLocationRelativeTo(null);
        this.setSize(350,350);
        this.setVisible(true);
        entrarButton.addActionListener(e -> loginUser());
    }
    private void loginUser(){
        String usuario=textField1.getText();
        String contraseña=new String(passwordField1.getPassword());
        validateUser(usuario, contraseña);
    }

    private Boolean validateUser(String usuario, String contraseña) {
        Boolean validate=null;
        if(usuario.equals("Francisco")&& contraseña.equals("1234")){
            System.out.println("perfecto estas dentro");
            validate=true;
            JOptionPane.showMessageDialog(null,"Bienvenido "+usuario,"Succesfull",JOptionPane.INFORMATION_MESSAGE);
            panelFondo.setBackground(Color.GREEN);
            Sesion.setUser(usuario);
            this.dispose();
            Principal ventanaPrincipal=new Principal();
            ventanaPrincipal.setVisible(true);
        }else{
            validate=false;
            panelFondo.setBackground(Color.RED);
            JOptionPane.showMessageDialog(null,"Usuario o Contraseña Invalida","Error",JOptionPane.ERROR_MESSAGE);
        }
        return validate;
    }


}
