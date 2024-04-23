package dao;

import clase.Servicio;

import java.util.List;

public interface DAOServicio {
    public Servicio subida(Servicio servicio);
    public List<Servicio> getAll();
}
