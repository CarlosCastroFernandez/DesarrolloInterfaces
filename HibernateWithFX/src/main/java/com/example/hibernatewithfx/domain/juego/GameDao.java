package com.example.hibernatewithfx.domain.juego;

import com.example.hibernatewithfx.domain.DAO;
import com.example.hibernatewithfx.domain.HibernateUtil;
import lombok.extern.java.Log;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
@Log

public class GameDao implements DAO<Game> {

    public static final HashMap<String,String>QUERY_ATTR;
    static{
        QUERY_ATTR=new HashMap<>();
        QUERY_ATTR.put("studio","select distinct(g.study) from Game g");
        QUERY_ATTR.put("empresa","select distinct(g.enterprise) from Game g");
        QUERY_ATTR.put("plataforma","select distinct(g.plataform) from Game g");
        QUERY_ATTR.put("formato","select distinct(g.format) from Game g");
        QUERY_ATTR.put("estadoCaja","select distinct(g.boxStatus) from Game g");
        QUERY_ATTR.put("estadoJuego","select distinct(g.gameStatus) from Game g");
        QUERY_ATTR.put("category","select distinct(g.category) from Game g");
    }
    @Override
    public ArrayList<Game> getAll() {
        return null;
    }
  /*  public ArrayList<Game>getAllFromUser(User u){
        ArrayList<Game>results=new ArrayList<>();
        try(Session s= HibernateUtil.getSesionFactory().openSession()){
            Query<Game> q=s.createQuery("from Game where usuarioId=:userId",Game.class);
            q.setParameter("userId",u.getId());
            results= (ArrayList<Game>) q.getResultList();
        }
        return results;
    }*/

    @Override
    public Game get(Long id) {
        return null;
    }

    @Override

    public Game save(Game data) {
        Game salida=null;
        try(org.hibernate.Session s= HibernateUtil.getSesionFactory().openSession()){
            Transaction t=s.beginTransaction();
            s.persist(data);
            t.commit();
            salida=data;

        }catch(Exception e){
            log.severe("Error al guardar juego");
        }
        return salida;
    }

    @Override
    public void update(Game data) {

        try(org.hibernate.Session s= HibernateUtil.getSesionFactory().openSession()){
            Transaction t=s.beginTransaction();
            Game g=s.get(Game.class, data.getId());
            Game.merge(data, g);
            t.commit();

        }

    }



    @Override
    public void delete(Game data) {
        try(Session s=HibernateUtil.getSesionFactory().openSession()){
            Transaction t=s.beginTransaction();
            Game g=s.get(Game.class,data.getId());
            s.remove(g);
            t.commit();

        }

    }
    public List<String> getCategories(){
        ArrayList<String>results=new ArrayList<>();
        try(Session s= HibernateUtil.getSesionFactory().openSession()){
            Query<String> q=s.createQuery("select distinct(g.category) from Game g" ,String.class);
            results= (ArrayList<String>) q.getResultList();
        }
        return results;
    }
    public List<String> getFormato(){
        ArrayList<String>results=new ArrayList<>();
        try(Session s= HibernateUtil.getSesionFactory().openSession()){
            Query<String> q=s.createQuery("select distinct(g.format) from Game g" ,String.class);
            results= (ArrayList<String>) q.getResultList();
        }
        return results;
    }
    public List<String> getPlataform(){
        ArrayList<String>results=new ArrayList<>();
        try(Session s= HibernateUtil.getSesionFactory().openSession()){
            Query<String> q=s.createQuery("select distinct(g.plataform) from Game g" ,String.class);
            results= (ArrayList<String>) q.getResultList();
        }
        return results;
    }
    public List<String> getEstadoJuego(){
        ArrayList<String>results=new ArrayList<>();
        try(Session s= HibernateUtil.getSesionFactory().openSession()){
            Query<String> q=s.createQuery("select distinct(g.gameStatus) from Game g" ,String.class);
            results= (ArrayList<String>) q.getResultList();
        }
        return results;
    }
    public List<String> getEstadoCaja(){
        ArrayList<String>results=new ArrayList<>();
        try(Session s= HibernateUtil.getSesionFactory().openSession()){
            Query<String> q=s.createQuery("select distinct(g.boxStatus) from Game g" ,String.class);
            results= (ArrayList<String>) q.getResultList();
        }
        return results;
    }
    public List<String> getEstudio(){
        ArrayList<String>results=new ArrayList<>();
        try(Session s= HibernateUtil.getSesionFactory().openSession()){
            Query<String> q=s.createQuery("select distinct(g.study) from Game g" ,String.class);
            results= (ArrayList<String>) q.getResultList();
        }
        return results;
    }
    public List<String> getEmpresa(){
        ArrayList<String>results=new ArrayList<>();
        try(Session s= HibernateUtil.getSesionFactory().openSession()){
            Query<String> q=s.createQuery("select distinct(g.enterprise) from Game g" ,String.class);
            results= (ArrayList<String>) q.getResultList();
        }
        return results;
    }
    public List<String> getDistinctFormatAtribute(String attr){
        ArrayList<String>results=new ArrayList<>();
        try(Session s= HibernateUtil.getSesionFactory().openSession()){
            Query<String> q=s.createQuery(QUERY_ATTR.get(attr) ,String.class);
            results= (ArrayList<String>) q.getResultList();
        }
        return results;
    }
}
