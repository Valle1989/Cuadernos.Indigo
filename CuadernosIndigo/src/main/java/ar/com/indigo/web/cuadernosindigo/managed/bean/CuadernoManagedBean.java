
package ar.com.indigo.web.cuadernosindigo.managed.bean;

import ar.com.indigo.web.cuadernosindigo.entities.Cuaderno;
import ar.com.indigo.web.cuadernosindigo.enums.Interiores;
import ar.com.indigo.web.cuadernosindigo.interfaces.I_CuadernoRepository;
import ar.com.indigo.web.cuadernosindigo.repositories.rest.CuadernoRepository;
import java.io.Serializable;
import java.util.Arrays;
import java.util.List;
import javax.enterprise.context.SessionScoped;
import javax.inject.Named;

@Named("cuadernoMB")
@SessionScoped
public class CuadernoManagedBean implements Serializable {
    
    private I_CuadernoRepository cr = new CuadernoRepository("http://localhost:8086/CuadernosBack/resources/cuadernos/v1");
    
    private Cuaderno cuaderno = new Cuaderno();
    
    private String mensaje = "";
    
    public void save(){
        cr.save(cuaderno);
        mensaje = "Se ha ingreaso el pedido con id: " + cuaderno.getId();
        cuaderno = new Cuaderno();
    }
    
    public void update(){
        cr.update(cuaderno);
        mensaje = "Se ha actualizado el pedido con id:" + cuaderno.getId();
        cuaderno = new Cuaderno();
    }
    
    public void remove(){
        cr.remove(cuaderno);
        mensaje = "Se ha eliminado el pedido con id:" + cuaderno.getId();
    }
    
    public List<Cuaderno> getAll(){
        return cr.getAll();
    }
    
    public List<Interiores>getInteriores(){
        return Arrays.asList(Interiores.values());
    }

    public Cuaderno getCuaderno() {
        return cuaderno;
    }

    public void setCuaderno(Cuaderno cuaderno) {
        this.cuaderno = cuaderno;
    }

    public String getMensaje() {
        return mensaje;
    }

    public void setMensaje(String mensaje) {
        this.mensaje = mensaje;
    }
    
    
    
}
