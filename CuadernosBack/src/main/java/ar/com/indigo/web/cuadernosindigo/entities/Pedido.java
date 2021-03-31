
package ar.com.indigo.web.cuadernosindigo.entities;

import java.sql.Date;


public class Pedido {
    
    private int id;
    
    private Date fecha;
    
    private int idCliente;

    public Pedido() {
    }

    public Pedido(Date fecha, int idCliente) {
        this.fecha = fecha;
        this.idCliente = idCliente;
    }

    public Pedido(int id, Date fecha, int idCliente) {
        this.id = id;
        this.fecha = fecha;
        this.idCliente = idCliente;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    public int getIdCliente() {
        return idCliente;
    }

    public void setIdCliente(int idCliente) {
        this.idCliente = idCliente;
    }

    @Override
    public String toString() {
        return "Pedido{" + "id=" + id + ", fecha=" + fecha + ", idCliente=" + idCliente + '}';
    }
    
    
    
}
