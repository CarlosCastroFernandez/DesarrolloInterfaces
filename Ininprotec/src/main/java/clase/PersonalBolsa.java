package clase;

import error.DNIIncorrecto;
import jakarta.persistence.*;

import java.io.Serializable;
import java.sql.Date;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Entity
@Table(name = "personalbolsa")
public class PersonalBolsa implements Serializable,Comparable<PersonalBolsa>{
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;
  private String nombre;
  private String apellido1;
  private String apellido2;
  private String correo;
  private String dni;
  private String telefono;
  @Column(name = "fecha_nacimiento")
  private java.sql.Date fechaNacimiento;
  @Column(name = "licencia_arma")
  private String licenciaArma;
  @Column(name = "talla_camiseta")
  private String tallaCamiseta;
  @Column(name = "curriculum_url")
  private byte[] curriculumUrl;
  @Column(name = "numero_cuenta")
  private String numeroCuenta;
  @Column(name = "numero_social")
  private String numeroSocial;
  @Column(name = "alumno")
  private Long esAlumno;
  @Column(name = "numero_tip")
  private String numeroTip;

  @Column(name = "imagen_perfil")
  private byte[] imagenPerfil;

  private String titulacion;
  @Column(name = "lugar_residencia")
  private String lugarResidencia;
  @OneToMany(mappedBy = "alumnoId",fetch = FetchType.EAGER,cascade = {CascadeType.PERSIST,CascadeType.MERGE,CascadeType.REMOVE})


  private List<AlumnoCurso> cursosAlumnos=new ArrayList<>();
  @OneToMany(mappedBy = "alumnoId",fetch = FetchType.EAGER,cascade = {CascadeType.PERSIST,CascadeType.MERGE,CascadeType.REMOVE})
  private List<AlumnoModulo> moduloAlumno;
  @Column(name = "nota_final")
  private Double notaFinal;
  private Double altura;
  private String idioma;
  @Column(name = "fecha_registro")
  private Date fechaRegistro;
  private Character sexo;
  private String contraseña;
  private Integer posicion;


  public PersonalBolsa(Long id, String nombre, String apellido1, String apellido2, String correo, String dni, String telefono, Date fechaNacimiento, String licenciaArma, String tallaCamiseta, byte[] curriculumUrl, String numeroCuenta, String numeroSocial, Long esAlumno, String numeroTip, byte[] imagenPerfil, String titulacion, String lugarResidencia, List<AlumnoCurso> cursosAlumnos, List<AlumnoModulo> modulos,String contraseña) throws DNIIncorrecto {
    this.id = id;
    this.nombre = nombre;
    this.apellido1 = apellido1;
    this.apellido2 = apellido2;
    this.correo = correo;
    this.setDni(dni);
    this.telefono = telefono;
    this.fechaNacimiento = fechaNacimiento;
    this.licenciaArma = licenciaArma;
    this.tallaCamiseta = tallaCamiseta;
    this.curriculumUrl = curriculumUrl;
    this.numeroCuenta = numeroCuenta;
    this.numeroSocial = numeroSocial;
    this.esAlumno = esAlumno;
    this.numeroTip = numeroTip;
this.contraseña=contraseña;
    this.imagenPerfil = imagenPerfil;
    this.titulacion = titulacion;
    this.lugarResidencia = lugarResidencia;
    this.cursosAlumnos = cursosAlumnos;
    this.moduloAlumno = modulos;
  }
  public PersonalBolsa(String nombre, String apellido1, String apellido2, String correo, String dni, String telefono, Date fechaNacimiento, String licenciaArma, String tallaCamiseta, byte[] curriculumUrl, String numeroCuenta, String numeroSocial, Long esAlumno, String numeroTip, byte[] imagenPerfil, String titulacion, String lugarResidencia,Date fechaRegisto,
                       String idioma,Double altura,Character sexo) throws DNIIncorrecto {
    this.nombre = nombre;
    this.apellido1 = apellido1;
    this.apellido2 = apellido2;
    this.correo = correo;
    this.setDni(dni);
    this.idioma=idioma;
    this.altura=altura;
    this.telefono = telefono;
    this.fechaNacimiento = fechaNacimiento;
    this.licenciaArma = licenciaArma;
    this.tallaCamiseta = tallaCamiseta;
    this.curriculumUrl = curriculumUrl;
    this.numeroCuenta = numeroCuenta;
    this.numeroSocial = numeroSocial;
    this.esAlumno = esAlumno;
    this.numeroTip = numeroTip;
    this.imagenPerfil = imagenPerfil;
    this.fechaRegistro=fechaRegisto;
    this.titulacion = titulacion;
    this.lugarResidencia = lugarResidencia;
    this.sexo=sexo;
  }

  public PersonalBolsa() {
  }

  public List<AlumnoCurso> getCursosAlumnos() {
    return cursosAlumnos;
  }

  public Double getNotaFinal() {
    return notaFinal;
  }

  public Integer getPosicion() {
    return posicion;
  }

  public void setPosicion(Integer posicion) {
    this.posicion = posicion;
  }

  public Character getSexo() {
    return sexo;
  }

  public void setSexo(Character sexo) {
    this.sexo = sexo;
  }

  public Double getAltura() {
    return altura;
  }

  public void setAltura(Double altura) {
    this.altura = altura;
  }

  public String getIdioma() {
    return idioma;
  }

  public String getContraseña() {
    return contraseña;
  }

  public void setContraseña(String contraseña) {
    this.contraseña = contraseña;
  }

  public void setIdioma(String idioma) {
    this.idioma = idioma;
  }

  public Date getFechaRegistro() {
    return fechaRegistro;
  }

  public void setFechaRegistro(Date fechaRegistro) {
    this.fechaRegistro = fechaRegistro;
  }

  public void setNotaFinal(Double notaFinal) {
    this.notaFinal = notaFinal;
  }

  public void setCursosAlumnos(List<AlumnoCurso> cursosAlumnos) {
    this.cursosAlumnos = cursosAlumnos;
  }

  public List<AlumnoModulo> getModuloAlumno() {
    return moduloAlumno;
  }

  public void setModuloAlumno(List<AlumnoModulo> modulos) {
    this.moduloAlumno = modulos;
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

  public void setDni(String dni) throws DNIIncorrecto {
    String numeros="0123456789";
    String letras="abcdefghijklmnñopqrstuvwxyz".toUpperCase();
    String dniMin=dni.toUpperCase();

    if(dniMin.length()==9&&(letras.contains(""+dniMin.charAt(0))&&letras.contains(""+dniMin.charAt(dniMin.length()-1)))||letras.contains(""+dniMin.charAt(dniMin.length()-1))&&numeros.contains(""+dniMin.charAt(0))){
      for(int i=0;i<dniMin.length();i++){
        if(i==1&&i!=dniMin.length()-1){
          if(!numeros.contains(""+dniMin.charAt(i))){
            throw new DNIIncorrecto("DNI incorrecto");
          }
        }
      }
      this.dni=dniMin;

    }else{
      throw new DNIIncorrecto("DNI Incorrecto");
    }
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


  public String getLicenciaArma() {
    return licenciaArma;
  }

  public void setLicenciaArma(String licenciaArma) {
    this.licenciaArma = licenciaArma;
  }


  public String getTallaCamiseta() {
    return tallaCamiseta;
  }

  public void setTallaCamiseta(String tallaCamiseta) {
    this.tallaCamiseta = tallaCamiseta;
  }


  public byte[] getCurriculumUrl() {
    return curriculumUrl;
  }

  public void setCurriculumUrl(byte[] curriculumUrl) {
    this.curriculumUrl = curriculumUrl;
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


  public Long getEsAlumno() {
    return esAlumno;
  }

  public void setEsAlumno(Long alumno) {
    this.esAlumno = alumno;
  }


  public String getNumeroTip() {
    return numeroTip;
  }

  public void setNumeroTip(String numeroTip) {
    this.numeroTip = numeroTip;
  }


  public byte[] getImagenPerfil() {
    return imagenPerfil;
  }

  public void setImagenPerfil(byte[] imagenPerfil) {
    this.imagenPerfil = imagenPerfil;
  }





  public String getTitulacion() {
    return titulacion;
  }

  public void setTitulacion(String titulacion) {
    this.titulacion = titulacion;
  }


  public String getLugarResidencia() {
    return lugarResidencia;
  }

  public void setLugarResidencia(String lugarResidencia) {
    this.lugarResidencia = lugarResidencia;
  }

  @Override
  public String toString() {
    return "PersonalBolsa{" +
            "id=" + id +
            ", nombre='" + nombre + '\'' +
            ", apellido1='" + apellido1 + '\'' +
            ", apellido2='" + apellido2 + '\'' +
            ", correo='" + correo + '\'' +
            ", dni='" + dni + '\'' +
            ", telefono='" + telefono + '\'' +
            ", fechaNacimiento=" + fechaNacimiento +
            ", licenciaArma='" + licenciaArma + '\'' +
            ", tallaCamiseta='" + tallaCamiseta + '\'' +
            ", curriculumUrl='" + curriculumUrl + '\'' +
            ", numeroCuenta='" + numeroCuenta + '\'' +
            ", numeroSocial='" + numeroSocial + '\'' +
            ", esAlumno=" + esAlumno +
            ", numeroTip='" + numeroTip + '\'' +
            ", imagenPerfil=" + Arrays.toString(imagenPerfil) +
            ", titulacion='" + titulacion + '\'' +
            ", lugarResidencia='" + lugarResidencia + '\'' +
            ", modulos=" + moduloAlumno +
            " cursosAlumnos "+cursosAlumnos+
            '}';
  }

  @Override
  public int compareTo(PersonalBolsa o) {
    // Comparación por número de cursos en orden descendente
    if (this.cursosAlumnos.size() > o.cursosAlumnos.size()) {
      return -1;
    } else if (this.cursosAlumnos.size() < o.cursosAlumnos.size()) {
      return 1;
    }

    // Prioridad especial para esAlumno == 3
    boolean thisEsAlumnoEsTres = this.esAlumno == 3;
    boolean otroEsAlumnoEsTres = o.esAlumno == 3;

    if (thisEsAlumnoEsTres && !otroEsAlumnoEsTres) {
      return -1;  // Este objeto tiene esAlumno == 3, va primero
    } else if (!thisEsAlumnoEsTres && otroEsAlumnoEsTres) {
      return 1;  // Otro objeto tiene esAlumno == 3, este va después
    }

    // Si esAlumno es igual o ambos son 3, comparar por notaFinal en orden descendente
    if (this.notaFinal > o.notaFinal) {
      return -1;
    } else if (this.notaFinal < o.notaFinal) {
      return 1;
    }

    // Si notaFinal es igual, comparar por dni en orden ascendente
    return this.dni.compareTo(o.dni);

  }
}
