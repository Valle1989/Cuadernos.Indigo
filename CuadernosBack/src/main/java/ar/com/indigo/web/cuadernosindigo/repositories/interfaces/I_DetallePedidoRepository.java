
package ar.com.indigo.web.cuadernosindigo.repositories.interfaces;


import ar.com.indigo.web.cuadernosindigo.entities.DetallePedido;
import ar.com.indigo.web.cuadernosindigo.entities.Pedido;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;


public interface I_DetallePedidoRepository {
    
    void save(DetallePedido detalle);
    
    List<DetallePedido> getAll();
    
    default List<DetallePedido> getByPedido(Pedido pedido){
        if(pedido == null) return new ArrayList<DetallePedido>();
        return getAll()
                .stream()
                .filter(p -> p.getIdPedido() == pedido.getId())
                .collect(Collectors.toList());
    }
    
}
