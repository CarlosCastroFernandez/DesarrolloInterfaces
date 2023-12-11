package metodos;

import errores.MarcadorIncompatible;

import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

/**
 *Clase en la que hay una serie de funciones estaticas
 */
public class FuncionesEstaticas {
    /**
     * Funcin estatica llamada clienteOutput en la que mete los datos de un fichero en un nuevo fichero
     * especifico para ese cliente
     * @param datosClientes variable en la que se especifica la ruta de lista de clientes
     * @param template variable en la que se especifica la ruta del fichero donde estan los datos a escribir en los
     *                 nuevos ficheros segun los clientes que hayan
     */
    public static void clienteOutput(File datosClientes,File template){
        File datos=new File("./Carpeta");
        File salida=new File(datos.getParent()+File.separator+"salida");
        File principal=template;
        File csv=datosClientes;
        String numeros="0123456789";
        String numeroGuardado="";
        String textitoCopy="";
        String textito="";
        String[]filaGuardada;
        Integer contadorcito=0;
        File[]hijos=salida.listFiles();
        BufferedReader leer2 = null;

        for(int i=0;i<hijos.length;i++) {
            numeroGuardado=hijos[i].getName();
            numeroGuardado=numeroGuardado.substring(numeroGuardado.indexOf("-")+1,numeroGuardado.indexOf("."));
            try {
                leer2 = new BufferedReader(new FileReader(csv));
                String textazo = leer2.readLine();
                String numeroComparador=textazo.substring(0,textazo.indexOf(","));
                while(!(numeroComparador.equals(numeroGuardado))){
                    textazo= leer2.readLine();
                    numeroComparador=textazo.substring(0,textazo.indexOf(","));

                }
                filaGuardada = textazo.split(",");

                BufferedReader leer3 = new BufferedReader(new FileReader(principal));
                    while ((textito = leer3.readLine()) != null) {
                        contadorcito = 0;
                        for (int q = 0; q < textito.length(); q++) {
                            if (numeros.contains(textito.charAt(q) + "")) {
                                contadorcito++;
                                Integer numeroG = Integer.parseInt(textito.charAt(q) + "");
                                textitoCopy += textito.replace("%%" + numeroG + "%%", filaGuardada[numeroG - 1]) + "\n";
                                if(textitoCopy.contains(""+numeroG)){
                                    try {
                                        throw new MarcadorIncompatible("Marcador incompatible en archivo Template.txt");
                                    } catch (MarcadorIncompatible e) {
                                        throw new RuntimeException(e);
                                    }
                                }

                            } else if (q == textito.length() - 1 && contadorcito == 0) {
                                textitoCopy += textito + "\n";


                            }

                        }
                    }

                try(BufferedWriter escritura=new BufferedWriter(new FileWriter(datos.getParent()+File.separator+"salida"+File.separator+hijos[i].getName()))){
                    escritura.write(textitoCopy);
                    textitoCopy="";
                }catch(Exception e){
                    e.printStackTrace();
                }
               /* FileWriter escrituraCliente=new FileWriter(datos.getParent()+File.separator+"salida"+File.separator+hijos[i].getName());
                escrituraCliente.write(textitoCopy);
                escrituraCliente.flush();
                escrituraCliente.close();
                textitoCopy="";*/

            } catch (FileNotFoundException ex) {
            throw new RuntimeException(ex);
        } catch (IOException ex) {
            throw new RuntimeException(ex);
        }catch(NullPointerException e){
            System.err.println("Este cliente no esta en la lista de datos de clientes");
        }
        }

    }

    /**
     * Metodo en el que se le muestra al usuario si quiere borrar o no la carpeta que se crea
     * con todos los ficheros de los clientes ya escritos en ellos
     */
    public static void SalidaOBorrado() {
        Scanner sc = new Scanner(System.in);
        File comprobacion = new File("." + File.separator + "salida");
        if (comprobacion.exists() && comprobacion.isDirectory()) {
            System.out.println("Menu informativo con importancia:" +
                    "\n\t1 - Presione 1 para borrar los datos de carpeta salida" +
                    "\n\t2 - Presione 2 para no borrar los datos de carpeta salida");
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

    /**
     * Metodo en el que solo se utiliza una vez para crear los ficheros que necesitamos como por ejemplo
     * el datosClientes.csv y el Template.txt..
     * @return devuelve la carpeta raiz donde van los dos ficheros tanto el datosClientes.csv y el Template.txt
     */
    public static File getFile() {
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

    /**
     * En este metodo se lee el datosClientes.csv para saber cuantos clientes hay y en base a esto va creando nuevos
     * ficheros de texto para estos clientes, `por ejemplo si hay un cliente de id 100 se creara en una carpeta
     * llamada salida un fichero de texto template-100.txt donde ira la informacion de este cliente en especifico.
     * @param datos se le pasa la carpeta raiz donde en base a esta se va escribiendo  la nueva ruta
     * @throws IOException maneja una excepcion si algo falla en la lectura de los archivos
     */
    public static void lecturaClientesYTemplate(File datos) throws IOException {
        String[]clientes;
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
        clientes=linea.split(",");
        if (linea != null) {
            contador++;
            template = new File(datos.getParent() + File.separator + carpeta.getName() + File.separator + "Template-"+clientes[0] + ".txt");
            template.createNewFile();
        }
        while (linea != null) {
            texto += linea + "\n";
            contador++;
            linea = leer.readLine();
            if (linea != null) {
                clientes=linea.split(",");
                template = new File(datos.getParent() + File.separator + carpeta.getName() + File.separator + "Template-"+clientes[0]  + ".txt");
                template.createNewFile();
            }
        }
    }
}
