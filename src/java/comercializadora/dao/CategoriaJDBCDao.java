/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package comercializadora.dao;

import comercializadora.modelos.Categoria;
import conexiones.BaseDatosPG;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author FRANCISCO
 */
public class CategoriaJDBCDao implements ICategoriaDao {

    PreparedStatement ps = null;

    @Override
    public List<Categoria> listAll() {
        //codigo para acceder mediante JDBC a registros
        // de categorias de una base de datos
        List<Categoria> listaCategoria = new ArrayList<>();
        Categoria cat;
        BaseDatosPG base = new BaseDatosPG();
        String sql = "Select * FROM categorias";

        try {
            ps = base.getConnection().prepareStatement(sql);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                cat = new Categoria();
                cat.setCategoriaId(rs.getInt("CATEGORIAID"));
                cat.setNombreCat(rs.getString("NOMBRECAT"));
                listaCategoria.add(cat);
            }
        } catch (SQLException ex) {
            System.out.println("SQL error :" + ex.getMessage());
        }
        return listaCategoria;

    }

    @Override
    public String insert(Categoria cat) {
        String correcto="";
        try {
            String sql = "INSERT INTO categorias(categoriaid,nombrecat) VALUES(?,?)";
            BaseDatosPG base = new BaseDatosPG();
            ps=base.getConnection().prepareStatement(sql);
            ps.setInt(1, (int) cat.getCategoriaId());
            ps.setString(2, cat.getNombreCat());
            ps.executeUpdate();
            correcto="la grabacion fue un exito";
            base.desconectarBD();
        } catch (SQLException ex) {
            System.out.println("SQL error :" + ex.getMessage());
        }
        return correcto;
        
    }

    @Override
    public Categoria findByID(long iDcat) {
        Categoria cat=null;
        BaseDatosPG base = new BaseDatosPG();
        String sql = "Select * FROM categorias WHERE CATEGORIAID=? LIMIT 1 ";

        try {
            ps = base.getConnection().prepareStatement(sql);
            ps.setLong(1,iDcat);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                cat = new Categoria();
                cat.setCategoriaId(rs.getInt("CATEGORIAID"));
                cat.setNombreCat(rs.getString("NOMBRECAT"));
                
            }
            base.desconectarBD();
        } catch (SQLException ex) {
            System.out.println("SQL error :" + ex.getMessage());
        }
        return cat;
    }

    @Override
    public String update(Categoria cat) {
        String correcto="";
        try {
            String sql = "UPDATE categorias SET nombrecat=? where categoriaid =?";
            BaseDatosPG base = new BaseDatosPG();
            ps=base.getConnection().prepareStatement(sql);
            ps.setInt(1, (int) cat.getCategoriaId());
            ps.setString(2, cat.getNombreCat());
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
    public String borrarByID(Categoria cat) {
        String correcto="";
        try {
            String sql = "DELETE FROM categorias where categoriaid ='"+cat.getCategoriaId()+"'";
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
    public List<Categoria> busquedaPorCriterio(String param) {
       BaseDatosPG base = new BaseDatosPG();
       List<Categoria> listaCategorias=new ArrayList<>();
       Categoria cat;
       
       String sql = "Select * from categorias where NOMBRECAT like ?";
        try {
            ps = base.getConnection().prepareStatement(sql);
            ps.setString(1, "%"+param+"%");
            ResultSet rs = ps.executeQuery();
            
            while(rs.next()){
            cat=new Categoria();
            cat.setCategoriaId(rs.getInt("CATEGORIAID"));
            cat.setNombreCat(rs.getString("NOMBRECAT"));
            listaCategorias.add(cat);
            }
            base.desconectarBD();
            
            
        } catch (SQLException ex) {
            System.out.println("error por busqueda de criterio categorias :"+ex.getMessage());
            
        }finally{
            if (base.getConnection()!=null) {
                base.desconectarBD();
            }
        }
       return listaCategorias;
    }
    
    

}
