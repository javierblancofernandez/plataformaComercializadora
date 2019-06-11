/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package comercializadora.dao;

import comercializadora.modelos.Usuario;
import conexiones.BaseDatosPG;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author FRANCISCO
 */
public class UsuarioJDBCDao implements IUsuarioDao{
    PreparedStatement ps;
    @Override
    public Usuario crearUsuario(Usuario usuario) {
        BaseDatosPG base = new BaseDatosPG();
        try {
            
            
            String sql = " INSERT INTO usuarios (usuario,password)"
                    + " VALUES (?,?)";
            
            ps = base.getConnection().prepareStatement(sql,PreparedStatement.RETURN_GENERATED_KEYS);
            
            ps.setString(1, usuario.getUsuario());
            ps.setString(2, usuario.getPassword());
            ps.executeUpdate();
            
            int idGenerado;
            
            ResultSet generatedKeys= ps.getGeneratedKeys();
            if (generatedKeys.next()) {
                idGenerado = generatedKeys.getInt(1);
                usuario.setIdUsuario(idGenerado);
                
            }
            base.desconectarBD();
            
            
        } catch (SQLException ex) {
            ex.printStackTrace();
            
        }finally{
            if (base.getConnection()!=null) {
                base.desconectarBD();
                
            }
        }
                
        return usuario;
    }

    @Override
    public Usuario validarUsuario(String usuario,String pass) {
        BaseDatosPG base = new BaseDatosPG();
        Usuario user=null;
        try {
            
            
            String sql = "SELECT * FROM usuarios WHERE usuario = ? and password = ? LIMIT 1";
            
            ps = base.getConnection().prepareStatement(sql);
            ps.setString(1, usuario);
            ps.setString(2, pass);
            ResultSet rs = ps.executeQuery();
            
            while(rs.next()){
            user = new Usuario();
            long idUsuario = rs.getLong("idusuario");
            String nombUsuario = rs.getString("usuario");
            String passUsuario = rs.getString("password");
            
            user.setIdUsuario(idUsuario);
            user.setUsuario(usuario);
            user.setPassword(pass);
            }
             base.desconectarBD();
        } catch (SQLException ex) {
            System.out.println("Error al obtneer el Usaurio :"
             + ex.getMessage());
        }finally{
            if (base.getConnection()!=null) {
                base.desconectarBD();
                
            }
       
    }
        return user;
           
}
}
