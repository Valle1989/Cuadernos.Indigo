
package ar.com.indigo.web.cuadernosindigo.repositories.interfaces;

import ar.com.indigo.web.cuadernosindigo.entities.Cuaderno;
import java.util.List;


public interface I_CuadernoRepository {
    
    public void save(Cuaderno cuaderno);
	
    public void update(Cuaderno cuaderno);
	
    public void remove(Cuaderno cuaderno);
	
    public List<Cuaderno> getAll();
	
    default Cuaderno getById(int id){
        return getAll()
                .stream()
                .filter(c->c.getId()==id)
                .findAny()
                .orElse(new Cuaderno());
    }
    
    
}
