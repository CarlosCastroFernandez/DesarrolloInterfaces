package com.example.hibernatewithfx.domain.usuario;

import com.example.hibernatewithfx.domain.DAO;
import com.example.hibernatewithfx.domain.HibernateUtil;
import org.hibernate.Session;
import org.hibernate.query.Query;

import java.util.ArrayList;

public class UserDAO implements DAO<User> {
    @Override
    public ArrayList<User> getAll() {
        ArrayList<User>usuarios=new ArrayList<>();
        try(Session s=HibernateUtil.getSesionFactory().openSession()){
            Query<User>q=s.createQuery("from User", User.class);
            usuarios= (ArrayList<User>) q.getResultList();
        }
        return usuarios;
    }

    @Override
    public User get(Long id) {
        User usuario=new User();
        try(Session s=HibernateUtil.getSesionFactory().openSession()){
            usuario=s.get(User.class,id);
        }
        return usuario;
    }

    @Override
    public User save(User data) {
        return null;
    }

    @Override
    public void update(User data) {

    }

    @Override
    public void delete(User data) {

    }
    public User validateUser(String username,String password){
        User usuarioValido=null;
        try(Session s=HibernateUtil.getSesionFactory().openSession()){
            Query<User> q=s.createQuery("from User where username=:u and password=:p",User.class);
            q.setParameter("u",username);
            q.setParameter("p",password);
            try{
                usuarioValido= q.getSingleResult();
            }catch(Exception e){
               e.printStackTrace();
            }
            System.out.println(usuarioValido);
        }
        return usuarioValido;
    }
}
