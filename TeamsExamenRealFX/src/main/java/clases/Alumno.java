package clases;


import java.sql.Date;

public class Alumno {

  private Long id;
  private String nombre;
  private String apellido1;
  private String apellido2;
  private String correo;
  private String dni;
  private java.sql.Date fecha;
  private String localidad;
  private String telefono;
  private Long moduloId;
  private Modulo modulo;

public Alumno(){

}

  public Alumno(Long id, String nombre, String apellido1, String apellido2, String correo, String dni, Date fecha, String localidad, String telefono, Long moduloId, Modulo modulo) {
    this.id = id;
    this.nombre = nombre;
    this.apellido1 = apellido1;
    this.apellido2 = apellido2;
    this.correo = correo;
    this.dni = dni;
    this.fecha = fecha;
    this.localidad = localidad;
    this.telefono = telefono;
    this.moduloId = moduloId;
    this.modulo = modulo;
  }
  public Alumno( String nombre, String apellido1, String apellido2, String correo, String dni, Date fecha, String localidad, String telefono, Long moduloId, Modulo modulo) {
    this.nombre = nombre;
    this.apellido1 = apellido1;
    this.apellido2 = apellido2;
    this.correo = correo;
    this.dni = dni;
    this.fecha = fecha;
    this.localidad = localidad;
    this.telefono = telefono;
    this.moduloId = moduloId;
    this.modulo = modulo;
  }

  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }


  public String getNombre() {
    return nombre;
  }

  public void setNombre(String nombre) {
    this.nombre = nombre;
  }


  public String getApellido1() {
    return apellido1;
  }

  public void setApellido1(String apellido1) {
    this.apellido1 = apellido1;
  }


  public String getApellido2() {
    return apellido2;
  }

  public void setApellido2(String apellido2) {
    this.apellido2 = apellido2;
  }


  public String getCorreo() {
    return correo;
  }

  public void setCorreo(String correo) {
    this.correo = correo;
  }


  public String getDni() {
    return dni;
  }

  public void setDni(String dni) {
    this.dni = dni;
  }


  public java.sql.Date getFecha() {
    return fecha;
  }

  public void setFecha(java.sql.Date fecha) {
    this.fecha = fecha;
  }


  public String getLocalidad() {
    return localidad;
  }

  public void setLocalidad(String localidad) {
    this.localidad = localidad;
  }


  public String getTelefono() {
    return telefono;
  }

  public void setTelefono(String telefono) {
    this.telefono = telefono;
  }


  public long getModuloId() {
    return moduloId;
  }

  public void setModuloId(Long moduloId) {
    this.moduloId = moduloId;
  }

  public Modulo getModulo() {
    return modulo;
  }

  public void setModulo(Modulo modulo) {
    this.modulo = modulo;
  }

  @Override
    public String toString() {
        return
                "nombre=" + nombre + "\n" +
                "apellido1=" + apellido1 + "\n" +
                "apellido2=" + apellido2 + "\n" +
                "correo=" + correo + "\n" +
                "dni=" + dni + "\n" +
                "fecha=" + fecha +"\n"+
                "localidad=" + localidad + "\n"+
                "telefono=" + telefono + "\n" +
                "modulo=" + modulo.getId();
    }
}
