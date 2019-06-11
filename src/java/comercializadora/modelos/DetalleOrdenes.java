/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package comercializadora.modelos;

import java.text.DecimalFormat;

/**
 *
 * @author FRANCISCO
 */
public class DetalleOrdenes {
    private long detalleId;
    private Orden orden;
    private Producto producto;
    private double cantidad;
    private double importe;
    private String importeRedondeado;
    public DetalleOrdenes() {
    }

    public DetalleOrdenes(long detalleId, Orden orden, Producto producto, double cantidad, double importe) {
        this.detalleId = detalleId;
        this.orden = orden;
        this.producto = producto;
        this.cantidad = cantidad;
        this.importe = importe;
    }

    public long getDetalleId() {
        return detalleId;
    }

    public void setDetalleId(long detalleId) {
        this.detalleId = detalleId;
    }

    public Orden getOrden() {
        return orden;
    }

    public void setOrden(Orden orden) {
        this.orden = orden;
    }

    public Producto getProducto() {
        return producto;
    }

    public void setProducto(Producto producto) {
        this.producto = producto;
    }

    public double getCantidad() {
        return cantidad;
    }

    public void setCantidad(double cantidad) {
        this.cantidad = cantidad;
    }

    public double getImporte() {
        return importe;
    }

    public void setImporte(double importe) {
        this.importe = importe;
    }

    public String getImporteRedondeado() {
        return new DecimalFormat("#.##").format(importe) ;//para formatear el importe ;
    }

    public void setImporteRedondeado(String importeRedondeado) {
        this.importeRedondeado = importeRedondeado;
    }

    
    
    
    

    
    
    
    
}
