/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package comercializadora.dao;

import comercializadora.modelos.DetalleOrdenes;
import comercializadora.modelos.Orden;
import comercializadora.modelos.Producto;
import conexiones.BaseDatosPG;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author FRANCISCO
 */
public class DetalleOrdenesJDBCDao implements IDetalleOrdenesDao{

    @Override
    public List<DetalleOrdenes> getDetalles(Orden orden) {
        DetalleOrdenes detalle;
        List<DetalleOrdenes> detalles = new ArrayList<>();
        
        IProductoDao daoProducto = new ProductoJDBCDao();
        BaseDatosPG base = new BaseDatosPG();
        
        try{
        String sql = "SELECT * FROM detalle_ordenes WHERE ORDENID=? ";
        PreparedStatement ps = base.getConnection().prepareStatement(sql);
         ps.setLong(1, orden.getOrdenId());

            ResultSet rs = ps.executeQuery();
            
            while(rs.next()){
            long detalleId = rs.getLong("DETALLEID");
            long productoId = rs.getLong("PRODUCTOID");
            double cantidad = rs.getDouble("CANTIDAD");
            double importe = rs.getDouble("IMPORTE");
            
            Producto pro=daoProducto.findById(productoId);
            
            detalle=new DetalleOrdenes(detalleId, orden, pro, cantidad, importe);
            detalles.add(detalle);
            }
        }catch(SQLException ex){
        ex.getStackTrace();
        }finally{
         if (base.getConnection() != null) {
                base.desconectarBD();
            }
        }
        return detalles ;
    }

    @Override
    public List<DetalleOrdenes> getDetalles(long idOrden) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public DetalleOrdenes insert(DetalleOrdenes detalle) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public String update(DetalleOrdenes detalle) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public String delete(DetalleOrdenes detalle) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public DetalleOrdenes findById(long detalleId) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}
