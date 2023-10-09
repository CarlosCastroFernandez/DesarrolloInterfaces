package org.example;

import metodos.FuncionesEstaticas;

import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;


/**
 * Clase main donde ira las sentencias de ejecucion del programa
 */
public class Main {
    /**
     * Metodo principal donde correra el hilo principal del programa
     * @param args recibe argumentos de programa que en este caso no hay.
     */
    public static void main(String[] args) {
        File datos = FuncionesEstaticas.getFile();
        try {
            //escrituraDatosClientes(datos);
            FuncionesEstaticas.lecturaClientesYTemplate(datos);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        FuncionesEstaticas.clienteOutput(new File("."+File.separator+"Carpeta"+File.separator+"datosClientes.csv"),
                new File("."+File.separator+"Carpeta"+File.separator+"Template.txt"));
        FuncionesEstaticas.SalidaOBorrado();
    }

}
