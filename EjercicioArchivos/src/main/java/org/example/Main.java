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

        FuncionesEstaticas.clienteOutput(new File("./Carpeta/datosClientes.csv"),new File("./Carpeta/Template.txt"));
        FuncionesEstaticas.SalidaOBorrado();
    }

}
