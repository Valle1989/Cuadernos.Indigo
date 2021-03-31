
package ar.com.indigo.web.cuadernosindigo.repositories.jdbc;

import ar.com.indigo.web.cuadernosindigo.entities.Articulo;
import ar.com.indigo.web.cuadernosindigo.repositories.interfaces.I_ArticuloRepository;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import java.util.Queue;

public class ArticuloRepository implements I_ArticuloRepository{
    
    private Connection conn;

    public ArticuloRepository(Connection conn) {
        this.conn = conn;
    }
    
    

    @Override
    public void save(Articulo articulo) {
        if(articulo == null) return;
        try(PreparedStatement ps = conn.prepareStatement(
                "insert into articulos (id, descripcion, precio) values(?,?,?)")){
            ps.setInt(1, articulo.getId());
            ps.setString(2, articulo.getDescripcion());
            ps.setDouble(3, articulo.getPrecio());
            ps.execute();
        }catch(Exception e){
            System.out.println("************************");
            System.out.println(e);
            System.out.println("************************");
        }
    }

    @Override
    public void remove(Articulo articulo) {
        if(articulo == null) return;
        try(PreparedStatement ps = conn.prepareStatement(
                "delete from articulos where id = ?")){
            ps.setInt(1, articulo.getId());
            ps.execute();
        }catch(Exception e){
            System.out.println("************************");
            System.out.println(e);
            System.out.println("************************");    
        }
    }

    @Override
    public List<Articulo> getAll() {
       List<Articulo> list = new ArrayList();
       try(ResultSet rs = conn.prepareStatement("select * from articulos").executeQuery()){
           while(rs.next()){
               list.add(new Articulo(rs.getInt("id"), rs.getString("descripcion"), rs.getDouble("precio")));
           } 
        }catch(Exception e){
            System.out.println("************************");
            System.out.println(e);
            System.out.println("************************");    
        }
       return list;
    }

    @Override
    public Queue<Articulo> obtenerLista() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}
