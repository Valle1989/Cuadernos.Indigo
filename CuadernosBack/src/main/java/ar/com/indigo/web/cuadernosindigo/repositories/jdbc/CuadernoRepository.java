
package ar.com.indigo.web.cuadernosindigo.repositories.jdbc;

import ar.com.indigo.web.cuadernosindigo.entities.Cuaderno;
import ar.com.indigo.web.cuadernosindigo.enums.Interiores;
import ar.com.indigo.web.cuadernosindigo.repositories.interfaces.I_CuadernoRepository;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;


public class CuadernoRepository implements I_CuadernoRepository {
    
    private Connection conn;

    public CuadernoRepository(Connection conn) {
        this.conn = conn;
    }

    @Override
    public void save(Cuaderno cuaderno) {
        if(cuaderno == null) return;
        try (PreparedStatement ps = conn.prepareStatement(
            "insert into cuaderno (tapas, interiores) values (?,?)",
                PreparedStatement.RETURN_GENERATED_KEYS
        )) {
            ps.setInt(1, cuaderno.getTapas());
            ps.setString(2, cuaderno.getInteriores().toString());
            ps.execute();
            ResultSet rs = ps.getGeneratedKeys();
            if(rs.next()) cuaderno.setId(rs.getInt(1));
        } catch (Exception e) {
            System.out.println("*********************************************");
            System.out.println(e);
            System.out.println("*********************************************");
        }
        
    }

    @Override
    public void update(Cuaderno cuaderno) {
        if(cuaderno == null) return;
        try(PreparedStatement ps = conn.prepareStatement(
            "update cuaderno set tapas = ?, interiores = ? where id = ?")){
            ps.setInt(1, cuaderno.getTapas());
            ps.setString(2, cuaderno.getInteriores().toString());
            ps.setInt(3, cuaderno.getId());
            ps.execute();
        } catch (Exception e) {
            System.out.println("************************");
            System.out.println(e);
            System.out.println("************************");
        }
    }
        
   

    @Override
    public void remove(Cuaderno cuaderno) {
        if(cuaderno == null) return;
        try(PreparedStatement ps = conn.prepareStatement(
                "delete from cuaderno where id = ?")){
            ps.setInt(1, cuaderno.getId());
            ps.execute();
        }catch(Exception e){
            System.out.println("************************");
            System.out.println(e);
            System.out.println("************************");    
        }
    }

    @Override
    public List<Cuaderno> getAll() {
        List<Cuaderno> list = new ArrayList();
       try(ResultSet rs = conn.prepareStatement("select * from cuaderno").executeQuery()){
           while(rs.next()){
               list.add(new Cuaderno(
                       rs.getInt("id"), 
                       rs.getInt("tapas"),
                       Interiores.valueOf(rs.getString("interiores"))
               ));
           } 
        }catch(Exception e){
            System.out.println("************************");
            System.out.println(e);
            System.out.println("************************");    
        }
       return list;
    }

    
}
