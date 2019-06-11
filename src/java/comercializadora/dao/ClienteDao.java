/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package comercializadora.dao;

import comercializadora.modelos.Cliente;
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
public class ClienteDao implements IClienteJDBCdao{

    PreparedStatement ps = null;
    
    @Override
    public List<Cliente> listaClientes() {
        List <Cliente> listaCliente=new ArrayList<>();
            Cliente cli;
            BaseDatosPG base = new BaseDatosPG();
            String sql="SELECT * FROM clientes";
        
        try {
            
            
            ps= base.getConnection().prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            while(rs.next()){
            cli=new Cliente();
            long clienteId = rs.getLong("CLIENTEID");
            String cedulaRuc = rs.getString("CEDULA_RUC");
            String nombreCia= rs.getString("NOMBRECIA");
            String nombrecontac=rs.getString("NOMBRECONTACTO");
            String direccCli=rs.getString("DIRECCIONCLI");
            String faxCli=rs.getString("FAX");
            String emailCli=rs.getString("EMAIL");
            String celularCli=rs.getString("CELULAR");
            String fijoCli=rs.getString("FIJO");
            
            listaCliente.add(new Cliente(clienteId, cedulaRuc, nombreCia, nombrecontac, direccCli, faxCli, emailCli, celularCli, fijoCli));
            
            
            }
            base.desconectarBD();
            
            
        } catch (SQLException ex) {
            System.out.println("SQL error :" + ex.getMessage());
        }
        return listaCliente;
    }

    @Override
    public String insert(Cliente cli) {
        String correcto ="";
        
        String sql = " INSERT INTO clientes (CLIENTEID,CEDULA_RUC,NOMBRECIA,NOMBRECONTACTO,DIRECCIONCLI,FAX,EMAIL,CELULAR,FIJO)"
                + " VALUES (?,?,?,?,?,?,?,?,?)";
        BaseDatosPG base = new BaseDatosPG();
        try {
            ps = base.getConnection().prepareStatement(sql);
            ps.setLong(1, cli.getClienteId());
            ps.setString(2, cli.getCedulaRuc());
            ps.setString(3, cli.getNombrecia());
            ps.setString(4, cli.getNombreContacto());
            ps.setString(5, cli.getDireccionCli());
            ps.setString(6, cli.getFax());
            ps.setString(7, cli.getEmail());
            ps.setString(8, cli.getCelular());
            ps.setString(9, cli.getFijo());
           
            ps.executeUpdate();
            correcto="la grabacion fue un exito";
            base.desconectarBD();
        } catch (SQLException ex) {
            Logger.getLogger(ClienteDao.class.getName()).log(Level.SEVERE, null, ex);
        }
        return correcto;
    }

    @Override
    public String update(Cliente cli) {
        String correcto="";
        try {
            String sql = "UPDATE clientes SET CEDULA_RUC=? , NOMBRECIA=? , NOMBRECONTACTO=? ,"
                    + " DIRECCIONCLI=? , FAX=? , EMAIL=? ,CELULAR=?, FIJO=?   where CLIENTEID =? ";
            BaseDatosPG base = new BaseDatosPG();
            ps=base.getConnection().prepareStatement(sql);
            ps.setLong(9, cli.getClienteId());
            ps.setString(1,cli.getCedulaRuc());
            ps.setString(2,cli.getNombrecia());
            ps.setString(3,cli.getNombreContacto());
            ps.setString(4,cli.getDireccionCli());
            ps.setString(5,cli.getFax());
            ps.setString(6,cli.getEmail());
            ps.setString(7,cli.getCelular());
            ps.setString(8,cli.getFijo());
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
    public String delete(Cliente cli) {
        String correcto="";
        try {
            String sql = "DELETE FROM clientes where CLIENTEID = '"+cli.getClienteId()+"'";
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
    public Cliente findById(long cliId) {
       
        Cliente cli=null;
        BaseDatosPG base = new BaseDatosPG();
        String sql = "Select * FROM clientes WHERE CLIENTEID=? LIMIT 1 ";

        try {
            ps = base.getConnection().prepareStatement(sql);
            ps.setLong(1,cliId);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                cli = new Cliente();
                cli.setClienteId(rs.getLong("CLIENTEID"));
                cli.setCedulaRuc(rs.getString("CEDULA_RUC"));
                cli.setNombrecia(rs.getString("NOMBRECIA"));
                cli.setNombreContacto(rs.getString("NOMBRECONTACTO"));
                cli.setDireccionCli(rs.getString("DIRECCIONCLI"));
                cli.setFax(rs.getString("FAX"));
                cli.setEmail(rs.getString("EMAIL"));
                cli.setCelular(rs.getString("CELULAR"));
                cli.setFijo(rs.getString("FIJO"));
                            
            }
            base.desconectarBD();
        } catch (SQLException ex) {
            System.out.println("SQL error :" + ex.getMessage());
        }
        return cli;
    }
    
}
