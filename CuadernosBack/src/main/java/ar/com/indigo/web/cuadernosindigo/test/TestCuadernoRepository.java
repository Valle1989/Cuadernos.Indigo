
package ar.com.indigo.web.cuadernosindigo.test;

import ar.com.indigo.web.cuadernosindigo.entities.Cuaderno;
import ar.com.indigo.web.cuadernosindigo.enums.Interiores;
import ar.com.indigo.web.cuadernosindigo.repositories.interfaces.I_CuadernoRepository;
import ar.com.indigo.web.cuadernosindigo.repositories.jdbc.Connector;
import ar.com.indigo.web.cuadernosindigo.repositories.jdbc.CuadernoRepository;


public class TestCuadernoRepository {
    
    public static void main(String[] args) {
       
        I_CuadernoRepository cr = new CuadernoRepository(Connector.getConnection());
    
        Cuaderno cuaderno = new Cuaderno(4, Interiores.AgendaPerpetua);
        
        //cuaderno.setId(3);
        
        cr.save(cuaderno);
        
        System.out.println(cuaderno);
        
        System.out.println("****************************************************");
        
        cr.getAll().forEach(System.out::println);
    }
  
}
