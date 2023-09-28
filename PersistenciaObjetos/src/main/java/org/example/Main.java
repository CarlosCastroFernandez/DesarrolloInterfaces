package org.example;

import com.fasterxml.jackson.databind.ObjectMapper;
import model.Alumno;
import model.clase;

import java.io.*;
import java.util.ArrayList;
import java.util.Properties;

public class Main {
    public static void main(String[] args) {
        System.out.println("Hello world!");

        //Antes de guardar el archivo dataBase.properties en resources hay que comprobar
        /*que este todo guardado ya que dentro de esta carpeta no se puede modificar
        *por lo tanto toda la parte del config.put sobra si ya esta guardado en resources           */

        /*InputStream is=Main.class.getClassLoader().getResourceAsStream("./src/main/resources/dataBase.properties");
        Properties config=new Properties();
        try {
            config.load(is);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        config.put("version","1.0");
        try {
            config.store(new FileWriter("./dataBase.properties"),"Añadida la version");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        System.out.println(config.get("url"));
        System.out.println(config.get("host"));
        System.out.println(config.get("port"));
        System.out.println(config.get("user"));
        System.out.println(config.get("password"));
        System.out.println(config.get("version"));*/

        clase dam2=new clase();
        Alumno fer=new Alumno();
        fer.setNombre("Fernando");
        fer.setApellido("Perez");
        Alumno pablo=new Alumno();
        pablo.setNombre("Pablo");
        pablo.setApellido("Postigo");
        var clase=new ArrayList<Alumno>();
        clase.add(pablo);
        clase.add(fer);
        System.out.println(clase);

        guardarClase(clase);
        ArrayList<Alumno> clase2=new ArrayList<Alumno>();
        clase2 = leerClase();
        dam2.setAlumnos(clase2);
        System.out.println(clase2);
        //Mapeo de objetos
        var mapper=new ObjectMapper();
       try {
            mapper.writeValue(new File("clase.json"),dam2);
            System.out.println(mapper.writeValueAsString(dam2));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        try {
            clase dam2n=  mapper.readValue(new File("clase.json"),clase.class);
            System.out.println(mapper.writerWithDefaultPrettyPrinter().writeValueAsString(dam2n));
            System.out.println(dam2n);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private static void guardarClase(ArrayList<Alumno> clase) {
        try {
            ObjectOutputStream os=new ObjectOutputStream(new FileOutputStream("clase.obj"));
            os.writeObject(clase);
            os.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private static ArrayList<Alumno> leerClase() {
        ArrayList<Alumno> clase2;
        try {
            ObjectInputStream ois=new ObjectInputStream(new FileInputStream("clase.obj"));
            clase2=(ArrayList<Alumno>) ois.readObject();
            ois.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
        return clase2;
    }
}