package org.example;

import java.io.*;
import java.util.Arrays;
import java.util.Iterator;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {

      /*File archivo=new File(".");
      //Iterator it= Arrays.stream(archivo.list()).iterator();

            File[]hijos=archivo.listFiles();
            for(int i=0;i<hijos.length;i++){
                if(hijos[i].isDirectory()){
                    System.out.print("D ");
                }else{
                    System.out.print("F ");
                }
                System.out.print(hijos[i].getName()+" ");
                System.out.print(hijos[i].length()+" ");
                System.out.print(archivo.lastModified());
                System.out.println();
            }*/
       /* try {
            FileReader leer=new FileReader("./pom.xml");
            try {
                Integer tamaño=0;
                int c=leer.read();
                while(c!=-1){
                    System.out.println(c);
                    tamaño++;
                    c=leer.read();
                }
                leer.close();
                System.out.println(tamaño);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }

        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }*/
        /*try {
            BufferedReader leer=new BufferedReader(new FileReader("./carpetita/texto.txt"));
            BufferedWriter escribir=new BufferedWriter(new FileWriter("pom2.txt"));
            try {
                String letura=leer.readLine();
                String texto="";
                while(letura!=null){
                    texto+=letura+"\n";
                    letura= leer.readLine();
                    System.out.println(texto);

                }

            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        File archivo=new File("./carpetita/texto.txt");

        if(archivo.exists()){

        }else{
            try {
                archivo.createNewFile();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }

        try {
            Scanner sc=new Scanner(new File("./carpetita/texto.txt"));
            while(sc.hasNext()){
                System.out.println(sc.next());
            }
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }*/

        try {
            BufferedReader leer=new BufferedReader(new FileReader("./carpetita/texto.txt"));
            String linea=leer.readLine();
            String texto="";
            while(linea!=null){
                texto+=linea.toLowerCase()+"\n";
                linea=leer.readLine();
            }
            leer.close();
            FileWriter escritura=new FileWriter("./textoCopy.txt");
            if(texto.contains("a")||texto.contains("e")||texto.contains("i")||texto.contains("0")||texto.contains("u")){
               texto= texto.replaceAll("[aeiou]","");
            }
            escritura.write(texto);
            escritura.flush();
            escritura.close();
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}