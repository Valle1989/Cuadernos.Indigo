
package ar.com.indigo.web.cuadernosindigo.repositories.interfaces;


import ar.com.indigo.web.cuadernosindigo.entities.Cliente;
import ar.com.indigo.web.cuadernosindigo.entities.Pedido;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;


public interface I_PedidoRepository {
    
    void save(Pedido pedido);
    
    List<Pedido> getAll();
    
    default List<Pedido> getByCliente(Cliente cliente){
        if(cliente == null) return new ArrayList<Pedido>();
        return getAll()
                .stream()
                .filter(p -> p.getIdCliente() == cliente.getId())
                .collect(Collectors.toList());
    }
    
    default Pedido getById(int id){
        return getAll()
                .stream()
                .filter(c->c.getId()==id)
                .findAny()
                .orElse(new Pedido());   
    }
    
}
