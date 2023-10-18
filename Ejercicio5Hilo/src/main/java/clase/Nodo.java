package clase;

import java.util.ArrayList;

public class Nodo extends Thread {
    private ArrayList<Integer>lista;
    public Nodo(ArrayList<Integer>list){
        lista=list;
    }

    @Override
    public void run() {

    }
    public synchronized void añade(ArrayList<Integer>lista,Integer desde,Integer hasta){
        ArrayList<Integer>mitad=new ArrayList<Integer>();
        ArrayList<Integer>masMitad=new ArrayList<Integer>();
        for(Integer i=0;i<lista.size()/2;i++){
            mitad.add(lista.get(i));
        }
        for(Integer i=lista.size()/2;i<lista.size();i++){
            masMitad.add(lista.get(i));
        }
    }
    public synchronized void mezcla(){


    }
}
