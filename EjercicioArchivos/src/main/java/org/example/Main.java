package org.example;

import clases.Cliente;

import javax.xml.transform.Templates;
import java.io.*;

public class Main {
    public static void main(String[] args) {

        File datos = getFile();
        try {
            //escrituraDatosClientes(datos);
            lecturaClientesYTemplate(datos);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }


        Cliente nuevo = new Cliente("Template-104.txt");
        System.out.println(nuevo.clienteOutput(nuevo.getNombre()));

    }

    private static void lecturaClientesYTemplate(File datos) throws IOException {
        Integer contador = 0;
        String linea = "";
        String texto = "";
        File template;
        BufferedReader leer = new BufferedReader(new FileReader(datos.getParent() + File.separator + datos.getName() + File.separator + "datosClientes.csv"));

        linea = leer.readLine();
        if (linea != null) {
            contador++;
            template = new File(datos.getParent() + File.separator + datos.getName() + File.separator + "Template-10" + contador + ".txt");
            template.createNewFile();
        }
        while (linea != null) {
            texto += linea + "\n";
            contador++;
            linea = leer.readLine();
            if (linea != null) {
                template = new File(datos.getParent() + File.separator + datos.getName() + File.separator + "Template-10" + contador + ".txt");
                template.createNewFile();
            }
        }
    }

    /*private static void escrituraDatosClientes(File datos) throws IOException {
        FileWriter escritura = new FileWriter(datos.getParent() + File.separator + datos.getName() + File.separator + "datosClientes.csv",false);

        escritura.write("101,EcoTech Solutions,Barcelona,ecotech@example.com,Carlos González\n" +

                "102,InnovaSoft,Sevilla,innovasoft@example.com,Marta López\n" +

                "103,GlobalTech,Valencia,globaltech@example.com,Andrés Martínez\n" +

                "104,ElectroMasters,Madrid,electromasters@example.com,Sofía Pérez");
        escritura.flush();
        escritura.close();
    }*/

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
}