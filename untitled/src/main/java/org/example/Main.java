package org.example;

import java.io.*;

public class Main {
    public static void main(String[] args)
    {
        Usuario nuevo=new Usuario();
        Usuario usuarioCopiado=new Usuario();
        try (ObjectOutputStream escritura=new ObjectOutputStream(new FileOutputStream("datos.obj"))){
            escritura.writeObject(nuevo);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        try(ObjectInputStream leer=new ObjectInputStream(new FileInputStream("datos.obj"))){
           usuarioCopiado= (Usuario) leer.readObject();
        }catch(Exception e){

        }
        System.out.println(usuarioCopiado);
        System.out.println("Hello world!");
    }
}