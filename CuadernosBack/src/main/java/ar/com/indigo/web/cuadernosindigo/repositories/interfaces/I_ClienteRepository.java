
package ar.com.indigo.web.cuadernosindigo.repositories.interfaces;


import ar.com.indigo.web.cuadernosindigo.entities.Cliente;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;


public interface I_ClienteRepository {
    
    void save(Cliente cliente);
    
    List<Cliente> getAll();
    
    default Cliente getById(int id){
       return getAll()
                .stream()
                .filter(c-> c.getId()== id)
                .findAny()
                .orElse(new Cliente());
    }
    
    default List<Cliente> getLikeApellido(String apellido){
        if(apellido == null) return new ArrayList();
       return getAll()
                .stream()
                .filter(c-> c.getApellido().toLowerCase().contains(apellido.toLowerCase()))
                .collect(Collectors.toList());
    }
    
    default List<Cliente>getLikeNombreApellido(String nombre, String apellido){
        if(nombre == null || apellido == null) return new ArrayList<Cliente>();
        return getAll()
                .stream()
                .filter(c->c.getNombre().toLowerCase().contains(nombre.toLowerCase())
                && c.getApellido().toLowerCase().contains(apellido.toLowerCase()))
                .collect(Collectors.toList());
    }
}
