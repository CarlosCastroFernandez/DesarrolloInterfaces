package clase;

import jakarta.persistence.*;

@Entity
@Table(name = "servicio")
public class Servicio {
@Id
@GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;
  private String nombre;
  @Column(name = "horas_lunes")
  private Integer horasLunes;
  @Column(name = "horas_martes")
  private Integer horasMartes;
  @Column(name = "horas_miercoles")
  private Integer horasMiercoles;
  @Column(name = "horas_jueves")
  private Integer horasJueves;
  @Column(name = "horas_viernes")
  private Integer horasViernes;
  @Column(name = "horas_sabado")
  private Integer horasSabado;
  @Column(name = "horas_domingo")
  private Integer horasDomingo;
  @Column(name = "horas_festivo")
  private Integer horasFestivos;
  @Column(name = "horas_lv")
  private Integer horasLV;
  @Column(name = "horas_sdf")
  private Integer horasSDF;
  private Double comision;

  private Double beneficio;
  @Column(name = "hora_total")
  private Integer horaTotal;
  private String municipio;
  private String direccion;
  @Column(name = "n_escoltas")
  private Integer nEscoltas;
  private Double precio;
  private String iva;
  private Double salario;
  @Column(name = "fecha_inicio")
  private String fechaIn;
  @Column(name = "fecha_fin")
  private String fechaFin;

  public Servicio(Long id, String nombre, Integer horasLunes, Integer horasMartes, Integer horasMiercoles, Integer horasJueves, Integer horasViernes, Integer horasSabado, Integer horasDomingo, Integer horasFestivos, Integer horasLV, Integer horasSDF, Double comision, Double beneficio, Integer horaTotal, String municipio, String direccion, Integer nEscoltas, Double precio, String iva, Double salario, String fechaIn, String fechaFin) {
    this.id = id;
    this.nombre = nombre;
    this.horasLunes = horasLunes;
    this.horasMartes = horasMartes;
    this.horasMiercoles = horasMiercoles;
    this.horasJueves = horasJueves;
    this.horasViernes = horasViernes;
    this.horasSabado = horasSabado;
    this.horasDomingo = horasDomingo;
    this.horasFestivos = horasFestivos;
    this.horasLV = horasLV;
    this.horasSDF = horasSDF;
    this.comision = comision;
    this.beneficio = beneficio;
    this.horaTotal = horaTotal;
    this.municipio = municipio;
    this.direccion = direccion;
    this.nEscoltas = nEscoltas;
    this.precio = precio;
    this.iva = iva;
    this.salario = salario;
    this.fechaIn = fechaIn;
    this.fechaFin = fechaFin;
  }

  public Servicio(String nombre, Integer horasLunes, Integer horasMartes, Integer horasMiercoles, Integer horasJueves, Integer horasViernes, Integer horasSabado, Integer horasDomingo, Integer horasFestivos, Integer horasLV, Integer horasSDF, Double comision, Double beneficio, Integer horaTotal, String municipio, String direccion, Integer nEscoltas, Double precio, String iva, Double salario, String fechaIn, String fechaFin) {
    this.nombre = nombre;
    this.horasLunes = horasLunes;
    this.horasMartes = horasMartes;
    this.horasMiercoles = horasMiercoles;
    this.horasJueves = horasJueves;
    this.horasViernes = horasViernes;
    this.horasSabado = horasSabado;
    this.horasDomingo = horasDomingo;
    this.horasFestivos = horasFestivos;
    this.horasLV = horasLV;
    this.horasSDF = horasSDF;
    this.comision = comision;
    this.beneficio = beneficio;
    this.horaTotal = horaTotal;
    this.municipio = municipio;
    this.direccion = direccion;
    this.nEscoltas = nEscoltas;
    this.precio = precio;
    this.iva = iva;
    this.salario = salario;
    this.fechaIn = fechaIn;
    this.fechaFin = fechaFin;
  }


  public Servicio() {
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

  public Integer getHorasLunes() {
    return horasLunes;
  }

  public void setHorasLunes(Integer horasLunes) {
    this.horasLunes = horasLunes;
  }

  public Integer getHorasMartes() {
    return horasMartes;
  }

  public void setHorasMartes(Integer horasMartes) {
    this.horasMartes = horasMartes;
  }

  public Integer getHorasMiercoles() {
    return horasMiercoles;
  }

  public void setHorasMiercoles(Integer horasMiercoles) {
    this.horasMiercoles = horasMiercoles;
  }

  public Integer getHorasJueves() {
    return horasJueves;
  }

  public void setHorasJueves(Integer horasJueves) {
    this.horasJueves = horasJueves;
  }

  public Integer getHorasViernes() {
    return horasViernes;
  }

  public void setHorasViernes(Integer horasViernes) {
    this.horasViernes = horasViernes;
  }

  public Integer getHorasSabado() {
    return horasSabado;
  }

  public void setHorasSabado(Integer horasSabado) {
    this.horasSabado = horasSabado;
  }

  public Integer getHorasDomingo() {
    return horasDomingo;
  }

  public void setHorasDomingo(Integer horasDomingo) {
    this.horasDomingo = horasDomingo;
  }

  public Integer getHorasFestivos() {
    return horasFestivos;
  }

  public void setHorasFestivos(Integer horasFestivos) {
    this.horasFestivos = horasFestivos;
  }

  public Double getComision() {
    return comision;
  }

  public void setComision(Double comision) {
    this.comision = comision;
  }

  public Double getBeneficio() {
    return beneficio;
  }

  public void setBeneficio(Double beneficio) {
    this.beneficio = beneficio;
  }

  public Integer getHoraTotal() {
    return horaTotal;
  }

  public void setHoraTotal(Integer horaTotal) {
    this.horaTotal = horaTotal;
  }

  public String getMunicipio() {
    return municipio;
  }

  public void setMunicipio(String municipio) {
    this.municipio = municipio;
  }

  public Integer getHorasLV() {
    return horasLV;
  }

  public void setHorasLV(Integer horasLV) {
    this.horasLV = horasLV;
  }

  public Integer getHorasSDF() {
    return horasSDF;
  }

  public void setHorasSDF(Integer horasSDF) {
    this.horasSDF = horasSDF;
  }

  public String getDireccion() {
    return direccion;
  }

  public void setDireccion(String direccion) {
    this.direccion = direccion;
  }

  public Integer getnEscoltas() {
    return nEscoltas;
  }

  public void setnEscoltas(Integer nEscoltas) {
    this.nEscoltas = nEscoltas;
  }

  public Double getPrecio() {
    return precio;
  }

  public void setPrecio(Double precio) {
    this.precio = precio;
  }

  public String getIva() {
    return iva;
  }

  public void setIva(String iva) {
    this.iva = iva;
  }

  public Double getSalario() {
    return salario;
  }

  public void setSalario(Double salario) {
    this.salario = salario;
  }

  public String getFechaIn() {
    return fechaIn;
  }

  public void setFechaIn(String fechaIn) {
    this.fechaIn = fechaIn;
  }

  public String getFechaFin() {
    return fechaFin;
  }

  public void setFechaFin(String fechaFin) {
    this.fechaFin = fechaFin;
  }

  @Override
  public String toString() {
    return "Servicio{" +
            "id=" + id +
            ", nombre='" + nombre + '\'' +
            ", horasLunes=" + horasLunes +
            ", horasMartes=" + horasMartes +
            ", horasMiercoles=" + horasMiercoles +
            ", horasJueves=" + horasJueves +
            ", horasViernes=" + horasViernes +
            ", horasSabado=" + horasSabado +
            ", horasDomingo=" + horasDomingo +
            ", horasFestivos=" + horasFestivos +
            ", horasLV=" + horasLV +
            ", horasSDF=" + horasSDF +
            ", comision=" + comision +
            ", beneficio=" + beneficio +
            ", horaTotal=" + horaTotal +
            ", municipio='" + municipio + '\'' +
            ", direccion='" + direccion + '\'' +
            ", nEscoltas=" + nEscoltas +
            ", precio=" + precio +
            ", iva='" + iva + '\'' +
            ", salario=" + salario +
            ", fechaIn='" + fechaIn + '\'' +
            ", fechaFin='" + fechaFin + '\'' +
            '}';
  }
}
