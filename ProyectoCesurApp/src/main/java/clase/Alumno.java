package clase;

import enums.Curso;

public class Alumno {
    private String dni;
    private String email;
    private String nombre;
    private String apellido1;
    private String apellido2;
    private Integer telefono;
    private String contrasenha;
    private String profesor;
    private String empresa;
    private String fechaNacimiento;
    private Integer horasDual;
    private Integer horasFCT;
    private Curso curso;
    private String observaciones;

    public Alumno(String dni, String email, String nombre, String apellido1, String apellido2, Integer telefono, String contrasenha, String profesor, String empresa, String fechaNacimiento, Integer horasDual, Integer horasFCT, Curso curso, String observaciones) {
        this.dni = dni;
        this.email = email;
        this.nombre = nombre;
        this.apellido1 = apellido1;
        this.apellido2 = apellido2;
        this.telefono = telefono;
        this.contrasenha = contrasenha;
        this.profesor = profesor;
        this.empresa = empresa;
        this.fechaNacimiento = fechaNacimiento;
        this.horasDual = horasDual;
        this.horasFCT = horasFCT;
        this.curso = curso;
        this.observaciones = observaciones;
    }

    public String getDni() {
        return dni;
    }

    public void setDni(String dni) {
        this.dni = dni;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
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

    public Integer getTelefono() {
        return telefono;
    }

    public void setTelefono(Integer telefono) {
        this.telefono = telefono;
    }

    public String getContrasenha() {
        return contrasenha;
    }

    public void setContrasenha(String contrasenha) {
        this.contrasenha = contrasenha;
    }

    public String getProfesor() {
        return profesor;
    }

    public void setProfesor(String profesor) {
        this.profesor = profesor;
    }

    public String getEmpresa() {
        return empresa;
    }

    public void setEmpresa(String empresa) {
        this.empresa = empresa;
    }

    public String getFechaNacimiento() {
        return fechaNacimiento;
    }

    public void setFechaNacimiento(String fechaNacimiento) {
        this.fechaNacimiento = fechaNacimiento;
    }

    public Integer getHorasDual() {
        return horasDual;
    }

    public void setHorasDual(Integer horasDual) {
        this.horasDual = horasDual;
    }

    public Integer getHorasFCT() {
        return horasFCT;
    }

    public void setHorasFCT(Integer horasFCT) {
        this.horasFCT = horasFCT;
    }

    public Curso getCurso() {
        return curso;
    }

    public void setCurso(Curso curso) {
        this.curso = curso;
    }

    public String getObservaciones() {
        return observaciones;
    }

    public void setObservaciones(String observaciones) {
        this.observaciones = observaciones;
    }
}
