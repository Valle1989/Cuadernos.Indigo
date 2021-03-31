
package ar.com.indigo.web.cuadernosindigo.repositories.jdbc;


import ar.com.indigo.web.cuadernosindigo.entities.Pedido;
import ar.com.indigo.web.cuadernosindigo.repositories.interfaces.I_PedidoRepository;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;


public class PedidoRepository implements I_PedidoRepository {
    
    private Connection conn;

    public PedidoRepository(Connection conn) {
        this.conn = conn;
    }

    @Override
    public void save(Pedido pedido) {
        if(pedido==null) return;
        try (PreparedStatement ps=conn.prepareStatement(
                "insert into pedidos (fecha,idCliente) values (?,?)",
                PreparedStatement.RETURN_GENERATED_KEYS)){
            ps.setDate(1, pedido.getFecha());
            ps.setInt(2, pedido.getIdCliente());
            ps.execute();
            ResultSet rs=ps.getGeneratedKeys();
            if(rs.next()) pedido.setId(rs.getInt(1));
        } catch (Exception e) {
            System.out.println("*********************************************");
            System.out.println(e);
            System.out.println("*********************************************");
        }       
    }

    @Override
    public List<Pedido> getAll() {
        List<Pedido> list=new ArrayList();
        try (ResultSet rs=conn.createStatement().executeQuery("select * from pedidos")){
            while(rs.next()){
                list.add(new Pedido(
                        rs.getInt("id"), 
                        rs.getDate("fecha"), 
                        rs.getInt("idCliente")
                ));
            }
        } catch (Exception e) {
            System.out.println("*********************************************");
            System.out.println(e);
            System.out.println("*********************************************");
        }
        return list;
    }
    
  }
    

