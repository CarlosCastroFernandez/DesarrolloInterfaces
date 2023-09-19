package org.example;


import org.clases.Busqueda;

import java.util.ArrayList;
import java.util.Scanner;

public class App
{

    public static void main( String[] args ) {
        Scanner sc = new Scanner(System.in);
        System.out.println("Hello World!");
        char respuesta;

        do {
            System.out.println("Elije el primer numero");
            int numero1 = Integer.parseInt(sc.nextLine());
            System.out.println("Elije el segundo numero");
            int numero2 = Integer.parseInt(sc.nextLine());
            ArrayList<Integer> numerosPrimos = Busqueda.busquedaPrimos(numero1, numero2);
            try {
                System.out.println("Buscando Primos...");
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            for (int i = 0; i < numerosPrimos.size(); i++) {
                System.out.println("Numero primo: "+numerosPrimos.get(i));
            }
            System.out.println("Se han encontrado "+numerosPrimos.size()+" Numeros Primos en el intervalo");
            System.out.println("¿Desea elejir otro intervalo? (si/no)");
            respuesta = sc.nextLine().toLowerCase().charAt(0);
        } while (respuesta == 's');


    }
}
