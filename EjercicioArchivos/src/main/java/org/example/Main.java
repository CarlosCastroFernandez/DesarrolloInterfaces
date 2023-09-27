package org.example;

import clases.Cliente;

import javax.xml.transform.Templates;
import java.io.*;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        File datos = getFile();
        try {
            escrituraDatosClientes(datos);
            lecturaClientesYTemplate(datos);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        Cliente nuevo = new Cliente("Template-102.txt");
        System.out.println(nuevo.clienteOutput(nuevo.getNombre()));
        Main.SalidaOBorrado();
    }


    private static void lecturaClientesYTemplate(File datos) throws IOException {
        Integer contador = 0;
        String linea = "";
        String texto = "";
        File template;
        File carpeta = new File("." + File.separator + "salida");
        if (!carpeta.exists()) {
            carpeta.mkdir();
        }
        BufferedReader leer = new BufferedReader(new FileReader(datos.getParent() + File.separator + datos.getName() + File.separator + "datosClientes.csv"));

        linea = leer.readLine();
        if (linea != null) {
            contador++;
            template = new File(datos.getParent() + File.separator + carpeta.getName() + File.separator + "Template-10" + contador + ".txt");
            template.createNewFile();
        }
        while (linea != null) {
            texto += linea + "\n";
            contador++;
            linea = leer.readLine();
            if (linea != null) {
                template = new File(datos.getParent() + File.separator + carpeta.getName() + File.separator + "Template-10" + contador + ".txt");
                template.createNewFile();
            }
        }
    }

    private static void escrituraDatosClientes(File datos) throws IOException {
        FileWriter escritura = new FileWriter(datos.getParent() + File.separator + datos.getName() + File.separator + "datosClientes.csv", false);

        escritura.write("101,EcoTech Solutions,Barcelona,ecotech@example.com,Carlos González\n" +

                "102,InnovaSoft,Sevilla,innovasoft@example.com,Marta López\n" +

                "103,GlobalTech,Valencia,globaltech@example.com,Andrés Martínez\n" +

                "104,ElectroMasters,Madrid,electromasters@example.com,Sofía Pérez");
        escritura.flush();
        escritura.close();
    }

    private static File getFile() {
        File datos = new File("./Carpeta");
        File csv = new File(datos.getParent() + File.separator + datos.getName() + File.separator + "datosClientes.csv");
        File principal = new File(datos.getParent() + File.separator + datos.getName() + File.separator + "Template.txt");
        if (!datos.exists()) {
            datos.mkdir();

        }
        try {
            if (!(csv.exists())) {
                csv.createNewFile();
            }
            if (!(principal.exists())) {
                principal.createNewFile();
            }
        } catch (IOException e) {

        }
        return datos;
    }

    private static void SalidaOBorrado() {
        Scanner sc = new Scanner(System.in);
        File comprobacion = new File("." + File.separator + "salida");
        if (comprobacion.exists() && comprobacion.isDirectory()) {
            System.out.println("Si desea borrar todos los archivos de la carpeta de salida seleccione 1" +
                    "\nSi desea salir del programa seleccione 2");
            Byte seleccion = Byte.parseByte(sc.nextLine());
            while(seleccion!=2&&seleccion!=1){
                System.out.println("Porfavor seleccione 1 para borrar la carpeta de salida\n y selecciona 2 para salir del programa sin borrar");
                seleccion = Byte.parseByte(sc.nextLine());
            }
            switch (seleccion) {
                case 1:
                    File[] hijos = comprobacion.listFiles();
                    for (int i = 0; i < hijos.length; i++) {
                        hijos[i].delete();
                    }
                    break;
                case 2:
                    System.exit(0);
                    break;
            }
        }
    }
}
