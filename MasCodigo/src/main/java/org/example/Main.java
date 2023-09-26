package org.example;

import clases.Error;
import clases.Persona;
import ventanas.ventana;

import javax.swing.*;
import java.util.Scanner;

public class Main {

    public record Intervalo(Integer init, Integer fin) {
        public Integer tamaño() {
            return fin - init;
        }
    }

    public static Intervalo expandir(Intervalo i) {
        return new Intervalo(i.init - 1, i.fin + 1);
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        Intervalo i1 = new Intervalo(10, 20);
        System.out.println(i1);
        Intervalo i2 = expandir(i1);
        System.out.println(i2);
        System.out.println(i2.tamaño());
        Boolean variable = false;
        while (!variable) {
            try {
                System.out.println("Dime su apellido pleased");
                String apellido=sc.nextLine().toLowerCase();
                apellido.strip();
                apellido=apellido.toUpperCase().charAt(0)+ apellido.substring(1,apellido.length());
                Persona p1 = new Persona("Carlos", apellido, 12);
                variable = true;
            } catch (Error e) {
                e.printStackTrace();
                System.err.println(e.getMessage());
            }
            ventana v=new ventana();

        }

     /*   try {
            Persona p2=new Persona("Naomi","Camuña",13);
        } catch (Error e) {
            throw new RuntimeException(e);
        }


        Persona p3= null;
        try {
            p3 = Persona.builder().nombre("Pepe").build();
        } catch (Error e) {
            throw new RuntimeException(e);
        }
        System.out.println(p3);

        System.out.println(p3);
        //Hola
    }*/
    }
}