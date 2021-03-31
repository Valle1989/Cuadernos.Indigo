
package ar.com.indigo.web.cuadernosindigo.repositories.interfaces;

import ar.com.indigo.web.cuadernosindigo.entities.Articulo;
import java.util.ArrayList;
import java.util.List;
import java.util.Queue;
import java.util.stream.Collectors;

public interface I_ArticuloRepository {
    
    void save(Articulo articulo);
    
    void remove(Articulo articulo);
    
    Queue<Articulo> obtenerLista();
    
    List<Articulo> getAll(); //Select * from articulos
    
    default List<Articulo>getLikeDescripcion(String descripcion){
            //Select * from articulos where descripcion like %descripcion%
    List<Articulo> list = new ArrayList();
    //if(descripcion == null) return list;
    //for(Articulo a : getAll()){
        //if(a.getDescripcion().toLowerCase().contains(descripcion.toLowerCase()));
            //list.add(a);
        //}
        //return list;
        if(descripcion == null) return new ArrayList<Articulo>();
        return getAll()
                .stream()
                .filter(a ->a.getDescripcion().toLowerCase().contains(descripcion.toLowerCase()))
                .collect(Collectors.toList());
    }
    
    default Articulo getById(int id){
        return getAll()
                .stream()
                .filter(c->c.getId()==id)
                .findAny()
                .orElse(new Articulo());   
    }
         
}
