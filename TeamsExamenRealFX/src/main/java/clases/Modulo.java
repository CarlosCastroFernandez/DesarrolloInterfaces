package clases;


import errores.NotaException;

import java.util.ArrayList;

public class Modulo {

  private Long id;
  private Long ad;
  private Long sge;
  private Long di;
  private Long pmdm;
  private Long psp;
  private Long eie;
  private Long hlc;
  private ArrayList<Alumno> alumnos;

public Modulo(){

}

  public Modulo(Long id, Long ad, Long sge, Long di, Long pmdm, Long psp, Long eie, Long hlc) throws NotaException {
    this.id = id;
    this.setAd(ad);
    this.setSge(sge);
    this.setDi(di);
    this.setPmdm(pmdm);
    this.setPsp(psp);
    this.setEie(eie);
    this.setHlc(hlc);
    this.alumnos=new ArrayList<>();

  }
  public Modulo( Long ad, Long sge, Long di, Long pmdm, Long psp, Long eie, Long hlc) throws NotaException {
    this.setAd(ad);
    this.setSge(sge);
    this.setDi(di);
    this.setPmdm(pmdm);
    this.setPsp(psp);
    this.setEie(eie);
    this.setHlc(hlc);
  }

  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }


  public Long getAd() {
    return ad;
  }

  public void setAd(Long ad) throws NotaException {
  if(ad>=0&&ad<=10){
    this.ad = ad;
  }else{
    throw new NotaException ("Nota INvalida");
  }

  }


  public Long getSge() {
    return sge;
  }

  public void setSge(Long sge) throws NotaException {
    if(sge>=0&&sge<=10){
      this.sge = sge;
    }else{
      throw new NotaException ("Nota INvalida");
    }


  }


  public Long getDi() {
    return di;
  }

  public void setDi(Long di) throws NotaException {
    if(di>=0&&di<=10){
      this.di = di;
    }else{
      throw new NotaException ("Nota INvalida");
    }

  }


  public Long getPmdm() {
    return pmdm;
  }

  public void setPmdm(Long pmdm) throws NotaException {
    if(pmdm>=0&&pmdm<=10){
      this.pmdm = pmdm;
    }else{
      throw new NotaException ("Nota INvalida");
    }

  }


  public Long getPsp() {
    return psp;
  }

  public void setPsp(Long psp) throws NotaException {
    if(psp>=0&&psp<=10){
      this.psp = psp;
    }else{
      throw new NotaException ("Nota INvalida");
    }

  }


  public Long getEie() {
    return eie;
  }

  public void setEie(Long eie) throws NotaException {
    if(eie>=0&&eie<=10){
      this.eie = eie;
    }else{
      throw new NotaException ("Nota INvalida");
    }

  }


  public Long getHlc() {
    return hlc;
  }

  public void setHlc(Long hlc) throws NotaException {
    if(hlc>=0&&hlc<=10){
      this.hlc = hlc;
    }else{
      throw new NotaException ("Nota INvalida");
    }

  }

  public ArrayList<Alumno> getAlumnos() {
    return alumnos;
  }

  public void setAlumnos(ArrayList<Alumno> alumnos) {
    this.alumnos = alumnos;
  }

  @Override
  public String toString() {
    return "Modulo{" +
            "id=" + id +
            ", ad=" + ad +
            ", sge=" + sge +
            ", di=" + di +
            ", pmdm=" + pmdm +
            ", psp=" + psp +
            ", eie=" + eie +
            ", hlc=" + hlc +
            ", alumnos=" + alumnos +
            '}';
  }
}
