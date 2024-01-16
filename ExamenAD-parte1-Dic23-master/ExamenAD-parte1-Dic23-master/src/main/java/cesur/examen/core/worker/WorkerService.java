package cesur.examen.core.worker;

import cesur.examen.core.common.JDBCUtils;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * EXAMEN DE ACCESO A DATOS
 * Diciembre 2023
 *
 * Nombre del alumno:
 * Fecha:
 *
 *  No se permite escribir en consola desde las clases DAO, Service y Utils usando System.out.
 *  En su lugar, usa log.info(), log.warning() y log.severe() para mostrar información interna
 *  o para seguir la traza de ejecución.
 */
/**
 *  Services classes offers methods to our main application, and can
 *  use multiple methods from DAOs and Entities.
 *  It's a layer between Data Access Layer and Aplication Layer (where
 *  Main app and controllers lives)
 *  It helps to encapsulate multiple opperations with DAOs that can be
 *  reused in application layer.
 */
public class WorkerService {
    /*
    RenovateWorker() set "from" date of the worker with this dni to today's date.
    Remember Date().
    Returns the new updated worker, null if fails or dni doesn't exist.
    */

    public static Worker renovateWorker(String dni) {
        WorkerDAO workerDAO=new WorkerDAO();
        Worker worker=workerDAO.getWorkerByDNI(dni);
        SimpleDateFormat formato=new SimpleDateFormat("yyyy-MM-dd");
        String fechaHoy="2023-12-11";
        try {
            Date fecha=formato.parse(fechaHoy);
            Date fechaActual=JDBCUtils.dateUtilToSQL(fecha);
            worker.setFrom(fechaActual);
        } catch (ParseException e) {
            throw new RuntimeException(e);
        }
        return worker;
    }
}




