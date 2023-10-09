package ventanas;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

/**
 * Clase con nombre VentanaCalculadora donde se muestra una ventana ya que se hereda
 * de JFrame y dentro de esta hacemos uso de sus componentes.
 * @Author: Carlos Castro Fernandez
 */

public class VentanaCalculadora extends JFrame {
    /** variable global llamada panelPrincipal que tendra como layout BorderLayout*/
    private JPanel panelPrincipal;
    /** Variable que contiene un JtextField para mostrar los resultados de las operaciones*/
    private JTextField txtResultado;
    /** variable global llamada panelEscritura instalada en el norte
     * del panelPrincipal que tendra como layout GridBagLayout*/
    private JPanel panelEscritura;
    /** variablel Global llmada labelEscritura donde el usuario podra ver los calculos que ejecutará*/
    private JLabel labelEscritura;
    /** variable global llamada panelEscritura instalada en el centro
     * del panelPrincipal que tendra como layout FlowLayout*/
    private JPanel panelBotones;
    /** Variable global llamada boton7 que contiene un JButton y representa al numero 7 de
     * la calculadora*/
    private JButton boton7;
    /** Variable global llamada boton7 que contiene un JButton y representa al numero 5 de
     * la calculadora*/
    private JButton boton5;
    /** Variable global llamada boton7 que contiene un JButton y representa al numero 1 de
     * la calculadora*/
    private JButton boton1;
    /** Variable global llamada boton7 que contiene un JButton y representa al numero 2 de
     * la calculadora*/
    private JButton boton2;
    /** Variable global llamada boton7 que contiene un JButton y representa al numero 6 de
     * la calculadora*/
    private JButton boton6;
    /** Variable global llamada botonCe que contiene un JButton y tiene como ombjetivo
     * limpiar tanto el JTextField como el JLabel*/
    private JButton botonCe;
    /** Variable global llamada boton0 que contiene un JButton y representa al numero 0 de
     * la calculadora*/
    private JButton boton0;
    /** Variable global llamada boton9 que contiene un JButton y representa al numero 9 de
     * la calculadora*/
    private JButton boton9;
    /** Variable global llamada botonMultiplicar que contiene un JButton y representa la multiplicacionm de
     * la calculadora*/
    private JButton botonMultiplicar;
    /** Variable global llamada botonSumar que contiene un JButton y representa la suma de
     * la calculadora*/
    private JButton botonSumar;
    /** Variable global llamada boton3 que contiene un JButton y representa al numero 3 de
     * la calculadora*/
    private JButton boton3;
    /** Variable global llamada botonSumar que contiene un JButton y representa la resta de
     * la calculadora*/
    private JButton botonRestar;
    /** Variable global llamada botonSumar que contiene un JButton y representa el resultado de
     * operacion*/
    private JButton botonIgual;
    /** Variable global llamada botonComa que contiene un JButton y representa las comas para
     * los numeros decimales*/
    private JButton botonComa;
    /** Variable global llamada botonDividir que contiene un JButton y representa la division*/
    private JButton botonDividir;
    /** Variable global llamada boton4 que contiene un JButton y representa el numero 4*/
    private JButton boton4;
    /** Variable global llamada boton8 que contiene un JButton y representa el numero 8*/
    private JButton boton8;
    /** Variable global de String donde se va concatenando los numeros y los signos*/
    private String guardado;
    /** Variable global de Double llamada valor1 donde se almacena un primer numero para ser calculado posteriormente*/
    private Double valor1;
    /** Variable global de Double llamada valor 2 donde se almacena un primer numero para ser calculado con el valor1*/
    private Double valor2;
    /** Variable global llamada datos de un array de String donde se almacenaran dos numeros
     * para posteriormente parsearlos y hacer la respectiva operacion*/
    private String[]datos;
    /** Variable global de Double donde se almacena el resultado de las operaciones*/
    private Double operacion;

    /**
     * Constructor de la clase VentanaCalculadora donde se diseña la ventana
     * y los componenes que va a implementar
     */
    public VentanaCalculadora(){
    this.setContentPane(panelPrincipal);
    this.setTitle("Calculadora");
    this.setLocationRelativeTo(null);
    this.setDefaultCloseOperation(EXIT_ON_CLOSE);
    this.setSize(300,250);
    this.setResizable(false);
    this.setVisible(true);
    operacion=0.0;
    guardado="";

    boton7.addActionListener(e -> {

        guardado+="7";
        restriccionNumeros();

    });
    boton8.addActionListener(e -> {
        guardado+="8";
        restriccionNumeros();

    });
    botonSumar.addActionListener(e -> {
        guardado+="+";

        metodoSignos();

    });

    botonRestar.addActionListener(e -> {
        guardado+="-";
        metodoSignos();
    });
    botonIgual.addActionListener(e -> {
        resultadoObtenido();


    });
    boton9.addActionListener(e -> {
        guardado+="9";
        restriccionNumeros();

    });
    boton4.addActionListener(e -> {
        guardado+="4";
        restriccionNumeros();

    });
    boton5.addActionListener(e -> {
        guardado+="5";
        restriccionNumeros();

    });
    boton6.addActionListener(e -> {
        guardado+="6";
        restriccionNumeros();

    });
    boton1.addActionListener(e -> {
        guardado+="1";
        restriccionNumeros();

    });
    boton2.addActionListener(e -> {
        guardado+="2";
        restriccionNumeros();

    });
    boton3.addActionListener(e -> {
        guardado+="3";
        restriccionNumeros();

    });
    boton0.addActionListener(e -> {
        guardado+="0";
        restriccionNumeros();

    });
    botonComa.addActionListener(e -> {
        guardado+=".";
        estructuraBotonComa();
    });
    botonMultiplicar.addActionListener(e -> {
        guardado+="x";
        metodoSignos();
    });
    botonDividir.addActionListener(e -> {
        guardado+="/";
        metodoSignos();
    });
    botonCe.addActionListener(e -> {
        txtResultado.setText("");
        labelEscritura.setText("");
        guardado="";
    });
}

    /** Metodo que no recibe nada por parametros y no devuelve nada pero que sirve para calcular
     * el valor de las operaciones realzadas.*/
    private void resultadoObtenido() {
        if(labelEscritura.getText().contains("+")){
            datos=labelEscritura.getText().split("\\+");
            valor1=Double.parseDouble(datos[0]);
            valor2=Double.parseDouble(datos[1]);
            operacion=valor1+valor2;
            txtResultado.setText(operacion.toString());
        }else if(labelEscritura.getText().contains("-")){
            datos=labelEscritura.getText().split("-");
            valor1=Double.parseDouble(datos[0]);
            valor2=Double.parseDouble(datos[1]);
            operacion=valor1-valor2;
            txtResultado.setText(operacion.toString());
        }else if(labelEscritura.getText().contains("/")){
            datos=labelEscritura.getText().split("/");
            valor1=Double.parseDouble(datos[0]);
            valor2=Double.parseDouble(datos[1]);
            operacion=valor1/valor2;
            txtResultado.setText(operacion.toString());
        }else if(labelEscritura.getText().contains("x")){
            datos=labelEscritura.getText().split("x");
            valor1=Double.parseDouble(datos[0]);
            valor2=Double.parseDouble(datos[1]);
            operacion=valor1*valor2;
            txtResultado.setText(operacion.toString());
        }
        datos=new String[0];
        guardado=operacion.toString();
        labelEscritura.setText(operacion.toString());
    }

    /**
     * Metodo que no devuelve y no recibe nada por parametros pero que sirve
     * para comprobar que solamente puedan existir 1 o 2 comas como mucho sin que se
     * pase los datos de su longitud.
     */
    private void estructuraBotonComa() {
        if(labelEscritura.getText().contains("+")&&labelEscritura.getText().lastIndexOf(",")==0){
            datos=labelEscritura.getText().split("\\+");
            labelEscritura.setText(guardado);
            if(datos.length>2){
                labelEscritura.setEnabled(false);
            }
        }
        if(labelEscritura.getText()!=""){
            labelEscritura.setText(guardado);
        }
    }

    /**
     * Metodo que no recibe y no devuelve nada pero que sirve para comprobar la longitud
     * de los datos y si estos datos son mayores o igual a 2 no se pueda escribir mas en el JLabel
     * y si no es asi que si se pueda escribir
     */

    private void metodoSignos() {
        if(datos.length>=2){
            labelEscritura.setEnabled(false);
        }else{
            labelEscritura.setText(guardado);
        }
    }

    /**
     * Metodo donde se compruban que signo hay para saber que calculo hay que hacer
     */

    private void restriccionNumeros() {
        if(labelEscritura.getText().contains("+")||labelEscritura.getText().equals("")){
            labelEscritura.setText(guardado);
            datos=labelEscritura.getText().split("\\+");

            if(datos.length>2){
                labelEscritura.setEnabled(false);
            }
        } else if(labelEscritura.getText().contains("-")||labelEscritura.getText().equals("")){
            labelEscritura.setText(guardado);
            datos=labelEscritura.getText().split("-");

            if(datos.length>2){
                labelEscritura.setEnabled(false);
            }
        }else if(labelEscritura.getText().contains("x")||labelEscritura.getText().equals("")){
            labelEscritura.setText(guardado);
            datos=labelEscritura.getText().split("x");

            if(datos.length>2){
                labelEscritura.setEnabled(false);
            }
        }else if(labelEscritura.getText().contains("/")||labelEscritura.getText().equals("")){
            labelEscritura.setText(guardado);
            datos=labelEscritura.getText().split("/");

            if(datos.length>2){
                labelEscritura.setEnabled(false);
            }
        }
        if(labelEscritura.getText()!=""){
            labelEscritura.setText(guardado);
        }
    }
}
