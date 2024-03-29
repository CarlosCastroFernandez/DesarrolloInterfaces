package dao;

import clases.Modulo;
import errores.NotaException;
import utils.MYSQLConnection;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import static java.sql.Statement.RETURN_GENERATED_KEYS;

public class ModuloDAO {

    private static final String INSERTAR="insert into modulo (ad,sge,di,pmdm,psp,eie,hlc) values (?,?,?,?,?,?,?)";
    private static final String GETMODULO="select * from modulo where id=?";

public static Modulo insertarModulo(Modulo modulo){
    Modulo modulito=modulo;

    try {
        PreparedStatement ps= MYSQLConnection.getConexion().prepareStatement(INSERTAR, RETURN_GENERATED_KEYS);
        ps.setLong(1,modulo.getAd());
        ps.setLong(2,modulo.getSge());
        ps.setLong(3,modulo.getDi());
        ps.setLong(4,modulo.getPmdm());
        ps.setLong(5,modulo.getPsp());
        ps.setLong(6,modulo.getEie());
        ps.setLong(7,modulo.getHlc());
        Integer numeroFilaInsertada=ps.executeUpdate();
        ResultSet rs=ps.getGeneratedKeys();
        if (rs.next()){
            Long id=rs.getLong(1);
            modulito.setId(id);
        }
        return modulito;
    } catch (SQLException e) {
        throw new RuntimeException(e);
    }
}
public static Modulo getModuloBYId(Long id){
    Modulo modulo=new Modulo();
    try {
        PreparedStatement pst=MYSQLConnection.getConexion().prepareStatement(GETMODULO);
        pst.setLong(1,id);
        ResultSet rs=pst.executeQuery();
        if(rs.next()){
            modulo=new Modulo(rs.getLong("id"),rs.getLong("ad"),rs.getLong("sge"),rs.getLong("di"),
                    rs.getLong("pmdm"),rs.getLong("psp"),rs.getLong("eie"),rs.getLong("hlc"));
        }
        return modulo;
    } catch (SQLException e) {
        throw new RuntimeException(e);
    } catch (NotaException e) {
        throw new RuntimeException(e);
    }
}
}
