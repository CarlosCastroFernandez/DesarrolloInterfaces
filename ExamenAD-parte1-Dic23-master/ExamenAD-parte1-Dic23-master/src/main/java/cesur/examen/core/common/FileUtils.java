package cesur.examen.core.common;

import cesur.examen.core.worker.Worker;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.List;

/**
 * EXAMEN DE ACCESO A DATOS
 * Diciembre 2023
 *
 * Nombre del alumno:
 * Fecha:
 *
 * No se permite escribir en consola desde las clases DAO, Service y Utils usando System.out.
 * En su lugar, usa log.info(), log.warning() y log.severe() para mostrar información interna
 * o para seguir la traza de ejecución.
 */


public class FileUtils {

    public static void toCSV(String fileName, List<Worker> workers) {
        //throw new RuntimeException("Not implemented yet!");

        String nuevo="";

        System.out.println(workers);
        try (BufferedWriter writer=new BufferedWriter(new FileWriter(fileName))) {

            for(int i=0;i<workers.size();i++){
                SimpleDateFormat formato=new SimpleDateFormat("dd-MM-yyyy");
                String fechaString=formato.format(workers.get(i).getFrom());
                nuevo=workers.get(i).getId()+","+workers.get(i).getDni()+","+workers.get(i).getName()+","+fechaString;
                writer.write(nuevo);
                writer.newLine();
                System.out.println(nuevo);
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }


    }
}
