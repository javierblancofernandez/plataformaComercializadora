/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package comercializadora.modelos;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 *
 * @author FRANCISCO
 */
public class Orden {
    private long ordenId;
    private Empleado empleado;
    private Cliente cliente;
    private Date fechaOrden;
    private double descuento;
    private double importe;
    private String importeRedondeado;
    private List<DetalleOrdenes> detalles = new ArrayList<>();

    public Orden() {
    }

    
    
    
    public Orden(long ordenId, Empleado empleado, Cliente cliente, Date fechaOrden, double descuento, double importe) {
        this.ordenId = ordenId;
        this.empleado = empleado;
        this.cliente = cliente;
        this.fechaOrden = fechaOrden;
        this.descuento = descuento;
        this.importe = importe;
        
    }
    
    

    public long getOrdenId() {
        return ordenId;
    }

    public void setOrdenId(long ordenId) {
        this.ordenId = ordenId;
    }

    public Date getFechaOrden() {
        return fechaOrden;
    }

    public void setFechaOrden(Date fechaOrden) {
        this.fechaOrden = fechaOrden;
    }

    public double getDescuento() {
        return descuento;
    }

    public void setDescuento(double descuento) {
        this.descuento = descuento;
    }

    public double getImporte() {
        return importe;
    }

    public void setImporte(double importe) {
        this.importe = importe;
    }

    public String getImporteRedondeado() {
        return new DecimalFormat("#.##").format(importe) ;//para formatear el importe 
    }

    public void setImporteRedondeado(String importeRedondeado) {
        this.importeRedondeado = importeRedondeado;
    }

    public List<DetalleOrdenes> getDetalles() {
        return detalles;
    }

    public void setDetalles(List<DetalleOrdenes> detalles) {
        this.detalles = detalles;
    }

    public Empleado getEmpleado() {
        return empleado;
    }

    public void setEmpleado(Empleado empleado) {
        this.empleado = empleado;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }
    
    
}
