/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package comercializadora.dao;

import comercializadora.modelos.Cliente;
import comercializadora.modelos.DetalleOrdenes;
import comercializadora.modelos.Empleado;
import comercializadora.modelos.Orden;
import conexiones.BaseDatosPG;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author FRANCISCO
 */
public class OrdenJDBCDao implements IOrdenDao {

    PreparedStatement ps = null;

    @Override
    public List<Orden> listAll() {
        Orden orden = null;
        Empleado empleado = null;
        Cliente cliente = null;
        List<Orden> ordenes = new ArrayList<>();

        BaseDatosPG base = new BaseDatosPG();

        EmpleadoDao daoEmpleado = new EmpleadoDao();
        ClienteDao daoCliente = new ClienteDao();

        try {
            String sql = " Select * from ordenes";
            ps = base.getConnection().prepareStatement(sql);

            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                long id = rs.getLong("ORDENID");
                long idEmpleado = rs.getLong("EMPLEADOID");
                long idCliente = rs.getLong("CLIENTEID");
                java.sql.Date fecha = rs.getDate("FECHAORDEN");
                double descuento = rs.getDouble("DESCUENTO");
                double importe = rs.getDouble("IMPORTE");

                empleado = daoEmpleado.findById(idEmpleado);
                cliente = daoCliente.findById(idCliente);

                if (empleado != null || cliente != null) {
                    orden = new Orden(id, empleado, cliente, fecha, descuento, importe);
                    ordenes.add(orden);

                }
            }
            base.desconectarBD();
        } catch (SQLException ex) {
            ex.getStackTrace();

        } finally {
            if (base.getConnection() != null) {
                base.desconectarBD();
            }
        }
        return ordenes;
    }

    @Override
    public Orden insert(Orden orden) {
        String mensaje = "";
        BaseDatosPG base = new BaseDatosPG();
        try {
            int idGenerado = 0;
            base.getConnection().setAutoCommit(false);
            String sql = "INSERT INTO ordenes( CLIENTEID , EMPLEADOID , FECHAORDEN , DESCUENTO , IMPORTE )"
                    + " VALUES(?,?,?,?,?)";
            ps = base.getConnection().prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);

            ps.setInt(1, (int) orden.getCliente().getClienteId());
            ps.setInt(2, (int) orden.getEmpleado().getEmpleadoId());
            ps.setDate(3, (Date) orden.getFechaOrden());
            ps.setDouble(4, orden.getDescuento());
            ps.setDouble(5, orden.getImporte());

            ps.executeUpdate();

            ResultSet generatedKeys = ps.getGeneratedKeys();
            if (generatedKeys.next()) {
                idGenerado = generatedKeys.getInt(1);

            }
            PreparedStatement ps2;

            for (DetalleOrdenes detalle : orden.getDetalles()) {
                String sqlDetalles = " INSERT INTO detalle_ordenes "
                        + "( ORDENID ,PRODUCTOID , CANTIDAD , IMPORTE )"
                        + " VALUES (?,?,?,?)";
                ps2 = base.getConnection().prepareStatement(sqlDetalles);
                ps2.setInt(1, idGenerado);
                ps2.setInt(2, (int) detalle.getProducto().getProductoId());
                ps2.setDouble(3, detalle.getCantidad());
                ps2.setDouble(4, detalle.getImporte());

                ps2.executeUpdate();

            }

            mensaje = " el pedido se ha creado correctamente";
            base.getConnection().commit();

            orden.setOrdenId(idGenerado);
        } catch (SQLException ex) {
            if (base.getConnection() != null) {
                try {
                    System.err.print("la transaccion no pudo realizarse");
                    base.getConnection().rollback();
                } catch (SQLException ex1) {
                    System.err.print("no pudo hacerse rollback de la transacción");
                }
            }
            ex.getStackTrace();
            mensaje = "no fue posible registrar el producto : " + ex.getMessage();
            base.desconectarBD();

        } finally {
            if (base.getConnection() != null) {
                base.desconectarBD();
            }
        }
        return orden;
    }

    @Override
    public String update(Orden orden) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public String delete(Orden orden) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Orden findById(long ordenId) {
        Orden orden = null;
        Empleado empleado = null;
        Cliente cliente = null;
        BaseDatosPG base = new BaseDatosPG();

        try {
            String sql = " SELECT * FROM ordenes WHERE ORDENID=? limit 1";
            PreparedStatement ps = base.getConnection().prepareStatement(sql);
            ps.setLong(1, ordenId);

            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                long idEmpleado = rs.getLong("EMPLEADOID");
                long idCliente = rs.getLong("CLIENTEID");
                java.sql.Date fecha = rs.getDate("FECHAORDEN");
                double descuento = rs.getDouble("DESCUENTO");
                double importe = rs.getDouble("IMPORTE");

                IEmpleadoDao daoEmp = new EmpleadoDao();
                IClienteJDBCdao daoCli = new ClienteDao();

                empleado = daoEmp.findById(idEmpleado);
                cliente = daoCli.findById(idCliente);
                orden = new Orden(ordenId, empleado, cliente, fecha, descuento, importe);

            }
            base.desconectarBD();

        } catch (SQLException ex) {
            ex.getStackTrace();
        } finally {
            if (base.getConnection() != null) {
                base.desconectarBD();
            }

        }
        return orden;
    }

   

}
