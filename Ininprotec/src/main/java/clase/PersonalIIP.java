package clase;

import jakarta.persistence.*;

import java.io.Serializable;
import java.sql.Date;
import java.util.List;

@Entity
@Table(name = "personaliip")
public class PersonalIIP implements Serializable {
@Id
@GeneratedValue(strategy=GenerationType.IDENTITY)
  private Long id;
  private String nombre;
  private String apellido1;
  private String apellido2;
  private String correo;
  private String dni;
  private String telefono;
  @Column(name = "fecha_nacimiento")
  private java.sql.Date fechaNacimiento;
  @Column(name = "talla_camiseta")
  private String tallaCamiseta;
  private String curriculum;
  @Column(name = "numero_cuenta")
  private String numeroCuenta;
  @Column(name = "numero_social")
  private String numeroSocial;
  @Column(name = "numero_tip")
  private String numeroTip;
  private Long instructor;
  @Column(name = "imagen_perfil")
  private byte[] imagenPerfil;
  @Column(name = "lugar_residencia")
  private String lugarResidencia;
  private String titulacion;
  @Column(name = "licencia_arma")
  private String licenciaArma;
  @OneToMany(mappedBy = "instructor",fetch = FetchType.EAGER,cascade = CascadeType.REMOVE)
  private List<Modulo>modulos;
  @OneToMany(mappedBy = "instructor",fetch = FetchType.EAGER,cascade = {CascadeType.PERSIST, CascadeType.MERGE,CascadeType.REMOVE})
  private List<InstructorCurso> cursoInstructor;

  public PersonalIIP(Long id, String nombre, String apellido1, String apellido2, String correo, String dni, String telefono, Date fechaNacimiento, String tallaCamiseta, String curriculum, String numeroCuenta, String numeroSocial, String numeroTip, Long instructor, byte[] imagenPerfil, String lugarResidencia, String titulacion) {
    this.id = id;
    this.nombre = nombre;
    this.apellido1 = apellido1;
    this.apellido2 = apellido2;
    this.correo = correo;
    this.dni = dni;
    this.telefono = telefono;
    this.fechaNacimiento = fechaNacimiento;
    this.tallaCamiseta = tallaCamiseta;
    this.curriculum = curriculum;
    this.numeroCuenta = numeroCuenta;
    this.numeroSocial = numeroSocial;
    this.numeroTip = numeroTip;
    this.instructor = instructor;
    this.imagenPerfil = imagenPerfil;
    this.lugarResidencia = lugarResidencia;
    this.titulacion = titulacion;

  }
  public PersonalIIP(String nombre, String apellido1, String apellido2, String correo, String dni, String telefono, Date fechaNacimiento, String tallaCamiseta, String curriculum, String numeroCuenta, String numeroSocial, String numeroTip, Long instructor, byte[] imagenPerfil, String lugarResidencia, String titulacion,String licenciaArma) {
    this.nombre = nombre;
    this.apellido1 = apellido1;
    this.apellido2 = apellido2;
    this.licenciaArma=licenciaArma;
    this.correo = correo;
    this.dni = dni;
    this.telefono = telefono;
    this.fechaNacimiento = fechaNacimiento;
    this.tallaCamiseta = tallaCamiseta;
    this.curriculum = curriculum;
    this.numeroCuenta = numeroCuenta;
    this.numeroSocial = numeroSocial;
    this.numeroTip = numeroTip;
    this.instructor = instructor;
    this.imagenPerfil = imagenPerfil;
    this.lugarResidencia = lugarResidencia;
    this.titulacion = titulacion;

  }

  public PersonalIIP() {
  }

  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }





  public List<Modulo> getModulos() {
    return modulos;
  }

  public void setModulos(List<Modulo> modulos) {
    this.modulos = modulos;
  }

  public List<InstructorCurso> getCursoInstructor() {
    return cursoInstructor;
  }

  public void setCursoInstructor(List<InstructorCurso> cursos) {
    this.cursoInstructor = cursos;
  }

  public String getNombre() {
    return nombre;
  }

  public void setNombre(String nombre) {
    this.nombre = nombre;
  }

  public String getLicenciaArma() {
    return licenciaArma;
  }

  public void setLicenciaArma(String licenciaArma) {
    this.licenciaArma = licenciaArma;
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


  public String getTelefono() {
    return telefono;
  }

  public void setTelefono(String telefono) {
    this.telefono = telefono;
  }


  public java.sql.Date getFechaNacimiento() {
    return fechaNacimiento;
  }

  public void setFechaNacimiento(java.sql.Date fechaNacimiento) {
    this.fechaNacimiento = fechaNacimiento;
  }


  public String getTallaCamiseta() {
    return tallaCamiseta;
  }

  public void setTallaCamiseta(String tallaCamiseta) {
    this.tallaCamiseta = tallaCamiseta;
  }


  public String getCurriculum() {
    return curriculum;
  }

  public void setCurriculum(String curriculum) {
    this.curriculum = curriculum;
  }


  public String getNumeroCuenta() {
    return numeroCuenta;
  }

  public void setNumeroCuenta(String numeroCuenta) {
    this.numeroCuenta = numeroCuenta;
  }


  public String getNumeroSocial() {
    return numeroSocial;
  }

  public void setNumeroSocial(String numeroSocial) {
    this.numeroSocial = numeroSocial;
  }


  public String getNumeroTip() {
    return numeroTip;
  }

  public void setNumeroTip(String numeroTip) {
    this.numeroTip = numeroTip;
  }


  public Long getInstructor() {
    return instructor;
  }

  public void setInstructor(Long instructor) {
    this.instructor = instructor;
  }


  public byte[] getImagenPerfil() {
    return imagenPerfil;
  }

  public void setImagenPerfil(byte[] imagenPerfil) {
    this.imagenPerfil = imagenPerfil;
  }


  public String getLugarResidencia() {
    return lugarResidencia;
  }

  public void setLugarResidencia(String lugarResidencia) {
    this.lugarResidencia = lugarResidencia;
  }


  public String getTitulacion() {
    return titulacion;
  }

  public void setTitulacion(String titulacion) {
    this.titulacion = titulacion;
  }

  @Override
  public String toString() {
    return "PersonalIIP{" +
            "id=" + id +
            ", nombre='" + nombre + '\'' +
            ", apellido1='" + apellido1 + '\'' +
            ", apellido2='" + apellido2 + '\'' +
            ", correo='" + correo + '\'' +
            ", dni='" + dni + '\'' +
            ", telefono=" + telefono +
            ", fechaNacimiento=" + fechaNacimiento +
            ", tallaCamiseta='" + tallaCamiseta + '\'' +
            ", curriculum='" + curriculum + '\'' +
            ", numeroCuenta='" + numeroCuenta + '\'' +
            ", numeroSocial='" + numeroSocial + '\'' +
            ", numeroTip=" + numeroTip +
            ", instructor=" + instructor +
            ", lugarResidencia='" + lugarResidencia + '\'' +
            ", titulacion='" + titulacion + '\'' +
            ", modulos=" + modulos +
            " cursos ="+cursoInstructor.toString() +
            '}';
  }
}
