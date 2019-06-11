/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package comercializadora.dao;

import comercializadora.modelos.Empleado;
import java.util.List;

/**
 *
 * @author FRANCISCO
 */
public interface IEmpleadoDao {
    public List<Empleado> listAll();
    public String insert (Empleado emp);
    public String update(Empleado emp);
    public String delete(Empleado emp);
    public Empleado findById(long empleadoId);
    
    
}
