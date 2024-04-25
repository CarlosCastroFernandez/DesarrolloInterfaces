package dao;

import clase.Servicio;

import java.util.List;

public interface DAOServicio {
    public Servicio subida(Servicio servicio);
    public List<Servicio> getAll();
    public void borrar(Servicio servicio);
    public void actualizacionBeneficio(Servicio servicio);
}
