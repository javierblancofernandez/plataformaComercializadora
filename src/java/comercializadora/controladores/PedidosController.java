/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package comercializadora.controladores;

import comercializadora.dao.ClienteDao;
import comercializadora.dao.DetalleOrdenesJDBCDao;
import comercializadora.dao.EmpleadoDao;
import comercializadora.dao.IProductoDao;
import comercializadora.dao.OrdenJDBCDao;
import comercializadora.dao.ProductoJDBCDao;
import comercializadora.modelos.Cliente;
import comercializadora.modelos.DetalleOrdenes;
import comercializadora.modelos.Empleado;
import comercializadora.modelos.Orden;
import comercializadora.modelos.Producto;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author FRANCISCO
 */
@WebServlet(name = "PedidosController", urlPatterns = {"/pedidos"})
public class PedidosController extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        if (request.getParameter("accion") != null) {
            String accion = request.getParameter("accion");
            switch (accion) {
                case "verPedidos":
                    verPedidos(request, response);
                    break;
                case "hacerPedidos":
                    hacerPedidos(request, response);
                    break;
                case "verPedido":
                    verPedido(request, response);
                    break;
            }
        } else {
            request.getRequestDispatcher("/WEB-INF/ordenes-de-compra/dashboard.jsp").forward(request, response);
        }

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        if (request.getParameter("accion") != null) {
            String accion = request.getParameter("accion");
            switch (accion) {
                case "addProducto":
                    addProducto(request, response);
                    break;
                case "terminarPedido":
                    terminarPedido(request, response);
                    break;
            }
        }

    }

    private void verPedidos(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
         OrdenJDBCDao daoOrden = new OrdenJDBCDao();
         List<Orden> ordenes = daoOrden.listAll();
         request.setAttribute("ordenes", ordenes);
         
        request.getRequestDispatcher("/WEB-INF/ordenes-de-compra/index.jsp").forward(request, response);
    }

    private void hacerPedidos(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        Orden orden = (Orden) request.getSession().getAttribute("orden");

        ClienteDao daoCli = new ClienteDao();
        EmpleadoDao daoEmp = new EmpleadoDao();
        ProductoJDBCDao daoProd = new ProductoJDBCDao();

        List<Empleado> empleados = daoEmp.listAll();
        List<Producto> productos = daoProd.listAll();
        List<Cliente> clientes = daoCli.listaClientes();

        request.setAttribute("empleados", empleados);
        request.setAttribute("productos", productos);
        request.setAttribute("clientes", clientes);

        if (orden == null) {
            orden = new Orden();
            orden.setImporte(0.0);
            orden.setFechaOrden(new java.sql.Date(new java.util.Date().getTime()));
        } else {
            double importeOrden = 0.0;
            for (DetalleOrdenes detalle : orden.getDetalles()) {
                importeOrden += detalle.getImporte();
            }
            orden.setImporte(importeOrden);
        }

        request.getSession().setAttribute("orden", orden);

        request.getRequestDispatcher("/WEB-INF/ordenes-de-compra/new.jsp").forward(request, response);

    }

    private void addProducto(HttpServletRequest request, HttpServletResponse response) throws IOException {

        long idProducto = Long.parseLong(request.getParameter("idProd"));
        double cantidad = Double.parseDouble(request.getParameter("cantProd"));
        IProductoDao productoDao = new ProductoJDBCDao();
        Producto producto = productoDao.findById(idProducto);
        double importe = producto.getPrecioUnit() * cantidad;
        Orden orden = (Orden) request.getSession().getAttribute("orden");

        DetalleOrdenes detalle = new DetalleOrdenes();
        detalle.setCantidad(cantidad);
        detalle.setProducto(producto);
        detalle.setOrden(orden);
        detalle.setImporte(importe);

        if (orden == null) {
            orden = new Orden();
            request.getSession().setAttribute("orden", orden);
        }
        orden.getDetalles().add(detalle);

        response.sendRedirect(request.getContextPath() + "/pedidos?accion=hacerPedidos");

    }

    private void terminarPedido(HttpServletRequest request, HttpServletResponse response) throws IOException {
        
        long idCliente = Long.parseLong(request.getParameter("idCliente"));
        long idEmpleado = Long.parseLong(request.getParameter("idEmpleado"));
        
        ClienteDao daoCliente = new ClienteDao();
        EmpleadoDao daoEmpleado = new EmpleadoDao();
        
        Cliente cliente = daoCliente.findById(idCliente);
        Empleado empleado = daoEmpleado.findById(idEmpleado);
        
        Orden orden = (Orden) request.getSession().getAttribute("orden");
        orden.setCliente(cliente);
        orden.setEmpleado(empleado);
        
        OrdenJDBCDao daoOrden = new OrdenJDBCDao();
        Orden ordenCreada = daoOrden.insert(orden);
        
        if (ordenCreada != null) {
            
            request.getSession().setAttribute("ordenCreada", ordenCreada);
            request.getSession().removeAttribute("orden");
            response.sendRedirect(request.getContextPath() + "/pedidos?accion=verPedidos");
        }
    }

    private void verPedido(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        
        long id = Long.parseLong(request.getParameter("idPedido"));
        OrdenJDBCDao daoOrden = new OrdenJDBCDao();
        DetalleOrdenesJDBCDao daoDetalles = new DetalleOrdenesJDBCDao();
        
        Orden orden=daoOrden.findById(id);
        List<DetalleOrdenes> detalles = daoDetalles.getDetalles(orden);
        orden.setDetalles(detalles);
        
        request.setAttribute("orden", orden);
        request.getRequestDispatcher("/WEB-INF/ordenes-de-compra/show.jsp").forward(request, response);
        
        
        
        
    }

}
