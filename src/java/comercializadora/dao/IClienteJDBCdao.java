/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package comercializadora.dao;

import comercializadora.modelos.Cliente;
import java.util.List;

/**
 *
 * @author FRANCISCO
 */
public interface IClienteJDBCdao {
    
    public List <Cliente> listaClientes();
    public String insert(Cliente cli);
    public String update(Cliente cli);
    public String delete(Cliente cli);
    public Cliente findById(long cliId);
    
}
