/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package comercializadora.dao;

import comercializadora.modelos.Usuario;

/**
 *
 * @author FRANCISCO
 */
public interface IUsuarioDao {
    
    Usuario crearUsuario ( Usuario usuario);
    Usuario validarUsuario ( String usuario,String pass);
    
}
