package clase;

public class Cliente {
    private String nombre;
    private String sexo;
    private Float peso;
    private Integer edad;
    private Integer talla;
    private String tipoActividad;
    private String observaciones;
    public Cliente(){

    }

    public Cliente(String nombre, String sexo, Float peso,Integer edad, Integer talla,String tipoActividad, String observaciones) {
        this.nombre = nombre;
        this.sexo = sexo;
        this.peso=peso;
        this.edad = edad;
        this.talla = talla;
        this.tipoActividad=tipoActividad;
        this.observaciones = observaciones;
    }

    public String getNombre() {
        return nombre;
    }

    public String getTipoActividad() {
        return tipoActividad;
    }

    public Float getPeso() {
        return peso;
    }

    public void setPeso(Float peso) {
        this.peso = peso;
    }

    public void setTipoActividad(String tipoActividad) {
        this.tipoActividad = tipoActividad;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getSexo() {
        return sexo;
    }

    public void setSexo(String sexo) {
        this.sexo = sexo;
    }

    public Integer getEdad() {
        return edad;
    }

    public void setEdad(Integer edad) {
        this.edad = edad;
    }

    public Integer getTalla() {
        return talla;
    }

    public void setTalla(Integer talla) {
        this.talla = talla;
    }

    public String getObservaciones() {
        return observaciones;
    }

    public void setObservaciones(String observaciones) {
        this.observaciones = observaciones;
    }

    public Float datoPorActividad(String tipoActividad,String sexo){
   Float dato=null;
        if(tipoActividad.equalsIgnoreCase("sedentario")&&sexo.equalsIgnoreCase("hombre")){
             dato=1.3f;
        } else if (tipoActividad.equalsIgnoreCase("sedentario")&&sexo.equalsIgnoreCase("mujer")) {
             dato=1.3f;
        } else if (tipoActividad.equalsIgnoreCase("moderado")&&sexo.equalsIgnoreCase("hombre")) {
             dato=1.6f;
        } else if (tipoActividad.equalsIgnoreCase("moderado")&&sexo.equalsIgnoreCase("mujer")) {
             dato=1.5f;
        } else if (tipoActividad.equalsIgnoreCase("activo")&&sexo.equalsIgnoreCase("hombre")) {
             dato=1.7f;
        } else if (tipoActividad.equalsIgnoreCase("activo")&&sexo.equalsIgnoreCase("mujer")) {
             dato=1.6f;
        } else if (tipoActividad.equalsIgnoreCase("muy activo")&&sexo.equalsIgnoreCase("hombre")) {
             dato=2.1f;
        } else if (tipoActividad.equalsIgnoreCase("muy activo")&&sexo.equalsIgnoreCase("mujer")) {
             dato=1.9f;
        }
        return dato;
    }
}
