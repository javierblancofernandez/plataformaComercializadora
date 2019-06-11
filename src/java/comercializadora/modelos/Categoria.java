/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package comercializadora.modelos;

import java.util.List;

/**
 *
 * @author FRANCISCO
 */
public class Categoria {
    private long categoriaId;
    private String nombreCat;
    private List<Producto> productos;

    public Categoria() {
    }

    public Categoria(long categoriaId, String nombreCat) {
        this.categoriaId = categoriaId;
        this.nombreCat = nombreCat;
    }

    public Categoria(long categoriaId) {
        this.categoriaId = categoriaId;
    }
    
    public long getCategoriaId() {
        return categoriaId;
    }

    public void setCategoriaId(long categoriaId) {
        this.categoriaId = categoriaId;
    }

    public String getNombreCat() {
        return nombreCat;
    }

    public void setNombreCat(String nombreCat) {
        this.nombreCat = nombreCat;
    }

    public List<Producto> getProductos() {
        return productos;
    }

    public void setProductos(List<Producto> productos) {
        this.productos = productos;
    }


}
