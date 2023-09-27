package org.example;

import metodos.FuncionesEstaticas;

import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;


public class Main {
    public static void main(String[] args) {
        File datos = FuncionesEstaticas.getFile();
        try {
            //escrituraDatosClientes(datos);
            FuncionesEstaticas.lecturaClientesYTemplate(datos);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        ArrayList<String>nombres=new ArrayList<>();
        nombres.add("Template-1.txt");
        nombres.add("Template-2.txt");
        nombres.add("Template-3.txt");
        FuncionesEstaticas.clienteOutput(nombres);
        FuncionesEstaticas.SalidaOBorrado();
    }

}
