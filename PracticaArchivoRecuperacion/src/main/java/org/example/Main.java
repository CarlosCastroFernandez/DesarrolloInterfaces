package org.example;

import java.io.*;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.util.ArrayList;

public class Main {
    public static void main(String[] args) {
    ArrayList<Alumno>alumnos=Alumno.listaAlumno();

     File prueba=new File("./");

     File[]array=prueba.listFiles();
     for(int i=0;i<array.length;i++){
         File hijoActual=array[i];
         if(hijoActual.isDirectory()){

         }else{
             if(hijoActual.getName().equals("archivo2.txt")){
                 File nuevaRuta=new File(hijoActual.getParent()+File.separator+hijoActual.getName());
                 Integer contador=0;
                 try (BufferedReader leer=new BufferedReader(new FileReader(nuevaRuta))){
                     String linea="";
                     String concatenado="";
                     while ((linea=leer.readLine())!=null){
                         contador++;
                         concatenado+=linea+"\n";
                     }
                     System.out.println(concatenado);
                     System.out.println("HAY "+contador+" lineas");
                     String[]partesArchivo=concatenado.split(" ");
                     String contenido="";


                 } catch (FileNotFoundException e) {
                     throw new RuntimeException(e);
                 } catch (IOException e) {
                     throw new RuntimeException(e);
                 }
             }
         }
     }



























       /* File archivo=new File("./noEsxisto.txt");
        System.out.println("Existe el archivo "+archivo.exists());// SI EXISTE TRUE O FALSE
        try {
            System.out.println("Pude crear el archivo: "+archivo.createNewFile());//SE CREA VACION
            System.out.println(archivo.getAbsolutePath());//RUTA ABSOLUTA DE ARCHIVO
            System.out.println(archivo.getName());//NOMBRE ARCHIVO CON LA EXTENSION
            System.out.println(archivo.getParent());//NOMBRE DE LA CARPETA MADRE
            System.out.println(archivo.isDirectory());//DICE SI ES UN DIRECTORIO
            System.out.println("Tamaño total del disco duro: "+archivo.getTotalSpace()/1024f/1024f/1024f);//TAMAÑO DISCO DURO TOTAL: DIVISION KB,MB,GB por lo tanto aqui sale GB
            System.out.println("Tamaño libre del disco: "+archivo.getFreeSpace()/1024f/1024F/1024f);//ESPACIO LIBRE
            System.out.println("tamaño del archivo en bytes: "+archivo.length());
            System.out.println("Fecha de modificacion: "+archivo.lastModified());
            LocalDateTime ultimaMod=LocalDateTime.ofEpochSecond(archivo.lastModified()/1000, 0, ZoneOffset.systemDefault().getRules().getOffset(Instant.now()));
            System.out.println("Fecha modificacion bien escrita: "+ultimaMod);

            System.out.println("Puedo Borrarlo: "+archivo.delete());//PARA BORRAR EL ARCHIVO

        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        System.out.println("Hello world!");*/
    }
}