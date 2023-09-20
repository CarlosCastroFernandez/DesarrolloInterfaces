package Clases;

import java.util.ArrayList;
import java.util.logging.Logger;

public class PrimoUtilities {

    static Logger logger=Logger.getLogger(PrimoUtilities.class.getName());
    public static Boolean esPrimo(Integer i) {
        for (Integer n = 2; n < (i - 1); n++) {

            if (i == 0) return false;

        }
        logger.info("Pimo encontrado "+i);
        logger.severe("Error");
        return true;
    }


    public static ArrayList<Integer> primoIntervalo(Integer inicial, Integer fin){
        var salida=new ArrayList<Integer>();
        for(Integer numero=inicial;numero<fin;numero++){
            if(esPrimo(numero)) salida.add(numero);
        }
        return salida;
    }
}
