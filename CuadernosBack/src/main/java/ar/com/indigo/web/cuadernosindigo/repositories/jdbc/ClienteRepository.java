
package ar.com.indigo.web.cuadernosindigo.repositories.jdbc;


import ar.com.indigo.web.cuadernosindigo.entities.Cliente;
import ar.com.indigo.web.cuadernosindigo.enums.TipoCliente;
import ar.com.indigo.web.cuadernosindigo.repositories.interfaces.I_ClienteRepository;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;



public class ClienteRepository implements I_ClienteRepository {
    
    private Connection conn;

    public ClienteRepository(Connection conn) {
        this.conn = conn;
    }
    
    

    @Override
    public void save(Cliente cliente) {
        if(cliente == null) return;
        try(PreparedStatement ps = conn.prepareStatement(
                "insert into clientes (nombre, apellido, tipoCliente) values(?,?,?)",
                PreparedStatement.RETURN_GENERATED_KEYS
                )){
            ps.setString(1, cliente.getNombre());
            ps.setString(2, cliente.getApellido());
            ps.setString(3, cliente.getTipoCliente().toString());
            ps.execute();
            ResultSet rs = ps.getGeneratedKeys();
            if(rs.next()) cliente.setId(rs.getInt(1));
        }catch(Exception e){
            System.out.println("************************");
            System.out.println(e);
            System.out.println("************************");
        }
    }

    @Override
    public List<Cliente> getAll() {
         List<Cliente> list = new ArrayList();
       try(ResultSet rs = conn.prepareStatement("select * from clientes").executeQuery()){
           while(rs.next()){
               list.add(new Cliente(
                       rs.getInt("id"), 
                       rs.getString("nombre"), 
                       rs.getString("apellido"),
                       TipoCliente.valueOf(rs.getString("tipoCliente"))
               ));
           } 
        }catch(Exception e){
            System.out.println("************************");
            System.out.println(e);
            System.out.println("************************");    
        }
       return list;
    }
    
    @Override
    public List<Cliente> getLikeNombreApellido(String nombre, String apellido) {
        if(nombre==null || apellido==null) return new ArrayList<Cliente>();
        List<Cliente>list=new ArrayList();
        try (ResultSet rs=conn.createStatement().executeQuery(
            "select * from clientes "
                + "where nombre like '%"+nombre+"%' and apellido like '%"+apellido+"%'"
        )){
            while(rs.next()){
                list.add(
                        new Cliente(
                                rs.getInt("id"),
                                rs.getString("nombre"),
                                rs.getString("apellido"),
                                TipoCliente.valueOf(rs.getString("tipoCliente"))
                        )
                );        
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    } 
    
}
