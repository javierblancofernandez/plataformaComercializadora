/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package comercializadora.dao;

import comercializadora.modelos.Categoria;
import comercializadora.modelos.Empleado;
import conexiones.BaseDatosPG;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import java.util.List;

/**
 *
 * @author FRANCISCO
 */
public class EmpleadoDao implements IEmpleadoDao{
    PreparedStatement ps = null;

    @Override
    public List<Empleado> listAll() {
        List<Empleado> listaEmpleado = new ArrayList<>();
        Empleado empleado;
        BaseDatosPG base = new BaseDatosPG();
        String sql = "Select * FROM empleados";

        try {
            ps = base.getConnection().prepareStatement(sql);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                long empleadoid=rs.getInt("EMPLEADOID");
                String nombre=rs.getString("NOMBRE");
                String apellido=rs.getString("APELLIDO");
                Date fechaNac = rs.getDate("FECHA_NAC");
                int reportaA=rs.getInt("REPORTA_A");
                int extension=rs.getInt("EXTENSION");
                
                empleado=new Empleado(empleadoid, nombre, apellido, fechaNac, reportaA, extension);
                
                
                 if(reportaA>0){
                Empleado jefe= findById(reportaA);
                empleado.setJefe(jefe.getNombreCompleto());
                }
                 listaEmpleado.add(empleado);
            }
        } catch (SQLException ex) {
            System.out.println("SQL error :" + ex.getMessage());
        }
        return listaEmpleado;
    }

    @Override
    public String insert(Empleado emp) {
       String correcto="";
        try {
            String sql = "INSERT INTO empleados(empleadoid,nombre,apellidos,fecha_nac,reporta_a,extension) VALUES(?,?,?,?,?,?)";
            BaseDatosPG base = new BaseDatosPG();
            ps=base.getConnection().prepareStatement(sql);
            ps.setInt(1, (int) emp.getEmpleadoId());
            ps.setString(2, emp.getNombreEmpleado());
             ps.setString(3, emp.getApellidoEmpleado());
             ps.setDate(4, (Date)emp.getFechaNacimiento());
             ps.setInt(5, (int) emp.getReporta_a());
             ps.setInt(6, (int) emp.getExtension());
            ps.executeUpdate();
            correcto="la grabacion fue un exito";
            base.desconectarBD();
        } catch (SQLException ex) {
            System.out.println("SQL error :" + ex.getMessage());
        }
        return correcto;
    }

    @Override
    public String update(Empleado emp) {
       String correcto="";
        try {
            String sql = "UPDATE empleados SET NOMBRE=? , APELLIDO=? ,"
                    + "FECHA_NAC=? , REPORTA_A=? ,EXTENSION=?"
                    + " WHERE EMPLEADOID=?";
            BaseDatosPG base = new BaseDatosPG();
            ps=base.getConnection().prepareStatement(sql);
            ps.setString(1,emp.getNombreEmpleado());
            ps.setString(2, emp.getApellidoEmpleado());
            ps.setDate(3, (Date)emp.getFechaNacimiento());
            ps.setInt(4,emp.getReporta_a());
            ps.setInt(5, emp.getExtension());
            ps.setLong(6, emp.getEmpleadoId());
            
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
    public String delete(Empleado emp) {
       String correcto="";
        try {
            String sql = "DELETE FROM empleados where empleadoid ='"+emp.getEmpleadoId()+"'";
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
    public Empleado findById(long empleadoId) {
        Empleado emp=null;
        BaseDatosPG base = new BaseDatosPG();
        String sql = "Select * FROM empleados WHERE EMPLEADOID=? LIMIT 1 ";

        try {
            ps = base.getConnection().prepareStatement(sql);
            ps.setLong(1,empleadoId);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                
                long empleadoid=rs.getInt("EMPLEADOID");
                String nombre=rs.getString("NOMBRE");
                String apellido=rs.getString("APELLIDO");
                Date fechaNac = rs.getDate("FECHA_NAC");
                int reportaA=rs.getInt("REPORTA_A");
                int extension=rs.getInt("EXTENSION");
                
                emp = new Empleado(empleadoId, nombre, apellido, fechaNac, reportaA, extension);
                
                if(reportaA>0){
                Empleado jefe= findById(reportaA);
                emp.setJefe(jefe.getNombreCompleto());
                }
            }
            base.desconectarBD();
        } catch (SQLException ex) {
            System.out.println("SQL error :" + ex.getMessage());
        }
        return emp;
    }

    
}
