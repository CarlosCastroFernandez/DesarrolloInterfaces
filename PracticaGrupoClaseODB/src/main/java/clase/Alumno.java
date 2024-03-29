package clase;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity

public class Alumno {
    @Id
    @GeneratedValue
    private Long id;

    private String nombre;
    private String telefono;


    private String email;
    private Double ad;
    private Double di;
    public Alumno(){

    }

    public Alumno(Long id,String nombre, String telefono, String email, Double ad, Double di) {
        this.id=id;
        this.nombre = nombre;
        this.telefono = telefono;
        this.email = email;
        this.ad = ad;
        this.di = di;
    }
    public Alumno(String nombre, String telefono, String email, Double ad, Double di) {

        this.nombre = nombre;
        this.telefono = telefono;
        this.email = email;
        this.ad = ad;
        this.di = di;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getTelefono() {
        return telefono;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Double getAd() {
        return ad;
    }

    public void setAd(Double ad) {
        this.ad = ad;
    }

    public Double getDi() {
        return di;
    }

    public void setDi(Double di) {
        this.di = di;
    }

    @Override
    public String toString() {
        return "Alumno{" +
                "id=" + id +
                ", nombre='" + nombre + '\'' +
                ", telefono='" + telefono + '\'' +
                ", email='" + email + '\'' +
                ", ad=" + ad +
                ", di=" + di +
                '}';
    }
}
