
package ar.com.indigo.web.cuadernosindigo.repositories.jdbc;


import ar.com.indigo.web.cuadernosindigo.entities.DetallePedido;
import ar.com.indigo.web.cuadernosindigo.repositories.interfaces.I_DetallePedidoRepository;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;


public class DetallePedidoRepository implements I_DetallePedidoRepository {
    
    private Connection conn;

    public DetallePedidoRepository(Connection conn) {
        this.conn = conn;
    }
   
    @Override
    public void save(DetallePedido detalle) {
        if(detalle==null) return;
        try (PreparedStatement ps=conn.prepareStatement(
                "insert into detallesPedido (idPedido, idArticulo, cantidad) values (?,?,?)"
        )) {
            ps.setInt(1, detalle.getIdPedido());
            ps.setInt(2, detalle.getIdArticulo());
            ps.setInt(3, detalle.getCantidad());
            ps.execute();
        } catch (Exception e) {
            System.out.println("*********************************************");
            System.out.println(e);
            System.out.println("*********************************************");
        }
    }

    @Override
    public List<DetallePedido> getAll() {
        List<DetallePedido>list=new ArrayList();
        try (ResultSet rs=conn.createStatement().executeQuery("select * from detallesPedido")) {
            while(rs.next()){
                list.add(
                        new DetallePedido(
                                rs.getInt("idPedido"), 
                                rs.getInt("idArticulo"), 
                                rs.getInt("cantidad"))
                );
            }
        } catch (Exception e) {
            System.out.println("*********************************************");
            System.out.println(e);
            System.out.println("*********************************************");
        }
        return list;
    }
    
}
