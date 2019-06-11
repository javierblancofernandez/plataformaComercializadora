/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package comercializadora.dao;

import comercializadora.modelos.Categoria;
import java.util.List;

/**
 *
 * @author FRANCISCO
 */
public interface ICategoriaDao {
    
    public List<Categoria> listAll();
    public String insert(Categoria cat);
    public Categoria findByID(long iDcat);
    public String update(Categoria cat);
    public String borrarByID(Categoria cat);
    public List<Categoria> busquedaPorCriterio(String param);
    
}
