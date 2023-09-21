package org.example;

import Clases.PrimoUtilities;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

public class Main {
    public static void main(String[] args) {
        byte numero=0;
        byte numero2=0;
        for(byte i=0;i<args.length;i++){
           if(i==0){
               numero=Byte.parseByte(args[i]);
           }else if(i==1){
               numero2=Byte.parseByte(args[i]);
           }
        }
        if(numero!=0&&numero2!=0){
            System.out.println("Enhorabuena");
        }
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