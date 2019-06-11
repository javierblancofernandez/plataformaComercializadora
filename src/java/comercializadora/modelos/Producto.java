/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package comercializadora.modelos;

/**
 *
 * @author FRANCISCO
 */
public class Producto {
    
    private long productoId;
    private Proveedor proveedorId;
    private Categoria categoriaId;
    private String descripcion;
    private double precioUnit;
    private double existencia;

    public Producto() {
    }

    public Producto(long productoId) {
        this.productoId = productoId;
    }

    public Producto(long productoId, Proveedor proveedorId, Categoria categoriaId, String descripcion, double precioUnit, double existencia) {
        this.productoId = productoId;
        this.proveedorId = proveedorId;
        this.categoriaId = categoriaId;
        this.descripcion = descripcion;
        this.precioUnit = precioUnit;
        this.existencia = existencia;
    }

   
    
    

    public long getProductoId() {
        return productoId;
    }

    public void setProductoId(long productoId) {
        this.productoId = productoId;
    }

    public Proveedor getProveedorId() {
        return proveedorId;
    }

    public void setProveedorId(Proveedor proveedorId) {
        this.proveedorId = proveedorId;
    }

    public Categoria getCategoriaId() {
        return categoriaId;
    }

    public void setCategoriaId(Categoria categoriaId) {
        this.categoriaId = categoriaId;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public double getPrecioUnit() {
        return precioUnit;
    }

    public void setPrecioUnit(double precioUnit) {
        this.precioUnit = precioUnit;
    }

    public double getExistencia() {
        return existencia;
    }

    public void setExistencia(double existencia) {
        this.existencia = existencia;
    }


    
    
}
