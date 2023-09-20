package org.example;

import Clases.PrimoUtilities;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

public class Main {
    public static void main(String[] args) {
        System.out.println(PrimoUtilities.esPrimo(5));
        System.out.println(PrimoUtilities.esPrimo(4));

        var p=PrimoUtilities.primoIntervalo(1,10);


        imprimir(p);

    }

    private static void imprimir(ArrayList<Integer> p) {
        Iterator<Integer> it= p.iterator();
        while(it.hasNext()){
            Integer numero=it.next();
            System.out.println(numero);
        }
        var params=new HashMap<String,String>();
        params.put("hola","hola");
        params.put("Carlos","Castro");
        Iterator itn=params.entrySet().iterator();
        while (itn.hasNext()){
            Map.Entry nuevo= (Map.Entry) itn.next();
            System.out.println(nuevo.getKey()+" "+nuevo.getValue());
        }
    }
}