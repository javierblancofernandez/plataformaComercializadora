/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package comercializadora.dao;

import comercializadora.modelos.Categoria;
import comercializadora.modelos.Producto;
import comercializadora.modelos.Proveedor;
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
public class ProductoJDBCDao implements IProductoDao {

    PreparedStatement ps = null;

    @Override
    public List<Producto> listAll() {
        Producto producto;
        List<Producto> listaProducto = new ArrayList<>();
        BaseDatosPG base = new BaseDatosPG();
        try {

            String sql = "SELECT * FROM productos";
            ps = base.getConnection().prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                long idProdu = rs.getLong("PRODUCTOID");
                long idProveedor = rs.getLong("PROVEEDORID");
                long idCategoria = rs.getLong("CATEGORIAID");
                String descripProducto = rs.getString("DESCRIPCION");
                double precioProducto = rs.getDouble("PRECIOUNIT");
                int existenciaProducto = rs.getInt("EXISTENCIA");

                Categoria cat = new CategoriaJDBCDao().findByID(idCategoria);
                Proveedor prov = new ProveedorJDBCDAO().findById(idProveedor);

                producto = new Producto(idProdu, prov, cat, descripProducto, precioProducto, precioProducto);

                listaProducto.add(producto);

            }
            base.desconectarBD();

        } catch (SQLException ex) {
            System.out.println("Error en Listall de productos : " + ex.getMessage());
        }
        return listaProducto;

    }

    @Override
    public String insert(Producto prod) {

        String mensaje = "";
        BaseDatosPG base = new BaseDatosPG();

        String sql = "INSERT INTO productos ( PRODUCTOID, PROVEEDORID , CATEGORIAID , DESCRIPCION ,PRECIOUNIT, EXISTENCIA )"
                + "VALUES (?,?,?,?,?,?)";

        try {
            ps = base.getConnection().prepareStatement(sql);
            ps.setLong(1, prod.getProductoId());
            ps.setLong(2, prod.getProveedorId().getProveedorId());
            ps.setLong(3, prod.getCategoriaId().getCategoriaId());
            ps.setString(4, prod.getDescripcion());
            ps.setDouble(5, prod.getPrecioUnit());
            ps.setDouble(6, prod.getExistencia());

            ps.executeUpdate();
            mensaje = " EL producto se ha creado correctamente ";

            base.desconectarBD();
        } catch (SQLException ex) {
            mensaje = "no fue posible resitrar el producto : " + ex.getMessage();
            base.desconectarBD();
        }
        return mensaje;
    }

    @Override
    public String update(Producto prod) {
        String mensaje = "";
        BaseDatosPG base = new BaseDatosPG();

        String sql = " UPDATE productos SET  proveedorid=? , categoriaid=? , descripcion=? , preciounit=? , existencia=? "
                + " WHERE productoid=? ";
        try {
            ps = base.getConnection().prepareStatement(sql);
            ps.setLong(1, prod.getProveedorId().getProveedorId());
            ps.setLong(2, prod.getCategoriaId().getCategoriaId());
            ps.setString(3, prod.getDescripcion());
            ps.setDouble(4, prod.getPrecioUnit());
            ps.setDouble(5, prod.getExistencia());
            ps.setLong(6, prod.getProductoId());

            ps.executeUpdate();
            mensaje = " la ctualizacion de producto fue un exito";
            base.desconectarBD();

        } catch (SQLException ex) {
            mensaje = "no fue posible actualizar el producto : " + ex.getMessage();
            base.desconectarBD();
        }
        return mensaje;
    }

    @Override
    public String delete(Producto prod) {
        String mensaje = "";
        BaseDatosPG base = new BaseDatosPG();

        String sql = "DELETE FROM productos WHERE PRODUCTOID=?";
        try {
            ps = base.getConnection().prepareStatement(sql);
            ps.setLong(1, prod.getProductoId());
            mensaje = " el producto se borro correcrtamente";
            ps.executeUpdate();
        } catch (SQLException ex) {
            mensaje = "no fue posible borrar el producto : " + ex.getMessage();
        }
        return mensaje;
    }

    @Override
    public Producto findById(long idProd) {
        String mensaje = "";
        BaseDatosPG base = new BaseDatosPG();
        Producto producto = null;
        String sql = " SELECT * FROM productos WHERE PRODUCTOID=? LIMIT 1";
        try {
            ps = base.getConnection().prepareStatement(sql);
            ps.setLong(1, idProd);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                long id = rs.getLong("PRODUCTOID");
                long proveedorId = rs.getLong("PROVEEDORID");
                long categoriaId = rs.getLong("CATEGORIAID");
                String descripcion = rs.getString("DESCRIPCION");
                double precio = rs.getDouble("PRECIOUNIT");
                int existencia = rs.getInt("EXISTENCIA");

                Categoria cat = new CategoriaJDBCDao().findByID(categoriaId);
                Proveedor prov = new ProveedorJDBCDAO().findById(proveedorId);

                producto = new Producto(idProd, prov, cat, descripcion, precio, existencia);

            }
        } catch (SQLException ex) {
            Logger.getLogger(ProductoJDBCDao.class.getName()).log(Level.SEVERE, null, ex);
        }

        return producto;
    }

    @Override
    public List<Producto> getProductoByCategoria(Categoria cat) {
        Producto producto = null;
        BaseDatosPG base = new BaseDatosPG();
        List<Producto> listaProducto = new ArrayList<>();

        String sql = "SELECT * FROM productos WHERE CATEGORIAID=?";
        try {
            ps = base.getConnection().prepareStatement(sql);
            ps.setLong(1, cat.getCategoriaId());
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                long id = rs.getLong("PRODUCTOID");
                long proveedorId = rs.getLong("PROVEEDORID");
                long categoriaId = rs.getLong("CATEGORIAID");
                String descripcion = rs.getString("DESCRIPCION");
                double precio = rs.getDouble("PRECIOUNIT");
                int existencia = rs.getInt("EXISTENCIA");
                
                Categoria cate = new CategoriaJDBCDao().findByID(categoriaId);
                Proveedor prov = new ProveedorJDBCDAO().findById(proveedorId);

                producto = new Producto(proveedorId, prov, cate, descripcion, precio, precio);

                listaProducto.add(producto);
            }
        } catch (SQLException ex) {
            Logger.getLogger(ProductoJDBCDao.class.getName()).log(Level.SEVERE, null, ex);
        }
        return listaProducto;
    }

    @Override
    public List<Producto> busquedaPorCriterio(String param) {
        BaseDatosPG base = new BaseDatosPG();
       List<Producto> listaProductos=new ArrayList<>();
       Producto prod;
       
       String sql = "Select * from productos where DESCRIPCION like ?";
        try {
            ps = base.getConnection().prepareStatement(sql);
            ps.setString(1, "%"+param+"%");
            ResultSet rs = ps.executeQuery();
            
            while(rs.next()){
                System.out.println("resultado en Producto :");
                long id = rs.getInt("PRODUCTOID");
                long idProv = rs.getInt("PROVEEDORID");
                long idCat = rs.getInt("CATEGORIAID");
                String descr = rs.getString("DESCRIPCION");
                double precio = rs.getDouble("PRECIOUNIT");
                double existencia = rs.getInt("EXISTENCIA");
                
                Categoria cat= new CategoriaJDBCDao().findByID(idCat);
                Proveedor prov=new ProveedorJDBCDAO().findById(idProv);
                
                prod =new Producto(id, prov, cat, descr, precio, existencia);
            listaProductos.add(prod);
            }
            base.desconectarBD();
            
            
        } catch (SQLException ex) {
            System.out.println("error por busqueda de criterio productos :"+ex.getMessage());
            
        }finally{
            if (base.getConnection()!=null) {
                base.desconectarBD();
            }
        }
       return listaProductos;
    }

}
