/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package comercializadora.dao;

import comercializadora.modelos.Categoria;
import comercializadora.modelos.Producto;
import java.util.List;

/**
 *
 * @author FRANCISCO
 */
public interface IProductoDao {
    
    public List <Producto> listAll();
    public String insert (Producto prod);
    public String update( Producto prod);
    public String delete ( Producto prod);
    public Producto findById( long idProd);
    public List<Producto> getProductoByCategoria(Categoria cat);
    public List<Producto> busquedaPorCriterio(String param);
    
}
