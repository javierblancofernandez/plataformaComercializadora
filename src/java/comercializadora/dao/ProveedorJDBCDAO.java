/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package comercializadora.dao;

import comercializadora.modelos.Categoria;
import comercializadora.modelos.Proveedor;
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
public class ProveedorJDBCDAO implements IProveedorDao {
    
    PreparedStatement ps = null;

    @Override
    public List<Proveedor> listAll() {
        List<Proveedor> listaProveedor = new ArrayList<>();
        Proveedor prov;
        BaseDatosPG base = new BaseDatosPG();
        String sql = "Select * FROM proveedores";

        try {
            ps = base.getConnection().prepareStatement(sql);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                prov = new Proveedor();
                prov.setProveedorId(rs.getLong("PROVEEDORID"));
                prov.setNombreProv(rs.getString("NOMBREPROV"));
                prov.setContacto(rs.getString("CONTACTO"));
                prov.setCeluProv(rs.getString("CELUPROV"));
                prov.setFijoProv(rs.getString("FIJOPROV"));
                listaProveedor.add(prov);
            }
        } catch (SQLException ex) {
            System.out.println("SQL error :" + ex.getMessage());
        }
        return listaProveedor;

    }

    @Override
    public String insert(Proveedor prov) {
         String correcto="";
        try {
            String sql = "INSERT INTO proveedores(proveedorid,nombreprov,contacto,celuprov,fijoprov) VALUES(?,?,?,?,?)";
            BaseDatosPG base = new BaseDatosPG();
            ps=base.getConnection().prepareStatement(sql);
            ps.setLong(1,prov.getProveedorId());
            ps.setString(2, prov.getNombreProv());
            ps.setString(3, prov.getContacto());
            ps.setString(4, prov.getCeluProv());
            ps.setString(5, prov.getFijoProv());
            ps.executeUpdate();
            correcto="la grabacion fue un exito";
            base.desconectarBD();
        } catch (SQLException ex) {
            System.out.println("SQL error :" + ex.getMessage());
        }
        return correcto;
    }

    @Override
    public String update(Proveedor prov) {
         String correcto="";
        try {
            String sql = "UPDATE proveedores SET nombreprov=?,contacto=?,celuprov=?,fijoprov=? where proveedorid =?";
            BaseDatosPG base = new BaseDatosPG();
            ps=base.getConnection().prepareStatement(sql);
            ps.setLong(5,prov.getProveedorId());
            ps.setString(1, prov.getNombreProv());
            ps.setString(2, prov.getContacto());
            ps.setString(3, prov.getCeluProv());
            ps.setString(4, prov.getFijoProv());
            ps.executeUpdate();
            correcto="la Actualizacion fue un exito";
            base.desconectarBD();
        } catch (SQLException ex) {
            ex.getStackTrace();
            correcto="no fue posible actualizar SQL error :" + ex.getMessage();
        }
        return correcto;
    }

    @Override
    public String delete(Proveedor prov) {
               String correcto="";
        try {
            String sql = "DELETE FROM proveedores where proveedorid ='"+prov.getProveedorId()+"'";
            BaseDatosPG base = new BaseDatosPG();
            ps=base.getConnection().prepareStatement(sql);
            
            ps.executeUpdate();
            correcto="la eliminacion fue un exito";
            base.desconectarBD();
        } catch (SQLException ex) {
            ex.getStackTrace();
            correcto="no fue posible eliminar SQL error :" + ex.getMessage();
        }

    
    return correcto;
    }

    @Override
    public Proveedor findById(long proveedorId) {
         Proveedor prov=null;
        BaseDatosPG base = new BaseDatosPG();
        String sql = "Select * FROM proveedores WHERE PROVEEDORID=? LIMIT 1 ";

        try {
            ps = base.getConnection().prepareStatement(sql);
            ps.setLong(1,proveedorId);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                prov = new Proveedor();
                prov.setProveedorId(rs.getLong("PROVEEDORID"));
                prov.setNombreProv(rs.getString("NOMBREPROV"));
                prov.setContacto(rs.getString("CONTACTO"));
                prov.setCeluProv(rs.getString("CELUPROV"));
                prov.setFijoProv(rs.getString("FIJOPROV"));
                
            }
            base.desconectarBD();
        } catch (SQLException ex) {
            System.out.println("SQL error :" + ex.getMessage());
        }
        return prov;
    }
    
}
