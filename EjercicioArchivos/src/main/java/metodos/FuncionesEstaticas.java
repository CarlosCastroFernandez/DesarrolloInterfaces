package metodos;

import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;

public class FuncionesEstaticas {

    public static void clienteOutput(ArrayList<String> nombre){
        File datos=new File("./Carpeta");
        File principal=new File(datos.getParent()+File.separator+datos.getName()+File.separator+"Template.txt");
        File csv=new File(datos.getParent()+File.separator+datos.getName()+File.separator+"datosClientes.csv");
        String numeros="0123456789";
        Integer tamaño=1;
        Integer filas=0;
        String textitoCopy="";
        String textito="";
        String[]filaGuardada;
        Integer contadorcito=0;

        for(int i=0;i<nombre.size();i++) {
            while (!(nombre.get(i)).contains(tamaño + "")) {
                tamaño++;
            }


            try {
                BufferedReader leer2 = new BufferedReader(new FileReader(datos.getParent() + File.separator + datos.getName() + File.separator + csv.getName()));
                String textazo = leer2.readLine();
                while (!textazo.contains(tamaño + "")) {
                    textazo = leer2.readLine();
                }
                if (textazo.contains(tamaño + "") && textazo != null) {
                    filaGuardada = textazo.split(",");
                    BufferedReader leer3 = new BufferedReader(new FileReader(datos.getParent() + File.separator + datos.getName() + File.separator + principal.getName()));
                    while ((textito = leer3.readLine()) != null) {
                        contadorcito = 0;
                        for (int q = 0; q < textito.length(); q++) {
                            if (numeros.contains(textito.charAt(q) + "")) {
                                contadorcito++;
                                Integer numeroG = Integer.parseInt(textito.charAt(q) + "");
                                textitoCopy += textito.replace("%%" + numeroG + "%%", filaGuardada[numeroG - 1]) + "\n";

                            } else if (i == textito.length() - 1 && contadorcito == 0) {
                                textitoCopy += textito + "\n";


                            }

                        }
                    }

                }
                FileWriter escrituraCliente=new FileWriter(datos.getParent()+File.separator+"salida"+File.separator+nombre.get(i));
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
