/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package comercializadora.dao;

import comercializadora.modelos.DetalleOrdenes;
import comercializadora.modelos.Orden;
import java.util.List;

/**
 *
 * @author FRANCISCO
 */
public interface IOrdenDao {
    
    public List<Orden> listAll();
    public Orden insert (Orden orden);
    public String update(Orden orden);
    public String delete(Orden orden);
    public Orden findById(long ordenId);
   
    
}
