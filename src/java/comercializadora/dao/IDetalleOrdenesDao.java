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
public interface IDetalleOrdenesDao {
    
    public List<DetalleOrdenes> getDetalles (Orden orden);
    public List<DetalleOrdenes> getDetalles ( long idOrden);
    public DetalleOrdenes insert(DetalleOrdenes detalle);
  
    public String update(DetalleOrdenes detalle);
    public String delete(DetalleOrdenes detalle);
    public DetalleOrdenes findById(long detalleId);
    
}
