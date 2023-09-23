package org.example;

import clases.Persona;

public class Main {

    public  record Intervalo(Integer init,Integer fin){
        public Integer tamaño(){
            return fin-init;
        }
    }
    public static Intervalo expandir(Intervalo i){
        return new Intervalo(i.init-1,i.fin+1);
    }
    public static void main(String[] args) {
        Intervalo i1=new Intervalo(10,20);
        System.out.println(i1);
        Intervalo i2=expandir(i1);
        System.out.println(i2);
        System.out.println(i2.tamaño());

        Persona p1=new Persona("Carlos","Castro",12);
        Persona p2=new Persona("Naomi","Camuña",13);
        p2.setApellido("Loca");

        Persona p3=Persona.builder().nombre("Pepe").build();
        System.out.println(p3);
        p3.setApellido("facil");
        System.out.println(p3);
        //Hola
    }
}