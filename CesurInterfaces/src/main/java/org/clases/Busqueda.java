package org.clases;

import java.util.ArrayList;

public class Busqueda {
    private int intervalo1;
    private int intervalo2;
    public Busqueda(int intervalo1,int intervalo2){
        this.intervalo1=intervalo1;
        this.intervalo2=intervalo2;
    }

    public int getIntervalo1() {
        return intervalo1;
    }

    public void setIntervalo1(int intervalo1) {
        this.intervalo1 = intervalo1;
    }

    public int getIntervalo2() {
        return intervalo2;
    }

    public void setIntervalo2(int intervalo2) {
        this.intervalo2 = intervalo2;
    }
    public static ArrayList<Integer>busquedaPrimos(int intervalo1,int intervalo2)  {
        ArrayList<Integer> numerosPrimos=new ArrayList<Integer>();

        for(int i=intervalo1;i<intervalo2;i++){
            if(i==1||i==2||i==3||i==5){
                numerosPrimos.add(i);

            }else if(i!=0&&!(i%2==0)&&!(i%3==0)&&!(i%5==0)){
                numerosPrimos.add(i);

            }
        }
        return numerosPrimos;
    }
}
