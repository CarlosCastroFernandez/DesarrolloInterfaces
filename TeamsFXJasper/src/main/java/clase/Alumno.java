package clase;

import errores.NombreConNumerosException;

public class Alumno {
    private Integer id;
    private String nombre;
    private String apellido;
    private Byte AD;
    private Byte SGE;
    private Byte PMDM;
    private Byte PSP;
    private Byte EIE;
    private Byte DI;
    private Byte HLC;
    public Alumno(){

    }

    public Alumno(String nombre, String apellido, Byte AD, Byte SGE, Byte PMDM, Byte PSP, Byte EIE, Byte DI, Byte HLC) throws NombreConNumerosException {
        this.setNombre(nombre);
        this.setApellido(apellido);
        this.AD = AD;
        this.SGE = SGE;
        this.PMDM = PMDM;
        this.PSP = PSP;
        this.EIE = EIE;
        this.DI = DI;
        this.HLC = HLC;
    }

    public Alumno(Integer id, String nombre, String apellido, Byte AD, Byte SGE, Byte PMDM, Byte PSP, Byte EIE, Byte DI, Byte HLC) throws NombreConNumerosException {
        this.id = id;
        this.setNombre(nombre);
        this.setApellido(apellido);
        this.AD = AD;
        this.SGE = SGE;
        this.PMDM = PMDM;
        this.PSP = PSP;
        this.EIE = EIE;
        this.DI = DI;
        this.HLC = HLC;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) throws NombreConNumerosException {
        String numeros="0123456789";
        for(int i=0;i<nombre.length();i++){
            if(numeros.contains(""+nombre.charAt(i))){
                throw new NombreConNumerosException("Nombre con numeros");
            }
        }
        this.nombre = nombre;
    }

    public String getApellido() {
        return apellido;
    }

    public void setApellido(String apellido) throws NombreConNumerosException {
        String numeros="0123456789";
        for(int i=0;i<apellido.length();i++){
            if(numeros.contains(""+apellido.charAt(i))){
                throw new NombreConNumerosException("Nombre con numeros");
            }
        }
        this.apellido = apellido;
    }

    public Byte getAD() {
        return AD;
    }

    public void setAD(Byte AD) {
        this.AD = AD;
    }

    public Byte getSGE() {
        return SGE;
    }

    public void setSGE(Byte SGE) {
        this.SGE = SGE;
    }

    public Byte getPMDM() {
        return PMDM;
    }

    public void setPMDM(Byte PMDM) {
        this.PMDM = PMDM;
    }

    public Byte getPSP() {
        return PSP;
    }

    public void setPSP(Byte PSP) {
        this.PSP = PSP;
    }

    public Byte getEIE() {
        return EIE;
    }

    public void setEIE(Byte EIE) {
        this.EIE = EIE;
    }

    public Byte getDI() {
        return DI;
    }

    public void setDI(Byte DI) {
        this.DI = DI;
    }

    public Byte getHLC() {
        return HLC;
    }

    public void setHLC(Byte HLC) {
        this.HLC = HLC;
    }

    @Override
    public String toString() {
        return "Alumno{" +
                "nombre='" + nombre + '\'' +
                ", apellido='" + apellido + '\'' +
                ", AD=" + AD +
                ", SGE=" + SGE +
                ", PMDM=" + PMDM +
                ", PSP=" + PSP +
                ", EIE=" + EIE +
                ", DI=" + DI +
                ", HLC=" + HLC +
                '}';
    }
}
