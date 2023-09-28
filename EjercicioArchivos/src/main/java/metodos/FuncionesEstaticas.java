package metodos;

import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

public class FuncionesEstaticas {

    public static void clienteOutput(File datosClientes,File template){
        File datos=new File("./Carpeta");
        File salida=new File(datos.getParent()+File.separator+"salida");
        File principal=template;
        File csv=datosClientes;
        String numeros="0123456789";
        Integer tamaño=1;
        Integer filas=0;
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

                            } else if (q == textito.length() - 1 && contadorcito == 0) {
                                textitoCopy += textito + "\n";


                            }

                        }
                    }


                FileWriter escrituraCliente=new FileWriter(datos.getParent()+File.separator+"salida"+File.separator+hijos[i].getName());
                escrituraCliente.write(textitoCopy);
                escrituraCliente.flush();
                escrituraCliente.close();
                textitoCopy="";

            } catch (FileNotFoundException ex) {
            throw new RuntimeException(ex);
        } catch (IOException ex) {
            throw new RuntimeException(ex);
        }catch(NullPointerException e){
            System.err.println("Este cliente no esta en la lista de datos de clientes");
        }
        }

    }
    public static void SalidaOBorrado() {
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
