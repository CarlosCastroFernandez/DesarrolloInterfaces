package cesur.examen;

import cesur.examen.common.HibernateUtil;
import cesur.examen.domain.car.Car;
import cesur.examen.domain.car.CarDAO;
import cesur.examen.domain.client.ClientDAO;
import cesur.examen.domain.client.ClientService;

/**
 * EXAMEN DE ACCESO A DATOS
 * Diciembre 2023
 *
 * Nombre del alumno:
 * Fecha:
 */
public class App 
{
    /**
     *
     * NO MODIFICAR EL CODIGO DE ESTA CLASE.
     * SI SE MODIFICA NO SE CORREGIRÁ EL RESTO DEL PROYECTO.
     *
     * Descomentar el código conforme se vayan creando los
     * métodos implicados
     *
     */

    // Put your full name in the constant value
    static final String STUDENT_NAME = "Francisco Romero";

    public static void main( String[] args )
    {
        /*

        Second Part of exam: Hibernate mapping and operations
        Don't change anything in this part, just run and see the terminal...

         */

        System.out.println("----------------------------------------------------------");
        System.out.println(" >>> "+ STUDENT_NAME.toUpperCase() + " Hibernate exercises");
        System.out.println("----------------------------------------------------------\n");

        if(HibernateUtil.getSessionFactory()==null){
            throw new RuntimeException("Start with HibernateUtil, hibernate.config.xml and mapping classes...");
        }
        // Show all clients with their cars
        var clients = (new ClientDAO()).getAll();
        System.out.println("List of clients in database...");
        if(clients.isEmpty()){
            throw new RuntimeException("ClientDAO.getAll() is not implemented");
        }
        clients.forEach(System.out::println);

        // Create a car and add to first client in list (don´t worry about client)
        System.out.println("Creating a new Car...");
        Car car = new Car();
        car.setModel("C4");
        car.setManufacturer("Citroën");
        car.setPlate("AABBCC");
        clients.get(0).addCar(car);
        (new CarDAO()).save(car);

        // Show clients again with their cars. We can see the new car added
        clients = (new ClientDAO()).getAll();
        System.out.println("List of clients in database again...");
        clients.forEach(System.out::println);

        // List all clients that has a car of this manufacturer.
        System.out.println("List of clients with an car manufactured by Honda...");
        ClientService.hasManufacturer("Honda").forEach(System.out::println);
    }

}
